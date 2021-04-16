--薪资结果中间表生成
  function createWageMid(cIndexCalNo varchar2)
    return varchar is
    pragma autonomous_transaction;

  begin
    --清理薪资结果中间表数据
    delete from tmwagemid
     where IndexCalNo = cIndexCalNo
       --and managecom = cManageCom
       and branchtype = '1';

    --清理薪资结果表数据
    delete from tmwage
     where IndexCalNo = cIndexCalNo
       --and managecom = cManageCom
       and branchtype = '1';

      -- 插入中间表数据
       insert into tmwagemid
                             (INDEXCALNO,
                              AgentCode,
                              BranchType,
                              AgentGrade,
                              ManageCom,
                              AgentGroup,
                              BranchAttr,
                              Operator,
                              MakeDate,
                              MakeTime,
                              ModifyDate,
                              ModifyTime)
                             (select cIndexCalNo,
                                      a.agentcode,
                                     '1',
                                     a.posttype,
                                     a.ManageCom,
                                     a.agentgroup,
                                     a.branchattr,
                                     'admin',
                                     basic.CurrentDate,
                                     basic.CurrentTime,
                                     basic.CurrentDate,
                                     basic.CurrentTime
                                from tmwageagentc a
                               where 1 = 1
                                 and a.bakmonth = cIndexCalNo
                                -- and a.managecom = '?mManageCom?'
                                 and (a.state = '01' or
                                     ((a.state <> '01') and
                                     to_char(add_months(a.OUTWORKDATE, 3), 'YYYYMM') >= cIndexCalNo))) ;

    -- 先计算基础值更新
    update tmwagemid a set
            a.tmr_rate_anp_1 = (case when a.agentgrade = 'TMR'  then TMWAGECALITEM.GetCommRate(a.managecom,'ANP',a.agentcode,a.indexcalno,a.agentgrade,'1') else 0 end),
            a.tmr_amep_anp_1 = (case when a.agentgrade = 'TMR'  then TMWAGECALITEM.CalAMEP(a.managecom,'ANP',a.agentcode,a.indexcalno,a.agentgrade,'1') else 0 end),
            a.tmr_multiple =  (case when a.agentgrade = 'TMR'  then TMWAGECALITEM.GetTMRMultiple(a.managecom,a.agentcode ,a.indexcalno )  else 0   end),
           a.tmr_multiple_max_money = (case when a.agentgrade = 'TMR'  then TMWAGECALITEM.GetMaxMoney(a.managecom,a.agentcode ,a.indexcalno)  else 0   end),
           a.tms_rate_anp_1 =  (case when a.agentgrade = 'TMS'  then TMWAGECALITEM.GetCommRate(a.managecom,'ANP',a.agentcode,a.indexcalno,a.agentgrade,'1') else 0    end),
           a.tms_amep_anp_1 =  (case when a.agentgrade = 'TMS'  then TMWAGECALITEM.CalAMEP(a.managecom,'ANP',a.agentcode,a.indexcalno,a.agentgrade,'1') else 0   end),
           a.ccm_rate_anp_1 =  (case when a.agentgrade = 'CCM'  then TMWAGECALITEM.GetCommRate(a.managecom,'ANP',a.agentcode,a.indexcalno,a.agentgrade,'1') else 0   end),
           a.ccm_amep_anp_1 =  (case when a.agentgrade = 'CCM'  then TMWAGECALITEM.CalAMEP(a.managecom,'ANP',a.agentcode,a.indexcalno,a.agentgrade,'1') else 0   end)
    where a.indexcalno = cIndexCalNo ;

     --再进行组合计算更新
    update tmwagemid a set
              a.tmr_commission_anp_1 = (case when a.agentgrade = 'TMR'  then a.tmr_rate_anp_1 * a.tmr_amep_anp_1   else 0 end),
             a.tms_commission_anp_1 =  (case when a.agentgrade = 'TMS'  then a.tms_rate_anp_1  *  a.tms_amep_anp_1  else 0  end),
             a.ccm_commission_anp_1 =  (case when a.agentgrade = 'CCM'  then a.ccm_rate_anp_1 * a.ccm_amep_anp_1  else 0  end)
     where a.indexcalno = cIndexCalNo ;

     -- 薪资明细中间表 同步到 薪资明细表 tmwage
      merge into tmwage a
       using tmwagemid  b
       on (a.indexcalno = b.indexcalno
              and a.agentcode = b.agentcode
              and a.branchtype = b.branchtype
              and a.agentgrade = b.agentgrade
              and a.managecom = b.managecom
              and a.agentgroup = b.agentgroup
              and a.branchattr = b.branchattr)
         when matched then
             update set
                a.w1 = b.tmr_rate_anp_1,
                a.w77 = b.tmr_amep_anp_1,
                a.w41 = b.tmr_commission_anp_1,
                a.w100 = b.tmr_multiple,
                a.w114 = b.tmr_multiple_max_money,
                a.w7 = b.tms_rate_anp_1,
                a.w83 = b.tms_amep_anp_1,
                a.w47 = b.tms_commission_anp_1,
                a.w13 = b.ccm_rate_anp_1,
                a.w89 = b.ccm_amep_anp_1,
                a.w53 = b.ccm_commission_anp_1,
                a.modifydate = b.modifydate,
                a.modifytime = b.modifytime
         when not matched then
           insert ( IndexCalNo,
                       F08,
                       AgentCode,
                       Branchtype,
                       AgentGrade,
                       ManageCom,
                       AgentGroup,
                       BranchAttr,
                       Operator,
                       MakeDate,
                       MakeTime,
                       ModifyDate,
                       ModifyTime,
                       W1,
                       W77,
                       W41,
                       W100,
                       W114,
                       W7,
                       W83,
                       W47,
                       W13,
                       W89,
                       W53)
                       values(b.indexcalno,
                                 b.indexcalno,
                                 b.agentcode,
                                 b.branchtype,
                                 b.agentgrade,
                                 b.managecom,
                                 b.agentgroup,
                                 b.branchattr,
                                 b.operator,
                                 b.makedate,
                                 b.maketime,
                                 b.modifydate,
                                 b.modifytime,
                                 b.tmr_rate_anp_1,
                                 b.tmr_amep_anp_1,
                                 b.tmr_commission_anp_1,
                                 b.tmr_multiple,
                                 b.tmr_multiple_max_money,
                                 b.tms_rate_anp_1,
                                 b.tms_amep_anp_1,
                                 b.tms_commission_anp_1,
                                 b.ccm_rate_anp_1,
                                 b.ccm_amep_anp_1,
                                 b.ccm_commission_anp_1
                                 );

  --离职缓发的计佣月份改成按离职月份+3   by 2020年11月16日
 update tmwage a
       set a.f08 = to_char(add_Months(to_date((SELECT tm.dimissionmonth FROM TMSALARYAGENTC tm where a.agentcode = tm.agentcode and a.indexcalno = tm.bakmonth and tm.agentstate = '03'), 'yyyymm'), 3),
                           'yyyymm')
     where a.indexcalno = cIndexCalNo
       and exists
     (select 1
              from TMSALARYAGENTC t
             where a.agentcode = t.agentcode
                  --and t.managecom = cManageCom
               and a.indexcalno = t.bakmonth
               and t.agentstate = '03'
               and to_char(add_Months(to_date(t.dimissionmonth, 'yyyymm'), 3),
                           'yyyymm') >= cIndexCalNo);

    update tmwage a
       set a.f08 = nvl((select t.postponemonth
                         from TMWageDelay t, laagent t1
                        where t.agentnum = t1.agentnum
                          and t1.branchtype = '1'
                          and t.managecom = t1.managecom
                          and a.agentcode = t1.agentcode
                          and t.commissionmonth = a.indexcalno),
                       a.f08)
     where a.indexcalno = cIndexCalNo;

    --回刷佣金率类型  --薪资快照时刷进去，佣金率根据人员刷进去的进行匹配
    update tmwage t
       set (t.f06, t.f07) =
           (select a.anpagentcommtype, a.gwpagentcommtype
              from tmwageagentc a
             where a.bakmonth = t.indexcalno
               and t.agentcode = a.agentcode
               and a.managecom = t.managecom
               and a.posttype = t.agentgrade)
     where t.indexcalno = cIndexCalNo;


    --回写计算保费
    --ANP TMR
    merge into tmwage x
    using (select a.tmrcode, sum(a.calmoney) as money1
             from tmcommission a
            where a.iswage = 'Y'
              and a.isjumppoint = 'Y'
              and a.calbasetype = 'ANP'
              and a.commissionmonth = cIndexCalNo
              and a.commissioncount = '1'
            group by a.tmrcode) t
    on (t.tmrcode = x.agentcode and x.agentgrade = 'TMR' and x.indexcalno = cIndexCalNo )
    when matched then
      update set x.W115 = t.money1;

    --ANP TMS
    merge into tmwage x
    using (select a.tmScode, sum(a.calmoney) as money1
             from tmcommission a
            where a.iswage = 'Y'
              and a.isjumppoint = 'Y'
              and a.calbasetype = 'ANP'
              and a.commissionmonth = cIndexCalNo
              and a.commissioncount = '1'
            group by a.tmScode) t
   on (t.tmScode = x.agentcode and x.agentgrade = 'TMR' and x.indexcalno = cIndexCalNo )
    when matched then
      update set x.W115 = t.money1;

    --ANP CCM
    merge into tmwage x
    using (select a.CCMcode, sum(a.calmoney) as money1
             from tmcommission a
            where a.iswage = 'Y'
              and a.isjumppoint = 'Y'
              and a.calbasetype = 'ANP'
              and a.commissionmonth = cIndexCalNo
              and a.commissioncount = '1'
            group by a.CCMcode) t
    on (t.CCMcode = x.agentcode and x.agentgrade = 'TMR' and x.indexcalno = cIndexCalNo )
    when matched then
      update set x.W115 = t.money1;

    commit;

    return 'OK';
  Exception
    When Others Then
      --Return 'ERROR';
      rollback;
      Return sqlcode || sqlerrm;
  end;