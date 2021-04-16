
  --正式薪资结果表生成
  function createWage(cIndexCalNo varchar2, cManageCom varchar2)
    return varchar is
    pragma autonomous_transaction;

  begin
    --先清理数据
    delete from tmwage
     where IndexCalNo = cIndexCalNo
       and managecom = cManageCom
       and branchtype = '1';

    --commit;
    --插入lawage表
    insert into tmwage
      (IndexCalNo,
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
       ModifyTime
       /*,F05*/,
       W100,
       W1,
       W2,
       W3,
       W4,
       W5,
       W6,
       W7,
       W8,
       W9,
       W10,
       W11,
       W12,
       W13,
       W14,
       W15,
       W16,
       W17,
       W18,
       W19,
       W20,
       W21,
       W22,
       W23,
       W24,
       W25,
       W26,
       W27,
       W28,
       W29,
       W30,
       W31,
       W32,
       W33,
       W34,
       W35,
       W36,
       W37,
       W38,
       W39,
       W40,
       W41,
       W42,
       W43,
       W44,
       W45,
       W46,
       W47,
       W48,
       W49,
       W50,
       W51,
       W52,
       W53,
       W54,
       W55,
       W56,
       W57,
       W58,
       W59,
       W60,
       W61,
       W62,
       W63,
       W64,
       W65,
       W66,
       W67,
       W68,
       W69,
       W70,
       W71,
       W72,
       W73,
       W74,
       W75,
       W76,
       W77,
       W78,
       W79,
       W80,
       W81,
       W82,
       W83,
       W84,
       W85,
       W86,
       W87,
       W88,
       W89,
       W90,
       W91,
       W92,
       W93,
       W94,
       W95,
       W96,
       W97,
       W98,
       W99,
       W101,
       W102,
       W103,
       W104,
       W105,
       W106,
       W107,
       W108,
       W109,
       W110,
       W111,
       W112,
       W113,
       W114)
      select a.wageno,
             a.wageno,
             a.agentcode,
             a.branchtype,
             a.agentgrade,
             a.managecom,
             a.agentgroup,
             a.branchattr,
             'admin',
             basic.CurrentDate,
             basic.CurrentTime,
             basic.CurrentDate,
             basic.CurrentTime

             -- ,max(decode(a.wagecode, 'W201808383', a.s, '')) W201808383 /*员工计佣类型*/
            ,
             max(decode(a.wagecode, 'W201808382', a.n0, 1)) W201808382 /*新人翻倍*/,
             max(decode(a.wagecode, 'W201808025', a.n4, 0)) W201808025 /*1TMR一期佣金率_ANP*/,
             max(decode(a.wagecode, 'W201808026', a.n4, 0)) W201808026 /*TMR二期佣金率_ANP*/,
             max(decode(a.wagecode, 'W201808027', a.n4, 0)) W201808027 /*TMR三期佣金率_ANP*/,
             max(decode(a.wagecode, 'W201808028', a.n4, 0)) W201808028 /*TMR五期佣金率_ANP*/,
             max(decode(a.wagecode, 'W201808029', a.n4, 0)) W201808029 /*TMR四期佣金率_ANP*/,
             max(decode(a.wagecode, 'W201808030', a.n4, 0)) W201808030 /*TMR六期佣金率_ANP*/

            ,
             max(decode(a.wagecode, 'W201808031', a.n4, 0)) W201808031 /*TMS一期佣金率_ANP*/,
             max(decode(a.wagecode, 'W201808032', a.n4, 0)) W201808032 /*TMS二期佣金率_ANP*/,
             max(decode(a.wagecode, 'W201808033', a.n4, 0)) W201808033 /*TMS三期佣金率_ANP*/,
             max(decode(a.wagecode, 'W201808034', a.n4, 0)) W201808034 /*TMS五期佣金率_ANP*/,
             max(decode(a.wagecode, 'W201808035', a.n4, 0)) W201808035 /*TMS四期佣金率_ANP*/,
             max(decode(a.wagecode, 'W201808036', a.n4, 0)) W201808036 /*TMS六期佣金率_ANP*/

            ,
             max(decode(a.wagecode, 'W201808037', a.n4, 0)) W201808037 /*CCM一期佣金率_ANP*/,
             max(decode(a.wagecode, 'W201808038', a.n4, 0)) W201808038 /*CCM二期佣金率_ANP*/,
             max(decode(a.wagecode, 'W201808039', a.n4, 0)) W201808039 /*CCM三期佣金率_ANP*/,
             max(decode(a.wagecode, 'W201808040', a.n4, 0)) W201808040 /*CCM五期佣金率_ANP*/,
             max(decode(a.wagecode, 'W201808041', a.n4, 0)) W201808041 /*CCM四期佣金率_ANP*/,
             max(decode(a.wagecode, 'W201808042', a.n4, 0)) W201808042 /*CCM六期佣金率_ANP*/

             --GWP
            ,
             max(decode(a.wagecode, 'W201808346', a.n4, 0)) W201808346 /*TMR一期佣金率_GWP*/,
             max(decode(a.wagecode, 'W201808347', a.n4, 0)) W201808347 /*TMR二期佣金率_GWP*/,
             max(decode(a.wagecode, 'W201808348', a.n4, 0)) W201808348 /*TMR三期佣金率_GWP*/,
             max(decode(a.wagecode, 'W201808349', a.n4, 0)) W201808349 /*TMR五期佣金率_GWP*/,
             max(decode(a.wagecode, 'W201808350', a.n4, 0)) W201808350 /*TMR四期佣金率_GWP*/,
             max(decode(a.wagecode, 'W201808351', a.n4, 0)) W201808351 /*TMR六期佣金率_GWP*/

            ,
             max(decode(a.wagecode, 'W201808352', a.n4, 0)) W201808352 /*TMS一期佣金率_GWP*/,
             max(decode(a.wagecode, 'W201808353', a.n4, 0)) W201808353 /*TMS二期佣金率_GWP*/,
             max(decode(a.wagecode, 'W201808354', a.n4, 0)) W201808354 /*TMS三期佣金率_GWP*/,
             max(decode(a.wagecode, 'W201808355', a.n4, 0)) W201808355 /*TMS五期佣金率_GWP*/,
             max(decode(a.wagecode, 'W201808356', a.n4, 0)) W201808356 /*TMS四期佣金率_GWP*/,
             max(decode(a.wagecode, 'W201808357', a.n4, 0)) W201808357 /*TMS六期佣金率_GWP*/

            ,
             max(decode(a.wagecode, 'W201808358', a.n4, 0)) W201808358 /*CCM一期佣金率_GWP*/,
             max(decode(a.wagecode, 'W201808359', a.n4, 0)) W201808359 /*CCM二期佣金率_GWP*/,
             max(decode(a.wagecode, 'W201808360', a.n4, 0)) W201808360 /*CCM三期佣金率_GWP*/,
             max(decode(a.wagecode, 'W201808361', a.n4, 0)) W201808361 /*CCM五期佣金率_GWP*/,
             max(decode(a.wagecode, 'W201808362', a.n4, 0)) W201808362 /*CCM四期佣金率_GWP*/,
             max(decode(a.wagecode, 'W201808363', a.n4, 0)) W201808363 /*CCM六期佣金率_GWP*/
             --离职折算比不会大于一 所以取最小
            ,
             min(decode(a.wagecode, 'W201808023', a.n4, 1)) W201808023 /*37 离职折算比例*/

            ,
             sum(decode(a.wagecode, 'W201808061', a.n2, 0)) W201808061 /*  TMR扣佣 */,
             sum(decode(a.wagecode, 'W201808384', a.n2, 0)) W201808384 /*  TMS扣佣  */,
             sum(decode(a.wagecode, 'W201808385', a.n2, 0)) W201808385 /*  CCM扣佣 */
             /*,sum(decode(a.wagecode, 'W000000219', a.n2, 0)) W000000219 1  TMR_payment
              ,sum(decode(a.wagecode, 'W000000220', a.n2, 0)) W000000220   TMS_payment
             ,sum(decode(a.wagecode, 'W000000221', a.n2, 0)) W000000221   CCM_payment
             updated by aline 20170707 SMARTTM-559 离职折算，将TMR_payment薪资项替换成TMR_payment(折算)
             */,
             sum(decode(a.wagecode, 'W201808005', a.n2, 0)) W201808005 /*41  TMR一期佣金_ANP */,
             sum(decode(a.wagecode, 'W201808006', a.n2, 0)) W201808006 /*  TMR二期佣金_ANP  */,
             sum(decode(a.wagecode, 'W201808007', a.n2, 0)) W201808007 /*  TMR三期佣金_ANP  */,
             sum(decode(a.wagecode, 'W201808008', a.n2, 0)) W201808008 /*  TMR四期佣金_ANP */,
             sum(decode(a.wagecode, 'W201808009', a.n2, 0)) W201808009 /*  TMR五期佣金_ANP  */,
             sum(decode(a.wagecode, 'W201808010', a.n2, 0)) W201808010 /*  TMR六期佣金_ANP */

            ,
             sum(decode(a.wagecode, 'W201808011', a.n2, 0)) W201808011 /*7 TMS一期佣金_ANP  */,
             sum(decode(a.wagecode, 'W201808012', a.n2, 0)) W201808012 /*  TMS二期佣金_ANP  */,
             sum(decode(a.wagecode, 'W201808013', a.n2, 0)) W201808013 /*  TMS三期佣金_ANP  */,
             sum(decode(a.wagecode, 'W201808014', a.n2, 0)) W201808014 /*  TMS四期佣金_ANP  */,
             sum(decode(a.wagecode, 'W201808015', a.n2, 0)) W201808015 /*  TMS五期佣金_ANP  */,
             sum(decode(a.wagecode, 'W201808016', a.n2, 0)) W201808016 /*  TMS六期佣金_ANP  */

            ,
             sum(decode(a.wagecode, 'W201808017', a.n2, 0)) W201808017 /*  CCM一期佣金_ANP */,
             sum(decode(a.wagecode, 'W201808018', a.n2, 0)) W201808018 /*  CCM二期佣金_ANP  */,
             sum(decode(a.wagecode, 'W201808019', a.n2, 0)) W201808019 /*  CCM三期佣金_ANP  */,
             sum(decode(a.wagecode, 'W201808020', a.n2, 0)) W201808020 /*  CCM四期佣金_ANP */,
             sum(decode(a.wagecode, 'W201808021', a.n2, 0)) W201808021 /*  CCM五期佣金_ANP  */,
             sum(decode(a.wagecode, 'W201808022', a.n2, 0)) W201808022 /*  CCM六期佣金_ANP */

             --
            ,
             sum(decode(a.wagecode, 'W201808364', a.n2, 0)) W201808364 /*59  TMR一期佣金_GWP */,
             sum(decode(a.wagecode, 'W201808365', a.n2, 0)) W201808365 /*  TMR二期佣金_GWP  */,
             sum(decode(a.wagecode, 'W201808366', a.n2, 0)) W201808366 /*  TMR三期佣金_GWP  */,
             sum(decode(a.wagecode, 'W201808367', a.n2, 0)) W201808367 /*  TMR四期佣金_GWP */,
             sum(decode(a.wagecode, 'W201808368', a.n2, 0)) W201808368 /*  TMR五期佣金_GWP  */,
             sum(decode(a.wagecode, 'W201808369', a.n2, 0)) W201808369 /*  TMR六期佣金_GWP */

            ,
             sum(decode(a.wagecode, 'W201808370', a.n2, 0)) W201808370 /*  TMS一期佣金_GWP  */,
             sum(decode(a.wagecode, 'W201808371', a.n2, 0)) W201808371 /*  TMS二期佣金_GWP  */,
             sum(decode(a.wagecode, 'W201808372', a.n2, 0)) W201808372 /*  TMS三期佣金_GWP  */,
             sum(decode(a.wagecode, 'W201808373', a.n2, 0)) W201808373 /*  TMS四期佣金_GWP  */,
             sum(decode(a.wagecode, 'W201808374', a.n2, 0)) W201808374 /*  TMS五期佣金_GWP  */,
             sum(decode(a.wagecode, 'W201808375', a.n2, 0)) W201808375 /*  TMS六期佣金_GWP  */

            ,
             sum(decode(a.wagecode, 'W201808376', a.n2, 0)) W201808376 /*  CCM一期佣金_GWP */,
             sum(decode(a.wagecode, 'W201808377', a.n2, 0)) W201808377 /*  CCM二期佣金_GWP  */,
             sum(decode(a.wagecode, 'W201808378', a.n2, 0)) W201808378 /*  CCM三期佣金_GWP  */,
             sum(decode(a.wagecode, 'W201808379', a.n2, 0)) W201808379 /*  CCM四期佣金_GWP */,
             sum(decode(a.wagecode, 'W201808380', a.n2, 0)) W201808380 /*  CCM五期佣金_GWP  */,
             sum(decode(a.wagecode, 'W201808381', a.n2, 0)) W201808381 /*  CCM六期佣金_GWP */

             ----
            ,
             sum(decode(a.wagecode, 'W201808043', a.n2, 0)) W201808043 /* 77 TMR一期AMEP_ANP */,
             sum(decode(a.wagecode, 'W201808044', a.n2, 0)) W201808044 /*  TMR二期AMEP_ANP  */,
             sum(decode(a.wagecode, 'W201808045', a.n2, 0)) W201808045 /*  TMR三期AMEP_ANP  */,
             sum(decode(a.wagecode, 'W201808046', a.n2, 0)) W201808046 /*  TMR四期AMEP_ANP */,
             sum(decode(a.wagecode, 'W201808047', a.n2, 0)) W201808047 /*  TMR五期AMEP_ANP  */,
             sum(decode(a.wagecode, 'W201808048', a.n2, 0)) W201808048 /*  TMR六期AMEP_ANP */,
             sum(decode(a.wagecode, 'W201808049', a.n2, 0)) W201808049 /* TMS一期AMEP_ANP  */,
             sum(decode(a.wagecode, 'W201808050', a.n2, 0)) W201808050 /*  TMS二期AMEP_ANP  */,
             sum(decode(a.wagecode, 'W201808051', a.n2, 0)) W201808051 /*  TMS三期AMEP_ANP  */,
             sum(decode(a.wagecode, 'W201808052', a.n2, 0)) W201808052 /*  TMS四期AMEP_ANP  */,
             sum(decode(a.wagecode, 'W201808053', a.n2, 0)) W201808053 /*  TMS五期AMEP_ANP  */,
             sum(decode(a.wagecode, 'W201808054', a.n2, 0)) W201808054 /*  TMS六期AMEP_ANP  */,
             sum(decode(a.wagecode, 'W201808055', a.n2, 0)) W201808055 /*89  CCM一期AMEP_ANP */,
             sum(decode(a.wagecode, 'W201808056', a.n2, 0)) W201808056 /*  CCM二期AMEP_ANP  */,
             sum(decode(a.wagecode, 'W201808057', a.n2, 0)) W201808057 /*  CCM三期AMEP_ANP  */,
             sum(decode(a.wagecode, 'W201808058', a.n2, 0)) W201808058 /*  CCM四期AMEP_ANP */,
             sum(decode(a.wagecode, 'W201808059', a.n2, 0)) W201808059 /*  CCM五期AMEP_ANP  */,
             sum(decode(a.wagecode, 'W201808060', a.n2, 0)) W201808060 /*  CCM六期AMEP_ANP */
             --GWP
            ,
             sum(decode(a.wagecode, 'W201808328', a.n2, 0)) W201808328 /*  TMR一期AMEP_GWP */,
             sum(decode(a.wagecode, 'W201808329', a.n2, 0)) W201808329 /*  TMR二期AMEP_GWP  */,
             sum(decode(a.wagecode, 'W201808330', a.n2, 0)) W201808330 /*  TMR三期AMEP_GWP  */,
             sum(decode(a.wagecode, 'W201808331', a.n2, 0)) W201808331 /*  TMR四期AMEP_GWP */,
             sum(decode(a.wagecode, 'W201808332', a.n2, 0)) W201808332 /*  TMR五期AMEP_GWP  */,
             sum(decode(a.wagecode, 'W201808333', a.n2, 0)) W201808333 /*  TMR六期AMEP_GWP */

            ,
             sum(decode(a.wagecode, 'W201808334', a.n2, 0)) W201808334 /* TMS一期AMEP_GWP  */,
             sum(decode(a.wagecode, 'W201808335', a.n2, 0)) W201808335 /*  TMS二期AMEP_GWP  */,
             sum(decode(a.wagecode, 'W201808336', a.n2, 0)) W201808336 /*  TMS三期AMEP_GWP  */,
             sum(decode(a.wagecode, 'W201808337', a.n2, 0)) W201808337 /*  TMS四期AMEP_GWP  */,
             sum(decode(a.wagecode, 'W201808338', a.n2, 0)) W201808338 /*  TMS五期AMEP_GWP  */,
             sum(decode(a.wagecode, 'W201808339', a.n2, 0)) W201808339 /*  TMS六期AMEP_GWP  */

            ,
             sum(decode(a.wagecode, 'W201808340', a.n2, 0)) W201808340 /*  CCM一期AMEP_GWP */,
             sum(decode(a.wagecode, 'W201808341', a.n2, 0)) W201808341 /*  CCM二期AMEP_GWP  */,
             sum(decode(a.wagecode, 'W201808342', a.n2, 0)) W201808342 /*  CCM三期AMEP_GWP  */,
             sum(decode(a.wagecode, 'W201808343', a.n2, 0)) W201808344 /*  CCM四期AMEP_GWP */,
             sum(decode(a.wagecode, 'W201808344', a.n2, 0)) W201808344 /*  CCM五期AMEP_GWP  */,
             sum(decode(a.wagecode, 'W201808345', a.n2, 0)) W201808345 /*  CCM六期AMEP_GWP */,
             min(decode(a.wagecode, 'W201808386', a.n2, 99999999)) W201808386 /*  上限金额 */

        from lrindexinfo a
       where a.indextype = '01'
         and a.branchtype = '1'
         and a.mainindexflag = 'Y'
         and a.wageno = cIndexCalNo
         and a.basecode = (select codename
                             from ldcode a
                            where codetype = 'lrbase_TM'
                              and code = 'wage')
         and a.managecom = cManageCom
       group by a.wageno,
                a.agentcode,
                a.agentgrade,
                a.branchtype,
                a.branchtype2,
                a.managecom,
                a.agentgroup,
                a.branchattr,
                a.branchseries;

  /*   暂时去掉离职缓薪功能  2020-09-04
    --当月离职的人缓发
    update tmwage a
       set a.f08 = to_char(add_Months(to_date(a.indexcalno, 'yyyymm'), 3),
                           'yyyymm')
     where a.indexcalno = cIndexCalNo
       and exists (select 1
              from TMSALARYAGENTC t
             where a.agentcode = t.agentcode
               and a.indexcalno = t.bakmonth
               and t.agentstate = '03'
               and t.dimissionmonth = cIndexCalNo);
    --次月离职的人缓发
    update tmwage a
       set a.f08 = to_char(add_Months(to_date(a.indexcalno, 'yyyymm'), 2),
                           'yyyymm')
     where a.indexcalno = cIndexCalNo
       and exists
     (select 1
              from TMSALARYAGENTC t
             where a.agentcode = t.agentcode
                  --and t.managecom = cManageCom
               and a.indexcalno = t.bakmonth
               and t.agentstate = '03'
               and to_char(add_Months(to_date(t.dimissionmonth, 'yyyymm'), 1),
                           'yyyymm') = a.indexcalno);
    --第三个月离职的人缓发
    update tmwage a
       set a.f08 = to_char(add_Months(to_date(a.indexcalno, 'yyyymm'), 1),
                           'yyyymm')
     where a.indexcalno = cIndexCalNo
       and exists
     (select 1
              from TMSALARYAGENTC t
             where a.agentcode = t.agentcode
                  --and t.managecom = cManageCom
               and a.indexcalno = t.bakmonth
               and t.agentstate = '03'
               and to_char(add_Months(to_date(t.dimissionmonth, 'yyyymm'), 2),
                           'yyyymm') = cIndexCalNo);
  */


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
     where t.indexcalno = cIndexCalNo
       and t.managecom = cManageCom;

    --回写计算保费
    --GWP   TMR
    merge into tmwage x
    using (select a.tmrcode,
                  sum(decode(a.commissioncount, '1', a.calmoney)) as money1,
                  sum(decode(a.commissioncount, '2', a.calmoney)) as money2,
                  sum(decode(a.commissioncount, '3', a.calmoney)) as money3
             from tmcommission a
            where a.iswage = 'Y'
              and a.isjumppoint = 'Y'
              and a.managecom = cManageCom
              and a.calbasetype = 'GWP'
              and a.commissionmonth = cIndexCalNo
            group by a.tmrcode, a.commissioncount) t
    on (t.tmrcode = x.agentcode and x.agentgrade = 'TMR' and x.indexcalno = cIndexCalNo and x.managecom = cManageCom)
    when matched then
      update set x.w121 = t.money1, x.w122 = t.money2, x.w123 = t.money3;

    --GWP   TMS
    merge into tmwage x
    using (select a.tmscode,
                  sum(decode(a.commissioncount, '1', a.calmoney)) as money1,
                  sum(decode(a.commissioncount, '2', a.calmoney)) as money2,
                  sum(decode(a.commissioncount, '3', a.calmoney)) as money3
             from tmcommission a
            where a.iswage = 'Y'
              and a.isjumppoint = 'Y'
              and a.managecom = cManageCom
              and a.calbasetype = 'GWP'
              and a.commissionmonth = cIndexCalNo
            group by a.tmscode, a.commissioncount) t
    on (t.tmscode = x.agentcode and x.agentgrade = 'TMR' and x.indexcalno = cIndexCalNo and x.managecom = cManageCom)
    when matched then
      update set x.w121 = t.money1, x.w122 = t.money2, x.w123 = t.money3;

    --GWP   TMS
    merge into tmwage x
    using (select a.ccmcode,
                  sum(decode(a.commissioncount, '1', a.calmoney)) as money1,
                  sum(decode(a.commissioncount, '2', a.calmoney)) as money2,
                  sum(decode(a.commissioncount, '3', a.calmoney)) as money3
             from tmcommission a
            where a.iswage = 'Y'
              and a.isjumppoint = 'Y'
              and a.managecom = cManageCom
              and a.calbasetype = 'GWP'
              and a.commissionmonth = cIndexCalNo
            group by a.ccmcode, a.commissioncount) t
    on (t.ccmcode = x.agentcode and x.agentgrade = 'TMR' and x.indexcalno = cIndexCalNo and x.managecom = cManageCom)
    when matched then
      update set x.w121 = t.money1, x.w122 = t.money2, x.w123 = t.money3;

    --ANP TMR
    merge into tmwage x
    using (select a.tmrcode, sum(a.calmoney) as money1
             from tmcommission a
            where a.iswage = 'Y'
              and a.isjumppoint = 'Y'
              and a.managecom = cManageCom
              and a.calbasetype = 'ANP'
              and a.commissionmonth = cIndexCalNo
              and a.commissioncount = '1'
            group by a.tmrcode) t
    on (t.tmrcode = x.agentcode and x.agentgrade = 'TMR' and x.indexcalno = cIndexCalNo and x.managecom = cManageCom)
    when matched then
      update set x.W115 = t.money1;

    --ANP TMS
    merge into tmwage x
    using (select a.tmScode, sum(a.calmoney) as money1
             from tmcommission a
            where a.iswage = 'Y'
              and a.isjumppoint = 'Y'
              and a.managecom = cManageCom
              and a.calbasetype = 'ANP'
              and a.commissionmonth = cIndexCalNo
              and a.commissioncount = '1'
            group by a.tmScode) t
    on (t.tmScode = x.agentcode and x.agentgrade = 'TMR' and x.indexcalno = cIndexCalNo and x.managecom = cManageCom)
    when matched then
      update set x.W115 = t.money1;

    --ANP CCM
    merge into tmwage x
    using (select a.CCMcode, sum(a.calmoney) as money1
             from tmcommission a
            where a.iswage = 'Y'
              and a.isjumppoint = 'Y'
              and a.managecom = cManageCom
              and a.calbasetype = 'ANP'
              and a.commissionmonth = cIndexCalNo
              and a.commissioncount = '1'
            group by a.CCMcode) t
    on (t.CCMcode = x.agentcode and x.agentgrade = 'TMR' and x.indexcalno = cIndexCalNo and x.managecom = cManageCom)
    when matched then
      update set x.W115 = t.money1;

    /*      update  tmwage t
    set W115=TMWAGECAL.getCalMoney(t.managecom,t.agentcode,t.indexcalno,t.agentgrade,1,'ANP'),
        W116=TMWAGECAL.getCalMoney(t.managecom,t.agentcode,t.indexcalno,t.agentgrade,2,'ANP'),
        W117=TMWAGECAL.getCalMoney(t.managecom,t.agentcode,t.indexcalno,t.agentgrade,3,'ANP'),
        W118=TMWAGECAL.getCalMoney(t.managecom,t.agentcode,t.indexcalno,t.agentgrade,4,'ANP'),
        W119=TMWAGECAL.getCalMoney(t.managecom,t.agentcode,t.indexcalno,t.agentgrade,5,'ANP'),
        W120=TMWAGECAL.getCalMoney(t.managecom,t.agentcode,t.indexcalno,t.agentgrade,6,'ANP'),
        W121=TMWAGECAL.getCalMoney(t.managecom,t.agentcode,t.indexcalno,t.agentgrade,1,'GWP'),
        W122=TMWAGECAL.getCalMoney(t.managecom,t.agentcode,t.indexcalno,t.agentgrade,2,'GWP'),
        W123=TMWAGECAL.getCalMoney(t.managecom,t.agentcode,t.indexcalno,t.agentgrade,3,'GWP'),
        W124=TMWAGECAL.getCalMoney(t.managecom,t.agentcode,t.indexcalno,t.agentgrade,4,'GWP'),
        W125=TMWAGECAL.getCalMoney(t.managecom,t.agentcode,t.indexcalno,t.agentgrade,5,'GWP'),
        W126=TMWAGECAL.getCalMoney(t.managecom,t.agentcode,t.indexcalno,t.agentgrade,6,'GWP')
    where  t.indexcalno = cIndexCalNo
       and t.managecom = cManageCom
      \* and not exists(select 1 from tmwageagentc a where a.bakmonth = t.indexcalno
       and a.agentcode = t.agentcode
       and a.managecom = t.managecom
       and a.posttype = t.agentgrade
       and a.agentgrade in('ManagementTrainee','ManagementTrainee(P)','TMS-Coach','TMS-Coach(P)'))*\;*/

    commit;

    return 'OK';
  Exception
    When Others Then
      --Return 'ERROR';
      rollback;
      Return sqlcode || sqlerrm;
  end;
