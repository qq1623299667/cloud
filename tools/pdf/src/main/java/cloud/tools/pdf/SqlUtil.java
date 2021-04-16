package cloud.tools.pdf;

import lombok.extern.slf4j.Slf4j;

/**
 * sql工具类
 * @author 石佳
 * @date 2021/3/16 19:29
 */
@Slf4j
public class SqlUtil {
    public static void main(String[] args) {
        String sql="\"CSVDATE\" DATE, \n" +
                "\t\"TRANSSEQ\" VARCHAR2(30), \n" +
                "\t\"CONTNO\" VARCHAR2(30), \n" +
                "\t\"BANKTYPE\" NUMBER(*,0), \n" +
                "\t\"CARDTYPE\" NUMBER(*,0), \n" +
                "\t\"THISPAYYEAR\" NUMBER(*,0), \n" +
                "\t\"ANP\" NUMBER(16,2), \n" +
                "\t\"PREM\" NUMBER(16,2), \n" +
                "\t\"CUMULATIVEMONEY\" NUMBER(16,2), \n" +
                "\t\"CUMULATIVEPAYCOUNT\" NUMBER(*,0), \n" +
                "\t\"THISBUSSTYPE\" VARCHAR2(10), \n" +
                "\t\"THISMONEY\" NUMBER(16,2), \n" +
                "\t\"THISTOTALPAYCOUNT\" NUMBER(*,0), \n" +
                "\t\"ISRE\" NUMBER(*,0), \n" +
                "\t\"THISFIRSTYEARMONEY\" NUMBER(16,2), \n" +
                "\t\"THISSECONDYEARMONEY\" NUMBER(16,2), \n" +
                "\t\"THISTHIRDYEARMONEY\" NUMBER(16,2), \n" +
                "\t\"THISFOURTHYEARMONEY\" NUMBER(16,2), \n" +
                "\t\"THISFIFTHYEARMONEY\" NUMBER(16,2), \n" +
                "\t\"THISABOVEFIFTHYEARMONEY\" NUMBER(16,2), \n" +
                "\t\"SURRENDERDATE\" DATE, \n" +
                "\t\"REMARK\" VARCHAR2(100), \n" +
                "\t\"ENTERACCDATE\" DATE, \n" +
                "\t\"SETTLEMENTSPONSOR\" VARCHAR2(20), \n" +
                "\t\"TYPE\" VARCHAR2(10), \n" +
                "\t\"PAYTODATE\" DATE, \n" +
                "\t\"MAKEDATE\" DATE, \n" +
                "\t\"MAKETIME\" VARCHAR2(8), \n" +
                "\t\"MODIFYDATE\" DATE, \n" +
                "\t\"MODIFYTIME\" VARCHAR2(8), \n" +
                "\t\"BANKCODE\" VARCHAR2(20), \n" +
                "\t\"OPPBANKCODE\" VARCHAR2(20), \n" +
                "\t\"BANKCODEFLAG\" VARCHAR2(10), \n" +
                "\t\"TRANSSOURCESYS\" VARCHAR2(20),";
        String chineseColumn = ddlToEntity(sql);
        log.info(chineseColumn);
    }

    // 获取sql里面的栏位的中文名称
    public static String getChineseColumn(String sql,String splitStr){
        String[] split = sql.split("\n");
        StringBuilder stringBuilder = new StringBuilder();
        for(String str:split){
            if(str.contains(splitStr)){
                stringBuilder.append(str.split(splitStr)[1]).append(",");
            }
        }
        stringBuilder.deleteCharAt(stringBuilder.length()-1);
        return stringBuilder.toString();
    }

    // 数据库ddl生成实体类
    public static String ddlToEntity(String str){
        String[] split = str.split("\n");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("数据库ddl生成实体类结果如下：\r\n");
        for(String column:split){
            String replace = column.replace("\t", "");
            String entity = getColumn(replace);
            stringBuilder.append(entity);
        }
        return stringBuilder.toString();
    }

    private static String getColumn(String str) {
        if(str.contains("DATE")){
            return getColumn(str,"Date");
        }else if(str.contains("VARCHAR2")){
            return getColumn(str,"String");
        }else if(str.contains("NUMBER")){
            return getColumn(str,"BigDecimal");
        }else{
            log.warn("{} 未知类型",str);
            return "";
        }
    }

    private static String getColumn(String str,String typeStr) {
        String column = str.split(" ")[0];
        String replace = column.replace("\"", "");
        replace = replace.toLowerCase();
        return "private "+typeStr+" "+replace+";\r\n";
    }
}
