
function calWageTotal(cIndexCalNo varchar2) return varchar2 IS
  pragma autonomous_transaction;
  --tMinComm    varchar2(20);
begin

  --回写佣金表的rate  __TMR
  update tmcommission t
     set (t.tmrcommrate, t.modifydate, t.modifytime) =
         (select case
                   when t.calbasetype = 'ANP' THEN
                    decode(t.commissioncount,
                           1,
                           max(a.w1),
                           2,
                           max(w2),
                           3,
                           max(w3),
                           4,
                           max(w4),
                           5,
                           max(w5),
                           6,
                           max(w6),
                           NULL)
                   when t.calbasetype = 'GWP' then
                    decode(t.commissioncount,
                           1,
                           max(a.w19),
                           2,
                           max(w20),
                           3,
                           max(w21),
                           4,
                           max(w22),
                           5,
                           max(w23),
                           6,
                           max(w24),
                           NULL)
                   else
                    NULL
                 end,
                 basic.CurrentDate,
                 basic.CurrentTime
            from tmwage a
           where a.agentcode = t.tmrcode
             and a.indexcalno = t.commissionmonth
             and a.agentgrade = 'TMR'
             and a.managecom = t.managecom)
   where t.commissionmonth = cIndexCalNo
     and t.isjumppoint = 'Y'
     and exists (select 1
            from tmwage x
           where x.agentcode = t.tmrcode
             and x.managecom = t.managecom
             and x.agentgrade = 'TMR'
             and x.indexcalno = t.commissionmonth);
  --人员佣金率类型
  update tmcommission t
     set (t.tmragentcomtype, t.modifydate, t.modifytime) =
         (select case
                   when t.calbasetype = 'ANP' THEN
                    a.f06
                   when t.calbasetype = 'GWP' then
                    a.f07
                   else
                    NULL
                 end,
                 basic.CurrentDate,
                 basic.CurrentTime
            from tmwage a
           where a.agentcode = t.tmrcode
             and a.indexcalno = t.commissionmonth
             and a.agentgrade = 'TMR'
             and a.managecom = t.managecom)
   where t.commissionmonth = cIndexCalNo
     and t.isjumppoint = 'Y'
     and exists (select 1
            from tmwage x
           where x.agentcode = t.tmrcode
             and x.managecom = t.managecom
             and x.agentgrade = 'TMR'
             and x.indexcalno = t.commissionmonth);
  --回写佣金表的rate  __TMS
  update tmcommission t
     set (t.tmscommrate, t.modifydate, t.modifytime) =
         (select case
                   when t.calbasetype = 'ANP' THEN
                    decode(t.commissioncount,
                           1,
                           max(a.w7),
                           2,
                           max(w8),
                           3,
                           max(w9),
                           4,
                           max(w10),
                           5,
                           max(w11),
                           6,
                           max(w12),
                           NULL)
                   when t.calbasetype = 'GWP' then
                    decode(t.commissioncount,
                           1,
                           max(a.w25),
                           2,
                           max(w26),
                           3,
                           max(w27),
                           4,
                           max(w28),
                           5,
                           max(w29),
                           6,
                           max(w30),
                           NULL)
                   else
                    NULL
                 end,
                 basic.CurrentDate,
                 basic.CurrentTime
            from tmwage a
           where a.agentcode = t.tmscode
             and a.indexcalno = t.commissionmonth
             and a.agentgrade = 'TMS'
             and a.managecom = t.managecom)
   where t.commissionmonth = cIndexCalNo
     and t.isjumppoint = 'Y'
     and exists (select 1
            from tmwage x
           where x.agentcode = t.tmscode
             and x.managecom = t.managecom
             and x.agentgrade = 'TMS'
             and x.indexcalno = t.commissionmonth);
  --人员佣金率类型
  update tmcommission t
     set (t.tmsagentcomtype, t.modifydate, t.modifytime) =
         (select case
                   when t.calbasetype = 'ANP' THEN
                    a.f06
                   when t.calbasetype = 'GWP' then
                    a.f07
                   else
                    NULL
                 end,
                 basic.CurrentDate,
                 basic.CurrentTime
            from tmwage a
           where a.agentcode = t.tmscode
             and a.indexcalno = t.commissionmonth
             and a.agentgrade = 'TMS'
             and a.managecom = t.managecom)
   where t.commissionmonth = cIndexCalNo
     and t.isjumppoint = 'Y'
     and exists (select 1
            from tmwage x
           where x.agentcode = t.tmscode
             and x.managecom = t.managecom
             and x.agentgrade = 'TMS'
             and x.indexcalno = t.commissionmonth);
  --回写佣金表的rate  __CCM

  update tmcommission t
     set (t.ccmcommrate, t.modifydate, t.modifytime) =
         (select case
                   when t.calbasetype = 'ANP' THEN
                    decode(t.commissioncount,
                           1,
                           max(a.w13),
                           2,
                           max(w14),
                           3,
                           max(w15),
                           4,
                           max(w16),
                           5,
                           max(w17),
                           6,
                           max(w18),
                           NULL)
                   when t.calbasetype = 'GWP' then
                    decode(t.commissioncount,
                           1,
                           max(a.w31),
                           2,
                           max(w32),
                           3,
                           max(w33),
                           4,
                           max(w34),
                           5,
                           max(w35),
                           6,
                           max(w36),
                           NULL)
                   else
                    NULL
                 end,
                 basic.CurrentDate,
                 basic.CurrentTime
            from tmwage a
           where a.agentcode = t.ccmcode
             and a.indexcalno = t.commissionmonth
             and a.agentgrade = 'CCM'
             and a.managecom = t.managecom)
   where t.commissionmonth = cIndexCalNo
     and t.isjumppoint = 'Y'
     and exists (select 1
            from tmwage x
           where x.agentcode = t.ccmcode
             and x.managecom = t.managecom
             and x.agentgrade = 'CCM'
             and x.indexcalno = t.commissionmonth);
  --人员佣金率类型
  update tmcommission t
     set (t.ccmagentcomtype, t.modifydate, t.modifytime) =
         (select case
                   when t.calbasetype = 'ANP' THEN
                    a.f06
                   when t.calbasetype = 'GWP' then
                    a.f07
                   else
                    NULL
                 end,
                 basic.CurrentDate,
                 basic.CurrentTime
            from tmwage a
           where a.agentcode = t.ccmcode
             and a.indexcalno = t.commissionmonth
             and a.agentgrade = 'CCM'
             and a.managecom = t.managecom)
   where t.commissionmonth = cIndexCalNo
     and t.isjumppoint = 'Y'
     and exists (select 1
            from tmwage x
           where x.agentcode = t.ccmcode
             and x.managecom = t.managecom
             and x.agentgrade = 'CCM'
             and x.indexcalno = t.commissionmonth);
  --刷新保单中TMS CCM人员为空的佣金率类型
  --处理当月正常算薪的保单 即 isjumppoint 为Y的保单
  --处理当月薪资计算后又上传扣佣保单的数据(当月计当月扣 调动主管的人员佣金率不为空)
  --isjumppoint  = 'N'  不参与跳点的
  --istrace = 'Y'  为Y 是当月计当月扣，或者存在当月计的
  update tmcommission t
     set t.tmscommrate     = null,
         t.tmsagentcomtype = null,
         t.modifydate      = basic.CurrentDate,
         t.modifytime      = basic.CurrentTime
   where t.commissionmonth = cIndexCalNo
     and t.isjumppoint = 'N'
     and t.istrace = 'Y'
     and t.tmscode is null;

  update tmcommission t
     set t.tmscommrate     = null,
         t.tmsagentcomtype = null,
         t.modifydate      = basic.CurrentDate,
         t.modifytime      = basic.CurrentTime
   where t.commissionmonth = cIndexCalNo
     and t.isjumppoint = 'Y'
     and t.tmscode is null;

  update tmcommission t
     set t.ccmcommrate     = null,
         t.ccmagentcomtype = null,
         t.modifydate      = basic.CurrentDate,
         t.modifytime      = basic.CurrentTime
   where t.commissionmonth = cIndexCalNo
     and t.isjumppoint = 'N'
     and t.istrace = 'Y'
     and t.ccmcode is null;

  update tmcommission t
     set t.ccmcommrate     = null,
         t.ccmagentcomtype = null,
         t.modifydate      = basic.CurrentDate,
         t.modifytime      = basic.CurrentTime
   where t.commissionmonth = cIndexCalNo
     and t.isjumppoint = 'Y'
     and t.ccmcode is null;

  --重算先删除
  delete from tmwagetotal t where t.wageno = cIndexCalNo;
  commit;

  --汇总
  insert into tmwagetotal
    (WAGENO,
     PAYROLLMONTH,
     AGENTCODE,
     MANAGECOM,
     AGENTGRADE,
     TMRTYPE,
     TMSTYPE,
     COMMISSIONLEVEL,
     ORIGINAL_COMMISSION_BASIC,
     ORIGINAL_COMMISSION,
     PAYMENT_WITHDRAW_Y,
     PAYMENT_WITHDRAW_N,
     PROTECTION_BONUS_N,
     PROTECTION_BONUS_O,
     FULLWORKPRIZE,
     CALLWORKPRIZE,
     CONVERSIONRATEPRIZE,
     COACHPRIZE,
     LPEDORPRIZE,
     BASICMTVPRIZE,
     SPECIALMTVPRIZE,
     OTHERPRIZE,
     KeyCaseDeduct,
     ComplaintDeduct,
     SupervisionDeduct,
     PAYMENT,
     MAXMONEY,
     SALEMANAGEMENTPRIZE,
     PROTECTION_BONUSTYPE_O,
     BEFORE_PROMOTION_COMMISSION,
     AFTER_PROMOTION_COMMISSION)
    select t.indexcalno as wageno,
           t.f08 as Payrollmonth,
           t.agentcode as agentcode,
           t1.managecom as managecom,
           t1.agentgrade as agentgrade,
           '' as tmrtype,
           '' as tmstype,
           '' as CommissionLevel,
           0 as Original_Commission_basic, --基本佣金汇总
           0 as Original_Commission,
           0 as Payment_Withdraw_Y, --参与跳点扣佣金额
           0 as Payment_Withdraw_N, --不参与跳点扣佣金额
           0 as Protection_Bonus_N, --新人保底佣金
           0 as Protection_Bonus_O, --其他保底佣金
           0 as FullWorkPrize, --全勤奖金
           0 as CallWorkPrize, --通时奖金
           0 as ConversionRatePrize, --转换率奖金
           0 as CoachPrize, --Coach奖金
           0 as LPEDORPrize, --保全小组佣金
           0 as BasicMTVPrize, --基本MTV奖金
           0 as SpecialMTVPrize, --特殊MTV奖金奖金
           0 as otherPrize, --其它类型奖金
           0 as KeyCaseDeduct,
           0 as ComplaintDeduct,
           0 as SupervisionDeduct,
           0 as Payment, --Payment
           max(t.w114) as maxmoney,
           0 as SaleManagementPrize, --销售管理层奖金
           '' as PROTECTION_BONUSTYPE_O,
           0 as BEFORE_PROMOTION_COMMISSION,
           0 as AFTER_PROMOTION_COMMISSION
      from tmwage t, TMSALARYAGENTC t1
     where t.indexcalno = cIndexCalNo
       and t.agentcode = t1.agentcode
       and t.indexcalno = t1.bakmonth
       and t.branchtype = '1'
    /* and not exists (select *
     from tmwagetotal tt
    where tt.wageno = t.indexcalno
      and t.agentcode = tt.agentcode
      and tt.managecom = t.managecom
      and (select a.codealias from ldcode a where a.codetype = 'f_tmagentgrade' and a.code=tt.agentgrade)= t.agentgrade)*/
     group by t.indexcalno, t.agentcode, t1.managecom, t1.agentgrade, t.f08;

  --TMR  AMEP
  update tmwagetotal tt
     set (tt.Original_Commission_basic, tt.Original_Commission) =
         (select sum((t.w41 + t.w42 + t.w43 + t.w44 + t.w45 + t.w46) +
                     (t.w59 + t.w60 + t.w61 + t.w62 + t.w63 + t.w64) * w37 +
                     (t.w47 + t.w48 + t.w49 + t.w50 + t.w51 + t.w52) +
                     (t.w65 + t.w66 + t.w67 + t.w68 + t.w69 + t.w70) * w37 +
                     (t.w53 + t.w54 + t.w55 + t.w56 + t.w57 + t.w58) +
                     (t.w71 + t.w72 + t.w73 + t.w74 + t.w75 + t.w76) * w37),
                 sum((t.w41 + t.w42 + t.w43 + t.w44 + t.w45 + t.w46) * w100 +
                     (t.w59 + t.w60 + t.w61 + t.w62 + t.w63 + t.w64) * w100 * w37 +
                     (t.w47 + t.w48 + t.w49 + t.w50 + t.w51 + t.w52) * w100 +
                     (t.w65 + t.w66 + t.w67 + t.w68 + t.w69 + t.w70) * w100 * w37 +
                     (t.w53 + t.w54 + t.w55 + t.w56 + t.w57 + t.w58) * w100 +
                     (t.w71 + t.w72 + t.w73 + t.w74 + t.w75 + t.w76) * w100 * w37)
            from tmwage t
           where t.indexcalno = tt.wageno
             and tt.agentcode = t.agentcode)
   where tt.wageno = cIndexCalNo;
  /*  TMR扣佣  不参与跳点的 离职人员只算离职三月及之前计佣的保单发生的跨月扣佣*/
  update tmwagetotal t
     set t.Payment_Withdraw_N =
         (select nvl(sum(a.tmramep * a.tmrcommrate * a.tmrrevisitrate), 0) +
                 nvl(t.Payment_Withdraw_N, 0)
            from tmcommission a
           where a.iswage = 'Y'
             and a.isjumppoint = 'N'
             and a.commissionmonth = cIndexCalNo
             and a.tmrcode = t.agentcode
             and a.commissionmonth = t.wageno
             and exists (select 1
                    from tmcommission c, tmsalaryagentc d
                   where 1 = 1
                     and c.tmrcode = d.agentcode
                     and d.bakmonth = cIndexCalNo
                     and c.commissionsn = a.bak2
                     and d.agentstate = '03'
                     and c.commissionmonth <= d.dimissionmonth)
             and a.istrace = 'N')
   where t.wageno = cIndexCalNo
     and exists (select 1
            from tmwageagentc c
           where c.agentcode = t.agentcode
             and c.bakmonth = t.wageno
             and c.posttype = 'TMR'
             and c.state = '03');
  /*  TMR扣佣  不参与跳点的 在职人员保单发生的跨月扣佣*/
  update tmwagetotal t
     set t.Payment_Withdraw_N =

         (select nvl(sum(
         -- tmr amep
         a.tmramep *
         -- tmr 佣金率
          a.tmrcommrate *
         -- tmr打折系数
         a.tmrrevisitrate), 0) +
                 nvl(t.Payment_Withdraw_N, 0)
            from tmcommission a
           where a.iswage = 'Y'
             and a.isjumppoint = 'N'
             and a.commissionmonth = cIndexCalNo
             and a.tmrcode = t.agentcode
             and a.commissionmonth = t.wageno
             and a.istrace = 'N')
   where t.wageno = cIndexCalNo
     and exists (select 1
            from tmwageagentc c
           where c.agentcode = t.agentcode
             and c.bakmonth = t.wageno
             and c.posttype = 'TMR'
             and c.state <> '03');

  /*  TMS扣佣  不参与跳点的 离职人员只算离职三月及之前计佣的保单发生的跨月扣佣*/
  update tmwagetotal t
     set t.Payment_Withdraw_N =
         (select nvl(sum(a.tmsamep * a.tmscommrate * a.tmsrevisitrate), 0) +
                 nvl(t.Payment_Withdraw_N, 0)
            from tmcommission a
           where a.iswage = 'Y'
             and a.isjumppoint = 'N'
             and a.commissionmonth = cIndexCalNo
             and a.tmscode = t.agentcode
             and a.commissionmonth = t.wageno
             and exists (select 1
                    from tmcommission c, tmsalaryagentc d
                   where 1 = 1
                     and c.tmscode = d.agentcode
                     and d.bakmonth = cIndexCalNo
                     and c.commissionsn = a.bak2
                     and d.agentstate = '03'
                     and c.commissionmonth <= d.dimissionmonth)
             and a.istrace = 'N')
   where t.wageno = cIndexCalNo
     and exists (select 1
            from tmwageagentc c
           where c.agentcode = t.agentcode
             and c.bakmonth = t.wageno
             and c.posttype = 'TMS'
             and c.state = '03');
  /*  TMS扣佣  不参与跳点的 在职人员保单发生的跨月扣佣*/
  update tmwagetotal t
     set t.Payment_Withdraw_N =
         (select nvl(sum(a.tmsamep * a.tmscommrate * a.tmsrevisitrate), 0) +
                 nvl(t.Payment_Withdraw_N, 0)
            from tmcommission a
           where a.iswage = 'Y'
             and a.isjumppoint = 'N'
             and a.commissionmonth = cIndexCalNo
             and a.tmscode = t.agentcode
             and a.commissionmonth = t.wageno
             and a.istrace = 'N')
   where t.wageno = cIndexCalNo
     and exists (select 1
            from tmwageagentc c
           where c.agentcode = t.agentcode
             and c.bakmonth = t.wageno
             and c.posttype = 'TMS'
             and c.state <> '03');
  /*  CCM扣佣  不参与跳点的 离职人员只算离职三月及之前计佣的保单发生的跨月扣佣*/
  update tmwagetotal t
     set t.Payment_Withdraw_N =
         (select nvl(sum(a.ccmamep * a.ccmcommrate * a.ccmrevisitrate), 0) +
                 nvl(t.Payment_Withdraw_N, 0)
            from tmcommission a
           where a.iswage = 'Y'
             and a.isjumppoint = 'N'
             and a.commissionmonth = cIndexCalNo
             and a.ccmcode = t.agentcode
             and a.commissionmonth = t.wageno
             and exists (select 1
                    from tmcommission c, tmsalaryagentc d
                   where 1 = 1
                     and c.ccmcode = d.agentcode
                     and d.bakmonth = cIndexCalNo
                     and c.commissionsn = a.bak2
                     and d.agentstate = '03'
                     and c.commissionmonth <= d.dimissionmonth)
             and a.istrace = 'N')
   where t.wageno = cIndexCalNo
     and exists (select 1
            from tmwageagentc c
           where c.agentcode = t.agentcode
             and c.bakmonth = t.wageno
             and c.posttype = 'CCM'
             and c.state = '03');
  /*  CCM扣佣  不参与跳点的 在职人员保单发生的跨月扣佣*/
  update tmwagetotal t
     set t.Payment_Withdraw_N =
         (select nvl(sum(a.ccmamep * a.ccmcommrate * a.ccmrevisitrate), 0) +
                 nvl(t.Payment_Withdraw_N, 0)
            from tmcommission a
           where a.iswage = 'Y'
             and a.isjumppoint = 'N'
             and a.commissionmonth = cIndexCalNo
             and a.ccmcode = t.agentcode
             and a.commissionmonth = t.wageno
             and a.istrace = 'N')
   where t.wageno = cIndexCalNo
     and exists (select 1
            from tmwageagentc c
           where c.agentcode = t.agentcode
             and c.bakmonth = t.wageno
             and c.posttype = 'CCM'
             and c.state <> '03');
  /*  扣佣  参与跳点的*/
  update tmwagetotal t
     set t.Payment_Withdraw_Y =
         (select sum(a.tmramep * a.tmrcommrate * a.tmrrevisitrate) +
                 nvl(t.Payment_Withdraw_Y, 0)
            from tmcommission a
           where a.iswage = 'Y'
             and a.isjumppoint = 'Y'
             and a.tmramep < 0
             and a.commissionmonth = cIndexCalNo
             and a.tmrcode = t.agentcode
             and a.commissionmonth = t.wageno)
   where t.wageno = cIndexCalNo;
  commit;
  update tmwagetotal t
     set t.Payment_Withdraw_Y =
         (select nvl(sum(a.tmsamep * a.tmscommrate * a.tmsrevisitrate), 0) +
                 nvl(t.Payment_Withdraw_Y, 0)
            from tmcommission a
           where a.iswage = 'Y'
             and a.isjumppoint = 'Y'
             and a.tmsamep < 0
             and a.commissionmonth = cIndexCalNo
             and a.tmscode = t.agentcode
             and a.commissionmonth = t.wageno)
   where t.wageno = cIndexCalNo;
  commit;
  update tmwagetotal t
     set t.Payment_Withdraw_Y =
         (select nvl(sum(a.ccmamep * a.ccmcommrate * a.ccmrevisitrate), 0) +
                 nvl(t.Payment_Withdraw_Y, 0)
            from tmcommission a
           where a.iswage = 'Y'
             and a.isjumppoint = 'Y'
             and a.ccmamep < 0
             and a.commissionmonth = cIndexCalNo
             and a.ccmcode = t.agentcode
             and a.commissionmonth = t.wageno)
   where t.wageno = cIndexCalNo;
  commit;
  update tmwagetotal t
     set t.Payment_Withdraw_N = 0
   where t.wageno = cIndexCalNo
     and t.Payment_Withdraw_N is null;

  --扣佣翻倍
  update tmwagetotal tt
     set (Payment_Withdraw_N) =
         (select Payment_Withdraw_N * max(t.w100)
            from tmwage t
           where t.indexcalno = tt.wageno
             and tt.agentcode = t.agentcode
           group by t.agentcode)
   where tt.wageno = cIndexCalNo;

  update tmwagetotal tt
     set (Payment_Withdraw_Y) =
         (select Payment_Withdraw_Y * max(t.w100)
            from tmwage t
           where t.indexcalno = tt.wageno
             and tt.agentcode = t.agentcode
           group by t.agentcode)
   where tt.wageno = cIndexCalNo;

  insert into tmwagetotal
    (WAGENO,
     PAYROLLMONTH,
     AGENTCODE,
     MANAGECOM,
     AGENTGRADE,
     TMRTYPE,
     TMSTYPE,
     COMMISSIONLEVEL,
     ORIGINAL_COMMISSION_BASIC,
     ORIGINAL_COMMISSION,
     PAYMENT_WITHDRAW_Y,
     PAYMENT_WITHDRAW_N,
     PROTECTION_BONUS_N,
     PROTECTION_BONUS_O,
     FULLWORKPRIZE,
     CALLWORKPRIZE,
     CONVERSIONRATEPRIZE,
     COACHPRIZE,
     LPEDORPRIZE,
     BASICMTVPRIZE,
     SPECIALMTVPRIZE,
     OTHERPRIZE,
     PAYMENT,
     MAXMONEY,
     SALEMANAGEMENTPRIZE,
     PROTECTION_BONUSTYPE_O,
     BEFORE_PROMOTION_COMMISSION,
     AFTER_PROMOTION_COMMISSION,
     KEYCASEDEDUCT,
     COMPLAINTDEDUCT,
     SUPERVISIONDEDUCT,
     MAKEDATE,
     MAKETIME)
    select t.bakmonth as wageno,
           t.bakmonth as Payrollmonth,
           t.agentcode as agentcode,
           t.managecom as managecom,
           t.agentgrade as agentgrade,
           '' as tmrtype,
           '' as tmstype,
           '' as CommissionLevel,
           0 as Original_Commission_basic, --基本佣金汇总
           0 as Original_Commission,
           0 as Payment_Withdraw_Y, --参与跳点扣佣金额
           0 as Payment_Withdraw_N, --不参与跳点扣佣金额
           0 as Protection_Bonus_N, --新人保底佣金
           0 as Protection_Bonus_O, --其他保底佣金
           0 as FullWorkPrize, --全勤奖金
           0 as CallWorkPrize, --通时奖金
           0 as ConversionRatePrize, --转换率奖金
           0 as CoachPrize, --Coach奖金
           0 as LPEDORPrize, --保全小组佣金
           0 as BasicMTVPrize, --基本MTV奖金
           0 as SpecialMTVPrize, --特殊MTV奖金奖金
           0 as otherPrize, --其它类型奖金
           0 as Payment, --Payment
           9999999.00 as maxmoney,
           0 as SaleManagementPrize, --销售管理层奖金
           '' as PROTECTION_BONUSTYPE_O,
           0 as BEFORE_PROMOTION_COMMISSION,
           0 as AFTER_PROMOTION_COMMISSION,
           0 as KEYCASEDEDUCT,
           0 as COMPLAINTDEDUCT,
           0 as SUPERVISIONDEDUCT,
           basic.CurrentDate,
           basic.CurrentTime
      from TMSALARYAGENTC t
     where t.bakmonth = cIndexCalNo
       and (t.agentstate = '01' or
           (t.agentstate <> '01' and
           to_char(add_months(t.dimissiondate, 3), 'YYYYMM') >=
           cIndexCalNo))
       and not exists (select 1
              from tmwagetotal t1
             where t1.wageno = cIndexCalNo
               and t1.agentcode = t.agentcode);

  --add by 晋升保底逻辑优化
  --更新其他保底类型
  update tmwagetotal a
     set a.protection_bonustype_o =
         (select b.type
            from tmothermincommission b, laagent c
           where a.agentcode = c.agentcode
             and b.agentnum = c.agentnum
             and b.startdate = cIndexCalNo)
   where a.wageno = cIndexCalNo;

  --晋升前岗位佣金
  update tmwagetotal tt
     set tt.before_promotion_commission = nvl((select sum((t.w41 + t.w42 +
                                                         t.w43 + t.w44 +
                                                         t.w45 + t.w46) * w100 +
                                                         (t.w59 + t.w60 +
                                                         t.w61 + t.w62 +
                                                         t.w63 + t.w64) * w100 * w37 +
                                                         (t.w47 + t.w48 +
                                                         t.w49 + t.w50 +
                                                         t.w51 + t.w52) * w100 +
                                                         (t.w65 + t.w66 +
                                                         t.w67 + t.w68 +
                                                         t.w69 + t.w70) * w100 * w37 +
                                                         (t.w53 + t.w54 +
                                                         t.w55 + t.w56 +
                                                         t.w57 + t.w58) * w100 +
                                                         (t.w71 + t.w72 +
                                                         t.w73 + t.w74 +
                                                         t.w75 + t.w76) * w100 * w37)
                                                from tmwage t
                                               where t.indexcalno =
                                                     tt.wageno
                                                 and tt.agentcode =
                                                     t.agentcode
                                                 and exists
                                               (select 1
                                                        from tmothermincommission b,
                                                             laagent              c
                                                       where tt.agentcode =
                                                             c.agentcode
                                                         and b.agentnum =
                                                             c.agentnum
                                                         and b.startdate =
                                                             cIndexCalNo
                                                         and b.type = '01'
                                                         and t.agentgrade <>
                                                             b.post)),
                                              0)
   where tt.wageno = cIndexCalNo;

  --晋升后岗位佣金
  update tmwagetotal tt
     set tt.after_promotion_commission = nvl((select sum((t.w41 + t.w42 +
                                                        t.w43 + t.w44 +
                                                        t.w45 + t.w46) * w100 +
                                                        (t.w59 + t.w60 +
                                                        t.w61 + t.w62 +
                                                        t.w63 + t.w64) * w100 * w37 +
                                                        (t.w47 + t.w48 +
                                                        t.w49 + t.w50 +
                                                        t.w51 + t.w52) * w100 +
                                                        (t.w65 + t.w66 +
                                                        t.w67 + t.w68 +
                                                        t.w69 + t.w70) * w100 * w37 +
                                                        (t.w53 + t.w54 +
                                                        t.w55 + t.w56 +
                                                        t.w57 + t.w58) * w100 +
                                                        (t.w71 + t.w72 +
                                                        t.w73 + t.w74 +
                                                        t.w75 + t.w76) * w100 * w37)
                                               from tmwage t
                                              where t.indexcalno = tt.wageno
                                                and tt.agentcode =
                                                    t.agentcode
                                                and exists
                                              (select 1
                                                       from tmothermincommission b,
                                                            laagent              c
                                                      where tt.agentcode =
                                                            c.agentcode
                                                        and b.agentnum =
                                                            c.agentnum
                                                        and b.startdate =
                                                            cIndexCalNo
                                                        and b.type = '01'
                                                        and t.agentgrade =
                                                            b.post)),
                                             0)
   where tt.wageno = cIndexCalNo;

  --打折佣金汇总 ORIGINAL_COMMISSION_Conversion
  update tmwagetotal t
     set t.ORIGINAL_COMMISSION_CONVERSION =
         --TMR
          nvl((select sum(a.tmramep * a.tmrcommrate * a.tmrrevisitrate)
                from tmcommission a, tmsalaryagentc b
               where a.iswage = 'Y'
                 and a.isjumppoint = 'Y'
                 and a.tmrcode = b.agentcode
                 and b.bakmonth = t.wageno
                 and (b.agentstate <> 03 or

                     (b.agentstate = '03' and b.dimissionmonth >= t.wageno))
                 and a.commissionmonth = t.wageno
                 and a.tmrcode = t.agentcode),
              0) +
         --TMS
          nvl((select sum(a.tmsamep * a.tmscommrate * a.tmsrevisitrate)
                from tmcommission a, tmsalaryagentc b
               where a.iswage = 'Y'
                 and a.isjumppoint = 'Y'
                 and a.tmscode = b.agentcode
                 and b.bakmonth = t.wageno
                 and (b.agentstate <> 03 or
                     (b.agentstate = '03' and b.dimissionmonth >= t.wageno))
                 and a.commissionmonth = t.wageno
                 and a.tmscode = t.agentcode),
              0) +
         --CCM
          nvl((select sum(a.CCMamep * a.CCMcommrate * a.CCMrevisitrate)
                from tmcommission a, tmsalaryagentc b
               where a.iswage = 'Y'
                 and a.isjumppoint = 'Y'
                 and a.ccmcode = b.agentcode
                 and b.bakmonth = t.wageno
                 and (b.agentstate <> 03 or
                     (b.agentstate = '03' and b.dimissionmonth >= t.wageno))
                 and a.commissionmonth = t.wageno
                 and a.ccmcode = t.agentcode),
              0)
   where t.wageno = cIndexCalNo;

  --打折翻倍佣金
  update tmwagetotal tt
     set tt.original_conversion_double = tt.original_commission_conversion *
                                         (select max(t.w100)
                                            from tmwage t
                                           where t.indexcalno = tt.wageno
                                             and t.managecom = tt.managecom
                                             and tt.agentcode = t.agentcode)
   where tt.wageno = cIndexCalNo;

  --晋升前打折佣金before_promotion_Conversion
  update tmwagetotal t
     set t.before_promotion_Conversion =
         --TMS
          nvl((select sum(a.tmSamep * a.tmScommrate * a.tmSrevisitrate)
                from tmcommission a, tmsalaryagentc b
               where a.iswage = 'Y'
                 and a.isjumppoint = 'Y'
                 and a.tmscode = b.agentcode
                 and b.bakmonth = t.wageno
                 and (b.agentstate <> 03 or
                     (b.agentstate = '03' and b.dimissionmonth >= t.wageno))
                 and a.commissionmonth = t.wageno
                 and a.tmscode = t.agentcode),
              0) +
         --CCM
          nvl((select sum(a.CCMamep * a.CCMcommrate * a.CCMrevisitrate)
                from tmcommission a, tmsalaryagentc b
               where a.iswage = 'Y'
                 and a.isjumppoint = 'Y'
                 and a.ccmcode = b.agentcode
                 and b.bakmonth = t.wageno
                 and (b.agentstate <> 03 or
                     (b.agentstate = '03' and b.dimissionmonth >= t.wageno))
                 and a.commissionmonth = t.wageno
                 and a.ccmcode = t.agentcode),
              0)
   where t.wageno = cIndexCalNo
     and exists (select *
            from laagentgrade c
           where c.gradecode = t.agentgrade
             and c.noti = 'TMR');

  update tmwagetotal t
     set t.before_promotion_Conversion =
         --TMR
          nvl((select sum(a.tmramep * a.tmrcommrate * a.tmrrevisitrate)
                from tmcommission a, tmsalaryagentc b
               where a.iswage = 'Y'
                 and a.isjumppoint = 'Y'
                 and a.tmrcode = b.agentcode
                 and b.bakmonth = t.wageno
                 and (b.agentstate <> 03 or
                     (b.agentstate = '03' and b.dimissionmonth >= t.wageno))
                 and a.commissionmonth = t.wageno
                 and a.tmrcode = t.agentcode),
              0) +

         --CCM
          nvl((select sum(a.CCMamep * a.CCMcommrate * a.CCMrevisitrate)
                from tmcommission a, tmsalaryagentc b
               where a.iswage = 'Y'
                 and a.isjumppoint = 'Y'
                 and a.ccmcode = b.agentcode
                 and b.bakmonth = t.wageno
                 and (b.agentstate <> 03 or
                     (b.agentstate = '03' and b.dimissionmonth >= t.wageno))
                 and a.commissionmonth = t.wageno
                 and a.ccmcode = t.agentcode),
              0)
   where t.wageno = cIndexCalNo
     and exists (select *
            from laagentgrade c
           where c.gradecode = t.agentgrade
             and c.noti = 'TMS');
  update tmwagetotal t
     set t.before_promotion_Conversion =
         --TMR
          nvl((select sum(a.tmramep * a.tmrcommrate * a.tmrrevisitrate)
                from tmcommission a, tmsalaryagentc b
               where a.iswage = 'Y'
                 and a.isjumppoint = 'Y'
                 and a.tmrcode = b.agentcode
                 and b.bakmonth = t.wageno
                 and (b.agentstate <> 03 or
                     (b.agentstate = '03' and b.dimissionmonth >= t.wageno))
                 and a.commissionmonth = t.wageno
                 and a.tmrcode = t.agentcode),
              0) +
         --TMS
          nvl((select sum(a.tmSamep * a.tmScommrate * a.tmSrevisitrate)
                from tmcommission a, tmsalaryagentc b
               where a.iswage = 'Y'
                 and a.isjumppoint = 'Y'
                 and a.tmscode = b.agentcode
                 and b.bakmonth = t.wageno
                 and (b.agentstate <> 03 or
                     (b.agentstate = '03' and b.dimissionmonth >= t.wageno))
                 and a.commissionmonth = t.wageno
                 and a.tmscode = t.agentcode),
              0)
   where t.wageno = cIndexCalNo
     and exists (select *
            from laagentgrade c
           where c.gradecode = t.agentgrade
             and c.noti = 'CCM');
  --晋升后打折佣金 after_promotion_Conversion
  --晋升ccm岗位打折佣金
  update tmwagetotal t
     set t.after_promotion_conversion = nvl((select sum(a.ccmamep *
                                                       a.ccmcommrate *
                                                       a.ccmrevisitrate)
                                              from tmcommission a
                                             where a.iswage = 'Y'
                                               and a.isjumppoint = 'Y'
                                               and a.commissionmonth =
                                                   t.wageno
                                               and a.ccmcode = t.agentcode),
                                            0)
   where exists (select 1
            from tmothermincommission b, laagent c, laagentgrade d
           where t.agentcode = c.agentcode
             and d.gradecode = t.agentgrade
             and c.agentstate <> 3
             and b.agentnum = c.agentnum
             and b.startdate = cIndexCalNo
             and b.type = '01'
             and d.noti = b.post
             and d.noti = 'CCM')
     and t.wageno = cIndexCalNo;

  --晋升tms岗位打折佣金
  update tmwagetotal t
     set t.after_promotion_conversion = nvl((select sum(a.tmsamep *
                                                       a.tmscommrate *
                                                       a.tmsrevisitrate)
                                              from tmcommission a
                                             where a.iswage = 'Y'
                                               and a.isjumppoint = 'Y'
                                               and a.commissionmonth =
                                                   t.wageno
                                               and a.tmscode = t.agentcode),
                                            0)
   where exists (select 1
            from tmothermincommission b, laagent c, laagentgrade d
           where t.agentcode = c.agentcode
             and d.gradecode = t.agentgrade
             and c.agentstate <> 3
             and b.agentnum = c.agentnum
             and b.startdate = cIndexCalNo
             and b.type = '01'
             and d.noti = b.post
             and d.noti = 'TMS')
     and t.wageno = cIndexCalNo;

  --晋升tmr岗位打折佣金
  update tmwagetotal t
     set t.after_promotion_conversion = nvl((select sum(a.tmramep *
                                                       a.tmrcommrate *
                                                       a.tmrrevisitrate)
                                              from tmcommission a
                                             where a.iswage = 'Y'
                                               and a.isjumppoint = 'Y'
                                               and a.commissionmonth =
                                                   t.wageno
                                               and a.tmrcode = t.agentcode),
                                            0)
   where exists (select 1
            from tmothermincommission b, laagent c, laagentgrade d
           where t.agentcode = c.agentcode
             and d.gradecode = t.agentgrade
             and c.agentstate <> 3
             and b.agentnum = c.agentnum
             and b.startdate = cIndexCalNo
             and b.type = '01'
             and d.noti = b.post
             and d.noti = 'TMR')
     and t.wageno = cIndexCalNo;

  --adjust --转换率奖金
  update tmwagetotal tt
     set ConversionRatePrize =
         (select nvl(sum(a.amount), 0)
            from tmadjust a
           where 1 = 1
             and (select aa.agentcode
                    from laagent aa
                   where aa.branchtype = '1'
                     and aa.agentnum = a.agentnum) = tt.agentcode
             and a.yearmonth = tt.wageno
             and a.type = '01')
   where tt.wageno = cIndexCalNo;
  -- Coach奖金
  update tmwagetotal tt
     set CoachPrize =
         (select nvl(sum(a.amount), 0)
            from tmadjust a
           where 1 = 1
             and (select aa.agentcode
                    from laagent aa
                   where aa.branchtype = '1'
                     and aa.agentnum = a.agentnum) = tt.agentcode
             and a.yearmonth = tt.wageno
             and a.type = '02')
   where tt.wageno = cIndexCalNo;
  --保全小组佣金
  update tmwagetotal tt
     set LPEDORPrize =
         (select nvl(sum(a.amount), 0)
            from tmadjust a
           where 1 = 1
             and (select aa.agentcode
                    from laagent aa
                   where aa.branchtype = '1'
                     and aa.agentnum = a.agentnum) = tt.agentcode
             and a.yearmonth = tt.wageno
             and a.type = '03')
   where tt.wageno = cIndexCalNo;
  --基本MTV奖金
  update tmwagetotal tt
     set BasicMTVPrize =
         (select nvl(sum(a.amount), 0)
            from tmadjust a
           where 1 = 1
             and (select aa.agentcode
                    from laagent aa
                   where aa.branchtype = '1'
                     and aa.agentnum = a.agentnum) = tt.agentcode
             and a.yearmonth = tt.wageno
             and a.type = '04')
   where tt.wageno = cIndexCalNo;
  --特殊MTV奖金奖金
  update tmwagetotal tt
     set SpecialMTVPrize =
         (select nvl(sum(a.amount), 0)
            from tmadjust a
           where 1 = 1
             and (select aa.agentcode
                    from laagent aa
                   where aa.branchtype = '1'
                     and aa.agentnum = a.agentnum) = tt.agentcode
             and a.yearmonth = tt.wageno
             and a.type = '05')
   where tt.wageno = cIndexCalNo;
  --其它类型奖金
  update tmwagetotal tt
     set otherPrize =
         (select nvl(sum(a.amount), 0)
            from tmadjust a
           where 1 = 1
             and (select aa.agentcode
                    from laagent aa
                   where aa.branchtype = '1'
                     and aa.agentnum = a.agentnum) = tt.agentcode
             and a.yearmonth = tt.wageno
             and a.type = '06')
   where tt.wageno = cIndexCalNo;
  --销售管理层奖金
  update tmwagetotal tt
     set SaleManagementPrize =
         (select nvl(sum(a.amount), 0)
            from tmadjust a
           where 1 = 1
             and (select aa.agentcode
                    from laagent aa
                   where aa.branchtype = '1'
                     and aa.agentnum = a.agentnum) = tt.agentcode
             and a.yearmonth = tt.wageno
             and a.type = '07')
   where tt.wageno = cIndexCalNo;

  --上月薪资负数结转
  update tmwagetotal tt
     set NEGATIVESALARY =
         (select nvl(sum(a.amount), 0)
            from tmadjust a
           where 1 = 1
             and (select aa.agentcode
                    from laagent aa
                   where aa.branchtype = '1'
                     and aa.agentnum = a.agentnum) = tt.agentcode
             and a.yearmonth = tt.wageno
             and a.type = '08')
   where tt.wageno = cIndexCalNo;

  --重大案件扣款
  update tmwagetotal tt
     set KeyCaseDeduct =
         (select nvl(sum(a.amount), 0)
            from tmadjust a
           where 1 = 1
             and (select aa.agentcode
                    from laagent aa
                   where aa.branchtype = '1'
                     and aa.agentnum = a.agentnum) = tt.agentcode
             and a.yearmonth = tt.wageno
             and a.type = '09')
   where tt.wageno = cIndexCalNo;

  --投诉有责扣款
  update tmwagetotal tt
     set ComplaintDeduct =
         (select nvl(sum(a.amount), 0)
            from tmadjust a
           where 1 = 1
             and (select aa.agentcode
                    from laagent aa
                   where aa.branchtype = '1'
                     and aa.agentnum = a.agentnum) = tt.agentcode
             and a.yearmonth = tt.wageno
             and a.type = '10')
   where tt.wageno = cIndexCalNo;

  --监管有责扣款
  update tmwagetotal tt
     set SupervisionDeduct =
         (select nvl(sum(a.amount), 0)
            from tmadjust a
           where 1 = 1
             and (select aa.agentcode
                    from laagent aa
                   where aa.branchtype = '1'
                     and aa.agentnum = a.agentnum) = tt.agentcode
             and a.yearmonth = tt.wageno
             and a.type = '11')
   where tt.wageno = cIndexCalNo;

  --重刷上限金额
  update tmwagetotal tt
     set tt.Original_Commission = least(tt.Original_Commission, tt.maxmoney)
   where tt.wageno = cIndexCalNo;

  --翻倍之前金额是否大于上限金额，如果大于，取打折翻倍之前的
  update tmwagetotal tt
     set tt.Original_Commission = greatest(tt.Original_Commission,
                                           tt.Original_Commission_basic)
   where tt.wageno = cIndexCalNo
     and tt.Original_Commission < tt.Original_Commission_basic;
  --重刷上限金额
  update tmwagetotal tt
     set tt.ORIGINAL_CONVERSION_DOUBLE = least(tt.ORIGINAL_CONVERSION_DOUBLE,
                                               tt.maxmoney)
   where tt.wageno = cIndexCalNo;

  --翻倍之前金额是否大于上限金额，如果大于，取打折翻倍之前的
  update tmwagetotal tt
     set tt.ORIGINAL_CONVERSION_DOUBLE = greatest(tt.ORIGINAL_CONVERSION_DOUBLE,
                                                  tt.ORIGINAL_COMMISSION_CONVERSION)
   where tt.wageno = cIndexCalNo
     and tt.ORIGINAL_CONVERSION_DOUBLE < tt.ORIGINAL_COMMISSION_CONVERSION;

  --全勤津贴
  update tmwagetotal t
     set t.FullWorkPrize = nvl((select max(a.allowanceamount)
                                 from TMTimeAllowance a,
                                      TMSALARYAGENTC  b,
                                      TMAGENTTARGET   c
                                where a.managecom = t.managecom
                                  and a.managecom = b.managecom
                                  and b.BAKMONTH = cIndexCalNo
                                  and ((c.startdate >= a.startdate and
                                      c.startdate = cIndexCalNo and
                                      c.attendance >= a.VALUE and
                                      b.agentnum = c.agentnum and
                                      b.managecom = c.managecom) or
                                      0 = a.VALUE or TMBasic.GetRightMonth(b.employdate,
                                                                            -1) >=
                                      cIndexCalNo)
                                  and a.agentgrade = b.agentgrade
                                  and b.agentcode = t.agentcode
                                  and a.agentgrade = t.agentgrade
                                  and cIndexCalNo <=
                                      TMBasic.GetRightMonth(b.EMPLOYDATE,
                                                            a.monthcont +
                                                            a.startmonth - 1)
                                  and cIndexCalNo >=
                                      TMBasic.GetRightMonth(b.EMPLOYDATE,
                                                            a.startmonth - 1)
                                  and a.FLAG = '11'
                                  and a.type = '02'
                                  and a.startdate <= cIndexCalNo
                                  and a.STARTDATE =
                                      (select max(tt.STARTDATE)
                                         from TMTimeAllowance tt
                                        where tt.managecom = t.managecom
                                          and tt.agentgrade = t.agentgrade
                                          and tt.STARTDATE >= a.startdate
                                          and tt.type = '02'
                                          and tt.FLAG = '11')),
                               0)
   where 1 = 1
     and t.wageno = cIndexCalNo
  /* and exists (select *
   from tmlaagentc a, TMAGENTTARGET b
  where a.agentnum = b.agentnum
    and a.agentcode = t.agentcode
    and a.BAKMONTH = cIndexCalNo)*/
  ;

  --通时津贴
  update tmwagetotal t
     set t.CallWorkPrize = nvl((select max(a.allowanceamount)
                                 from TMTimeAllowance a,
                                      TMSALARYAGENTC  b,
                                      TMAGENTTARGET   c
                                where a.managecom = t.managecom
                                  and a.managecom = b.managecom
                                  and b.BAKMONTH = cIndexCalNo
                                  and c.startdate >= a.startdate
                                  and c.startdate = cIndexCalNo
                                  and a.agentgrade = b.agentgrade
                                  and b.agentcode = t.agentcode
                                  and a.agentgrade = t.agentgrade
                                  and ((to_number(replace(c.TALKTIME,
                                                          ':',
                                                          '')) >=
                                      to_number(replace(a.VALUE, ':', '')) and
                                      b.agentnum = c.agentnum and
                                      b.managecom = c.managecom) or
                                      to_char(b.takeupdate, 'yyyymm') >
                                      cIndexCalNo)
                                  and cIndexCalNo <=
                                      TMBasic.GetRightMonth(b.EMPLOYDATE,
                                                            a.monthcont +
                                                            a.startmonth - 1)
                                  and cIndexCalNo >=
                                      TMBasic.GetRightMonth(b.EMPLOYDATE,
                                                            a.startmonth - 1)
                                  and a.flag = '11'
                                  and a.type = '01'
                                  and a.startdate <= cIndexCalNo
                                  and a.STARTDATE =
                                      (select max(tt.STARTDATE)
                                         from TMTimeAllowance tt
                                        where tt.managecom = t.managecom
                                          and tt.agentgrade = t.agentgrade
                                          and tt.STARTDATE >= a.startdate
                                          and tt.type = '01'
                                          and tt.FLAG = '11')),
                               0)
   where 1 = 1
     and t.wageno = cIndexCalNo;
  /*and exists (select *
   from TMSALARYAGENTC a, TMAGENTTARGET b
  where a.agentnum = b.agentnum
    and a.agentcode = t.agentcode
    and a.BAKMONTH = cIndexCalNo)*/

  --保底佣金(新人保底)
  update tmwagetotal t
     set t.Protection_Bonus_N = nvl((select max(a.MINCOMMISSION)
                                      from TMStandardMinCOM a,
                                           TMSALARYAGENTC   b
                                     where a.managecom = t.managecom
                                       and a.managecom = b.managecom
                                       and b.GRADELEVEL = 'TMR'
                                       and b.BAKMONTH = cIndexCalNo
                                       and (a.attendance <=
                                           (select nvl(max(c.attendance), 0)
                                               from TMAGENTTARGET c
                                              where c.startdate >=
                                                    a.startdate
                                                and c.startdate = cIndexCalNo
                                                and c.agentnum = b.agentnum
                                                and c.attendance >=
                                                    a.attendance) or
                                           TMBasic.GetRightMonth(b.employdate,
                                                                  -1) >=
                                           cIndexCalNo)
                                       and b.agentstate <> '03'
                                       and a.agentgrade = b.agentgrade
                                       and b.agentcode = t.agentcode
                                       and a.agentgrade = t.agentgrade
                                       and ((a.datetype = '01' and
                                           TMBasic.GetRightMonth(b.EMPLOYDATE,
                                                                   a.startmonth - 1) <=
                                           cIndexCalNo and TMBasic.GetRightMonth(b.EMPLOYDATE,
                                                                                   a.startmonth +
                                                                                   a.monthcont - 1) >=
                                           cIndexCalNo))
                                       and a.flag = '11'
                                       and b.ISPROTECT = 'Y'
                                       and a.startdate <= cIndexCalNo
                                       and a.STARTDATE =
                                           (select max(tt.STARTDATE)
                                              from TMStandardMinCOM tt
                                             where tt.managecom = a.managecom
                                               and tt.agentgrade =
                                                   a.agentgrade
                                               and tt.STARTDATE >=
                                                   a.STARTDATE
                                               and tt.FLAG = '11'
                                               and tt.startdate <=
                                                   cIndexCalNo)),
                                    0)
   where 1 = 1
     and t.wageno = cIndexCalNo;

  --保底佣金(特殊新人保底)
  update tmwagetotal t
     set t.Protection_Bonus_N = nvl((select max(a.mincommission)
                                      from TMSPECIALMINCOM a,
                                           TMSALARYAGENTC  b
                                     where a.agentnum = b.agentnum
                                       and a.managecom = b.managecom
                                       and b.agentcode = t.agentcode
                                       and b.GRADELEVEL = 'TMR'
                                       and b.agentstate <> '03'
                                       and t.wageno = b.bakmonth
                                       and a.startdate <= cIndexCalNo
                                       and a.startdate =
                                           (select max(x.startdate)
                                              from TMSPECIALMINCOM x
                                             where a.managecom = x.managecom
                                               and a.agentnum = x.agentnum
                                               and x.startdate <= cIndexCalNo)
                                       and a.flag = 'Y'),
                                    t.Protection_Bonus_N)
   where 1 = 1
     and t.wageno = cIndexCalNo
  /*and exists (select 1
   from tmlaagentc a
  where a.agentcode = t.agentcode
    and a.BAKMONTH = cIndexCalNo)*/
  ;
  --特殊新人保底中为N的 保底全部置为0
  update tmwagetotal t
     set t.Protection_Bonus_N = nvl((select 0
                                      from TMSPECIALMINCOM a,
                                           TMSALARYAGENTC  b
                                     where a.agentnum = b.agentnum
                                       and a.managecom = b.managecom
                                       and b.agentcode = t.agentcode
                                       and b.GRADELEVEL = 'TMR'
                                       and b.agentstate <> '03'
                                       and t.wageno = b.bakmonth
                                       and a.startdate <= cIndexCalNo
                                       and a.startdate =
                                           (select max(x.startdate)
                                              from TMSPECIALMINCOM x
                                             where a.managecom = x.managecom
                                               and a.agentnum = x.agentnum
                                               and x.startdate <= cIndexCalNo)
                                       and a.flag = 'N'),
                                    t.Protection_Bonus_N)
   where 1 = 1
     and t.wageno = cIndexCalNo
  /*and exists (select 1
   from tmlaagentc a
  where a.agentcode = t.agentcode
    and a.BAKMONTH = cIndexCalNo)*/
  ;
  commit;
  --保底佣金(其他保底)
  update tmwagetotal t
     set t.Protection_Bonus_O = nvl((select max(a.MINCOMMISSION)
                                      from TMOtherminCommission a,
                                           TMSALARYAGENTC       b
                                     where a.agentnum = b.agentnum
                                       and a.managecom = b.managecom
                                          /*modify by Aline 20200817 其他保底不看员工状态*/
                                          --and b.agentstate <> '03'
                                       and b.agentcode = t.agentcode
                                       and t.wageno = b.bakmonth
                                       and a.startdate <= cIndexCalNo
                                       and a.enddate >= cIndexCalNo),
                                    0)
   where 1 = 1
     and t.wageno = cIndexCalNo
  /*and exists (select 1
   from tmlaagentc a
  where a.agentcode = t.agentcode
    and a.BAKMONTH = cIndexCalNo)*/
  ;
  commit;

  --add by Xiongl Li 晋升保底逻辑变更
  --payment
  update tmwagetotal t
     set t.payment = case
                       when t.protection_bonustype_o = '01' then
                                        -- 晋升后佣金
                        greatest(nvl(t.AFTER_PROMOTION_COMMISSION, 0) +
                                        -- 基本MTV奖金
                                 nvl(t.BasicMTVPrize, 0),
                                        -- 其他保底佣金
                                 nvl(t.Protection_Bonus_o, 0))
                       --基本佣金打折前后Gap
                                -- 晋升前佣金
                        + nvl(t.before_promotion_commission, 0)
                       else
                                    -- 基本佣金汇总
                        (greatest((nvl(t.original_commission, 0) +
                                    -- 基本MTV奖金
                                  nvl(t.BasicMTVPrize, 0)),
                                  (case
                                                      -- 新人保底佣金
                                    when greatest(nvl(t.Protection_Bonus_N, 0),
                                                      -- 其他保底佣金
                                                  nvl(t.Protection_Bonus_O, 0)) = 0 then
                                     -9999999
                                    else
                                     greatest(nvl(t.Protection_Bonus_N, 0),
                                              nvl(t.Protection_Bonus_O, 0))
                                  end)))
                     end -
                    --基本佣金打折前后Gap
                    -- nvl(基本佣金汇总,0)-nvl(打折佣金翻倍, 0)
                     (nvl(t.original_commission, 0) -
                     nvl(t.ORIGINAL_CONVERSION_DOUBLE, 0)) +
                     -- 不参与跳点扣佣金额
                     t.payment_withdraw_n +
                     -- 全勤奖金
                     t.FullWorkPrize +
                     -- 通时奖金
                     t.callworkprize +
                     -- 转换率奖金
                     (t.ConversionRatePrize +
                     -- coach奖金
                     t.CoachPrize +
                     -- 保全小组佣金
                     LPEDORPrize +
                     -- 特殊MTV奖金
                     SpecialMTVPrize +
                     -- 其他类型奖金
                     otherPrize +
                     -- 销售管理层奖金
                     SaleManagementPrize +
                     -- 上月薪资结转
                     NEGATIVESALARY +
                     -- 重点案件扣款
                     t.keycasededuct +
                     -- 有责投诉扣款
                     t.complaintdeduct +
                     -- 监管有责扣款
                     t.supervisiondeduct)
   where 1 = 1
     and t.wageno = cIndexCalNo;
  commit;
  --commission Level
  update tmwagetotal t
     set t.commissionlevel = case
                               when t.payment > 0 and t.payment <= 1000 then
                                '0-1K'
                               when t.payment > 1000 and t.payment <= 2000 then
                                '1-2K'
                               when t.payment > 2000 and t.payment <= 3000 then
                                '2-3K'
                               when t.payment > 3000 and t.payment <= 4000 then
                                '3-4K'
                               when t.payment > 4000 and t.payment <= 5000 then
                                '4-5K'
                               when t.payment > 5000 and t.payment <= 10000 then
                                '5-10K'
                               when t.payment > 10000 and t.payment <= 20000 then
                                '10-20K'
                               when t.payment > 20000 and t.payment <= 50000 then
                                '20-50K'
                               when t.payment > 50000 then
                                '>50K'
                             end
   where 1 = 1
     and t.wageno = cIndexCalNo
  /*and exists (select 1
   from tmlaagentc a
  where a.agentcode = t.agentcode
    and a.BAKMONTH = cIndexCalNo)*/
  ;
  /* 临时注释 Aline 2020-10-23 */
  /*    --当月离职的人缓发
      update tmwagetotal a
         set a.payrollmonth = to_char(add_Months(to_date(a.wageno, 'yyyymm'),
                                                 3),
                                      'yyyymm')
       where a.wageno = cIndexCalNo
         and exists (select 1
                from TMSALARYAGENTC t
               where a.agentcode = t.agentcode
                 and a.wageno = t.bakmonth
                 and t.agentstate = '03'
                 and t.dimissionmonth >= cIndexCalNo);
      --次月离职的人缓发
      update tmwagetotal a
         set a.payrollmonth = to_char(add_Months(to_date(a.wageno, 'yyyymm'),
                                                 2),
                                      'yyyymm')
       where a.wageno = cIndexCalNo
         and exists
       (select 1
                from TMSALARYAGENTC t
               where a.agentcode = t.agentcode
                    --and t.managecom = cManageCom
                 and a.wageno = t.bakmonth
                 and t.agentstate = '03'
                 and to_char(add_Months(to_date(t.dimissionmonth, 'yyyymm'), 1),
                             'yyyymm') = cIndexCalNo);
      --第三个月离职的人缓发
      update tmwagetotal a
         set a.payrollmonth = to_char(add_Months(to_date(a.wageno, 'yyyymm'),
                                                 1),
                                      'yyyymm')
       where a.wageno = cIndexCalNo
         and exists
       (select 1
                from TMSALARYAGENTC t
               where a.agentcode = t.agentcode
                    --and t.managecom = cManageCom
                 and a.wageno = t.bakmonth
                 and t.agentstate = '03'
                 and to_char(add_Months(to_date(t.dimissionmonth, 'yyyymm'), 2),
                             'yyyymm') = cIndexCalNo);
  */
  --离职缓发的计佣月份改成按离职月份+3  by2020年11月16日
  update tmwagetotal a
     set a.payrollmonth = to_char(add_Months(to_date((SELECT tm.dimissionmonth
                                                       FROM TMSALARYAGENTC tm
                                                      where a.agentcode =
                                                            tm.agentcode
                                                        and a.wageno =
                                                            tm.bakmonth
                                                        and tm.agentstate = '03'),
                                                     'yyyymm'),
                                             3),
                                  'yyyymm')
   where a.wageno = cIndexCalNo
     and exists
   (select 1
            from TMSALARYAGENTC t
           where a.agentcode = t.agentcode
                --and t.managecom = cManageCom
             and a.wageno = t.bakmonth
             and t.agentstate = '03'
             and to_char(add_Months(to_date(t.dimissionmonth, 'yyyymm'), 3),
                         'yyyymm') >= cIndexCalNo);

  update tmwagetotal a
     set a.payrollmonth = nvl((select t.postponemonth
                                from TMWageDelay t, laagent t1
                               where t.agentnum = t1.agentnum
                                 and t1.branchtype = '1'
                                 and t.managecom = a.managecom
                                 and a.agentcode = t1.agentcode
                                 and t.commissionmonth = a.wageno),
                              a.payrollmonth)
   where a.wageno = cIndexCalNo;

  commit;

  commit;
  return 'OK';
Exception
  When Others Then
    --Return 'ERROR';
    rollback;
    Return dbms_utility.format_error_backtrace || ':' || sqlcode || sqlerrm;
end;