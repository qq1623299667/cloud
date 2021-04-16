package cloud.tools.pdf;

import lombok.extern.slf4j.Slf4j;

/**
 * 带？的sql转换成带#的sql
 * @author 石佳
 * @date 2021/2/18 9:43
 */
@Slf4j
public class TransferSQL {
    public static void main(String[] args) {
        String sqlString = "select a.NEWCOMERDOUBLESSN,\n" +
                "        -- 项目名称\n" +
                "       (select b.name from tmmanagecom b where b.managecom = a.managecom),\n" +
                "       -- 1、上线日期 2、培训起始日期\n" +
                "       (select t.codename\n" +
                "          from ldcode t\n" +
                "         where t.codetype = 'f_tmdatetype'\n" +
                "           and a.DATETYPE = t.code),\n" +
                "       -- 职级\n" +
                "       a.agentgrade,\n" +
                "       -- 翻倍开始月份\n" +
                "       a.startmonth,\n" +
                "       -- 第一个月倍数\n" +
                "       a.firstmonthrate,\n" +
                "       -- 第二个月倍数\n" +
                "       a.secondmonthrate,\n" +
                "       -- 第三个月倍数\n" +
                "       a.thirdmonthrate,\n" +
                "       -- 佣金上限金额\n" +
                "       a.maxbonus,\n" +
                "       -- 离职月是否翻倍\n" +
                "       a.outworkrateflag,\n" +
                "       -- 出勤率\n" +
                "       to_char(a.attendance * 100, '9990.99') || '%',\n" +
                "       -- 生效年月\n" +
                "       a.startdate,\n" +
                "       -- 00原始数据 01提交审核 10审核不通过 11审核通过\n" +
                "       (select codename\n" +
                "          from ldcode t\n" +
                "         where t.codetype = 'tmcheckflag'\n" +
                "           and a.flag = t.code),\n" +
                "       -- 审核状态\n" +
                "       a.flag\n" +
                "       -- 新人翻倍设置表\n" +
                "  from TMNEWCOMERDOUBLES a\n" +
                " where 1 = 1\n" +
                "   -- 区域/项目内码\n" +
                "   and (select b.comcode from tmmanagecom b where b.managecom = a.managecom) =\n" +
                "       '?manageCom?'\n" +
                "   -- 审核状态\n" +
                "   and a.flag = '?flag?'\n" +
                "   -- 区域/项目编码\n" +
                "   and (select b.comcode from tmmanagecom b where b.managecom = a.managecom) like\n" +
                "       '?comcode?%'\n" +
                " order by managecom desc, a.flag desc\n";
        String originSql = transferSql(sqlString,"");
        log.info("--------------------------------");
        log.info(originSql);
    }

    private static String transferSql(String sqlString,String addString) {
        // 替换？
        String sql = sqlString.replace("'?", "#{"+addString).replace("?'", "}");
        return sql;
    }
}
