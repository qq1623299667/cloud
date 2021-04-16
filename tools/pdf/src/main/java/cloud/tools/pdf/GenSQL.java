package cloud.tools.pdf;

import org.apache.catalina.connector.Request;

import javax.servlet.http.HttpServletRequest;

/**
 * 老代码的sql转换成带？的sql
 * @author Jia Shi
 * @since 2021/3/16
 */
public class GenSQL {
    static String mBatchSN = "?batch?";
    static String BatchSN = "?BatchSN?";
    static String Operator = "?Operator?";
    static String BranchType = "?BranchType?";
    static String ManageCom = "?ManageCom?";
    static String mWriteOffFlag = "mWriteOffFlag??";
    static String tCurrentDate="?tCurrentDate?";
    static String tCurrentTime="?tCurrentTime?";
    static String CurrentDate="?CurrentDate?";
    static String currentDate="?CurrentDate?";
    static String CurrentTime="?CurrentTime?";
    static String currentTime="?CurrentTime?";
    static String tREQSEQID = "?tREQSEQID?";
    static String tRDSEQ = "?tRDSEQ?";
    static String tSourceNoteCode = "?tSourceNoteCode?";
    static String tROWVERSION = "?tROWVERSION?";
    static String mProvDate = "?mProvDate?";
    static String mBranchCode = "?mBranchCode?";
    static String flag = "?flag?";
    static String fileId = "?fileId?";
    static String filePath = "?filePath?";
    static String FilterCondition = "?FilterCondition?";
    static String mErrorLogSN= "?mErrorLogSN?";
    static String mErrors = "?mErrors?";
    static String lastselectdtime = "?lastselectdtime?";
    static String sql = "?sql?";
    static String bakMonth = "?bakMonth?";
    static String tBakMonth = "?bakMonth?";
    static String datetype = "?datetype?";
    static String mIndexCalNo = "?mIndexCalNo?";
    static String mWageType = "?mWageType?";
    static String tComgrade = "?tComgrade?";
    static String tMangecom = "?tMangecom?";
    static String mBaseCode = "?mBaseCode?";
    static String mBranchType = "?mBranchType?";
    static String table = "lrindexinfo";
    static String lrindexinfo = "lrindexinfo";
    static String mWageNo = "?mWageNo?";
    static String managecom = "?managecom?";
    static String agentgrade = "?agentgrade?";
    static String startmonth = "?startmonth?";
    static String branchtype = "?branchtype?";
    static String basecode = "?basecode?";
    static String indexType = "?indexType?";
    static String indexCode = "?indexCode?";
    static String rowNum = "?rowNum?";
    static String colNum = "?colNum?";
    static String comcode = "?comcode?";
    static String ComCode = "?comcode?";
    static String newcomerdoublessn = "?newcomerdoublessn?";
    static String mBATCHNUM = "?mBATCHNUM?";
    static String i = "?i?";
    static String firstMonthRate = "?firstMonthRate?";
    static String secondMonthRate = "?secondMonthRate?";
    static String thirdMonthRate = "?thirdMonthRate?";
    static String maxBonus = "?maxBonus?";
    static String outWorkRateFlag = "?outWorkRateFlag?";
    static String attendance = "?attendance?";
    static String startDate = "?startDate?";
    static String postType = "?postType?";
    static String type = "?type?";
    static String amepStart = "?amepStart?";
    static String commissionRate = "?commissionRate?";
    static String commissionType = "?commissionType?";
    static String agentNum = "?agentNum?";
    static String rateType = "?rateType?";
    static String calBaseType = "?calBaseType?";
    GlobalInput mGlobalInput = new GlobalInput();
    static HttpServletRequest request = new Request() ;
    static String mIDX[] = new String[]{newcomerdoublessn};
    static String projectCode = "?projectCode?";
    static String tmCheckFlagCode = "?tmCheckFlagCode?";
    static String standardMinCOMSN = "?standardMinCOMSN?";
    static String monthCont = "?monthCont?";
    static String minCommission = "?minCommission?";
    static String agentCode = "?agentCode?";
    static String branchLevel = "?branchLevel?";
    static String branchAttr = "?branchAttr?";
    static String branchName = "?branchName?";
    static String remark = "?remark?";
    static String cNoType = "?cNoType?";
    static String cNoLimit = "?cNoLimit?";
    static String shortName = "?shortName?";
    static String endDate = "?endDate?";
    static String talkTime = "?talkTime?";
    static String timeAllowanceSN = "?timeAllowanceSN?";
    static String codeName = "?codeName?";
    static String value = "?value?";
    static String x = "?x?";
    static String allowanceAmount = "?allowanceAmount?";
    static String CheckFlag = "?CheckFlag?";
    static String contNo = "?contNo?";
    static String tMSagentnum = "?tMSagentnum?";
    static String cCMagentnum = "?cCMagentnum?";
    static String yearmonth = "?yearmonth?";
    static String filePlayName = "?filePlayName?";
    static String adjustmentType = "?adjustmentType?";
    static String agentName = "?agentName?";
    static String amount = "?amount?";
    static String tCalMonth = "?tCalMonth?";
    static String wageNo = "?wageNo?";
    static String commissionMonth = "?commissionMonth?";
    static String postponeMonth = "?postponeMonth?";
    static String indexCalNo = "?indexCalNo?";
    static String wageStateName = "?wageStateName?";
    static String seqNo = "?seqNo?";
    static String ip = "?ip?";
    static String mReCal = "?mReCal?";
    static String mStep = "?mStep?";
    static String month = "?month?";
    static String mMonth = month;
    static String mContNo = "?mContNo?";
    static String baseCode = "?baseCode?";
    static String agentGrade = "?agentGrade?";
    static String datatype = "datatype";
    static String indexcode = "?indexCode?";
    static String calSQL = "?calSQL?";
    static String AgentGrade = "?AgentGrade?";
    static String IndexCode = "?IndexCode?";
    static String IndexType = "?IndexType?";
    static String tCommRateType = "?tCommRateType?";
    static String tProjectCode = "?tProjectCode?";
    static String SN = "?SN?";
    static String mYearMonth = "?mYearMonth?";
    static String mWageMonth = "?mWageMonth?";
    static String p_month = "?p_month?";
    static String ProjectName = "?ProjectName?";
    static String BeginYearMonth = "?BeginYearMonth?";
    static String AgentNum = "?AgentNum?";
    static String GrantYearMonth = "?GrantYearMonth?";
    static String dBakMonth = "?dBakMonth?";
    static String mor = "?mor?";
    static String agentnum = "?agentnum?";
    static String specialresult = "?specialresult?";
    static String specialgrade = "?specialgrade?";
    static String specialreason = "?specialreason?";
    static String istheyearcountflag = "?istheyearcountflag?";
    static String cManagecom = managecom;
    static String calDate = "?calDate?";
    static String mCalDate = "?mCalDate?";

    /*
     *
     *    "2020-06-29"
     *     "20:00:00"
     */
    public static void main(String[] args) {
        request.setAttribute("MANAGECOM",managecom);
        request.setAttribute("FLAG","");
        request.setAttribute("COMCODE",comcode);
        request.setAttribute("P_CHECKDATE",startDate);
        request.setAttribute("TMCHECKFLAGCODE",tmCheckFlagCode);
        request.setAttribute("PROJECTCODE",projectCode);
        request.setAttribute("P_TMLEVEL",branchLevel);
        request.setAttribute("P_BRANCHATTR",branchAttr);
        request.setAttribute("P_BRANCHNAME",branchName);
        request.setAttribute("P_QCOMCODE",comcode);
        request.setAttribute("P_COMCODE",comcode);
        request.setAttribute("AGENTNUM",agentNum);
        request.setAttribute("P_AGENTNUM",agentNum);
        request.setAttribute("P_AGENTCODE",agentCode);
        request.setAttribute("AGENTCODE",agentCode);
        request.setAttribute("CONTNO",contNo);
        request.setAttribute("P_YEARMONTH",yearmonth);
        request.setAttribute("YEARMONTH",yearmonth);
        request.setAttribute("AGENTNAME",agentName);
        request.setAttribute("INDEXCALNO",indexCalNo);
        request.setAttribute("WAGESTATENAME",wageStateName);
        request.setAttribute("MONTH",indexCalNo);
        request.setAttribute("P_WAGENO",wageNo);
        request.setAttribute("P_MONTH",p_month);

        StringBuffer insertSql = new StringBuffer("\n");
        StringBuffer updateZZSQL = new StringBuffer();
        String sql =
                " select "+
                        " a.TransSeq,a.ContNo,a.BankType,a.CardType, " +
                        " a.ThisPayYear,a.ANP,a.Prem,a.CumulativeMoney,a.CumulativePayCount, " +
                        " a.ThisBussType,a.ThisMoney,a.ThisTotalPayCount,a.IsRE,a.ThisFirstYearMoney, " +
                        " a.ThisSecondYearMoney,a.ThisThirdYearMoney,a.ThisFourthYearMoney,a.ThisFifthYearMoney,a.ThisAboveFifthYearMoney, "+
                        " a.SurrenderDate,a.Remark "+
                        " from TM_CGB_ChargeComparison a where a.csvdate=to_date('"+mCalDate+"','yyyy-MM-dd')";
        System.out.println(sql);

    }

    private static String getCurrentDate() {
        return "?currentDate?";
    }

    private static String getCurrentTime() {
        return "?currentTime?";
    }

    public static String getWherePart(HttpServletRequest request, String fieldName, String controlName)
    {
        return getWherePart(request,fieldName,controlName,"=","0");
    }
    public static String getWherePart(HttpServletRequest request, String fieldName, String controlName, String strOperate)
    {
        return getWherePart(request,fieldName,controlName,strOperate,"0");
    }

    public static String getWherePart(HttpServletRequest request, String fieldName, String controlName, String strOperate, String fieldType)
    {
        String strWherePart = "";
        String value = "";
        if(controlName == null || controlName.equals("")) controlName = fieldName;
        value=(String)request.getAttribute(controlName.toUpperCase());
        if(value == null || value.equals("")) return strWherePart;
        if(fieldType == null || fieldType.equals("")) fieldType = "0";
        if(strOperate == null || strOperate .equals("")) strOperate = "=";
        if(fieldType.equals("0"))
        {
            // 0:字符型
            if(strOperate.toUpperCase().equals("LIKE"))
            {
                strWherePart = " and " + fieldName.trim() + " like '" + value.trim() + "%'";
            }
            else
            if(strOperate.toUpperCase().equals("IN"))
            {
                System.out.println("*********"+value.trim());
                strWherePart = " and " + fieldName.trim() + "  in  " + value.trim() ;
            }

            else
            if(strOperate.toUpperCase().equals("LIKE2"))
            {
                strWherePart = " and " + fieldName.trim() + " like '%" + value.trim() + "%'";
            }

            else
            {
                strWherePart = " and " + fieldName.trim() +  strOperate.trim() + "'" +  value.trim() + "' ";
            }
        }
        if(fieldType.equals("1"))
        {
            // 1:数字型
            strWherePart = " and " + fieldName.trim() + strOperate.trim() + value.trim() + " ";
        }
        if(fieldType.equals("2")){
            //2：日期类型
            strWherePart = " and " + fieldName.trim() + strOperate.toLowerCase().replace("date", "").trim() + "date'" + value.trim() + "' ";
        }
        return strWherePart;
    }

    /**
     * Copyright (c) 2002 sinosoft  Co. Ltd.
     * All right reserved.
     */

    /**
     * <p>Title: Web业务系统</p>
     * <p>Description: 全局变量区</p>
     * <p>Copyright: Copyright (c) 2002</p>
     * <p>Company: Sinosoft</p>
     * @author YT
     * @version 1.0
     * <p>增加兼业代理机构为满足兼业平台 2006-10-25 周磊</p>
     */

    public class GlobalInput
    {
        /** 当前渠道 */
        public String BranchType;
        /** 当前操作员 */
        public String Operator;
        /** 当前管理机构 */
        public String ManageCom;
        /** 当前登陆机构 */
        public String ComCode;
        /** 兼业代理机构 */
        public String AgentCom;
        /** 所有机构 多个机构中间用“,”隔开*/
        public String ComCodes;

//  /** 当前险种 */
//  public String RiskCode;
//  /** 当前险种版本 */
//  public String RiskVersion;

        public GlobalInput()
        {
        }

        /**
         * 两个GlobalInput对象之间的直接复制
         * @param cGlobalInput 包含有具体值的GlobalInput对象
         */
        public void setSchema(GlobalInput cGlobalInput)
        {
            //获取登陆用户基础信息：用户编码、管理机构等
            BranchType = cGlobalInput.BranchType;
            Operator = cGlobalInput.Operator;
            ComCode = cGlobalInput.ComCode;
            ManageCom = cGlobalInput.ManageCom;
            AgentCom = cGlobalInput.AgentCom;
            ComCodes = cGlobalInput.ComCodes;

        }
    }

}