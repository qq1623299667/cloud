
  function realWageTotal(cIndexCalNo varchar2) return varchar2 IS
    pragma autonomous_transaction;
    o_date  varchar2(30);
    o_value varchar2(500);
    --tMinComm    varchar2(20);
  begin
    update tmwagetotal t
       set (t.tmrtype, t.tmstype) =
           (select (case
                     when a.gradelevel = 'TMR' and a.agentstate = '01' then
                      add_Months(to_date(cIndexCalNo, 'YYYYMM'), 1) - 1 -
                      a.takeupdate
                     when a.gradelevel = 'TMR' and a.agentstate <> '01' then
                      a.dimissiondate - a.takeupdate
                   end) as TMRTYPE,
                   (case
                     when a.gradelevel = 'TMS' and a.agentstate = '01' then
                      add_Months(to_date(cIndexCalNo, 'YYYYMM'), 1) - 1 -
                      nvl(a.tmrtotmsdate,
                          (select max(t1.changedate)
                             from tmagentchangetrace t1
                            where t.agentcode = t1.agentcode
                              and t.managecom = t1.newmanagecom
                              and t1.TRACETYPE = '03'
                              and t1.oldagentgrade in
                                  (select d.code
                                     from ldcode d
                                    where d.codetype = 'f_tmagentgrade'
                                      and d.codealias = 'TMR')))
                     when a.gradelevel = 'TMS' and a.agentstate <> '01' then
                      a.dimissiondate -
                      nvl(a.tmrtotmsdate,
                          (select max(t1.changedate)
                             from tmagentchangetrace t1
                            where t.agentcode = t1.agentcode
                              and t.managecom = t1.newmanagecom
                              and t1.TRACETYPE = '03'
                              and t1.oldagentgrade in
                                  (select d.code
                                     from ldcode d
                                    where d.codetype = 'f_tmagentgrade'
                                      and d.codealias = 'TMR')))
                   end) as TMSTYPE
              from TMSALARYAGENTC a
             where a.bakmonth = cIndexCalNo
               and a.gradelevel in ('TMR', 'TMS')
               and a.agentcode = t.agentcode)
     where 1 = 1
       and t.wageno = cIndexCalNo
       and exists (select 1
              from TMSALARYAGENTC a
             where a.agentcode = t.agentcode
               and a.BAKMONTH = cIndexCalNo);
    commit;

    return 'OK';
  Exception
    When Others Then
      --Return 'ERROR';
      rollback;
      Return sqlcode || sqlerrm;
  end realWageTotal;
