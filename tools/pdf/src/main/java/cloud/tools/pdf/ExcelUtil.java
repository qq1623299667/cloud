package cloud.tools.pdf;

import lombok.extern.slf4j.Slf4j;

/**
 * @author 石佳
 * @date 2021/4/13 17:27
 */
@Slf4j
public class ExcelUtil {
    // excel 加密
    public static void setPassWord(String path, String password) {
        com.spire.xls.Workbook wb = new com.spire.xls.Workbook();
        wb.loadFromFile(path);
        //加密工作簿
        wb.protect(password);
        //保存文档
        wb.saveToFile(path);
    }

    public static void main(String[] args) {
        String path="C:\\Users\\SZK202005\\Desktop\\邮件统计 - 副本.xlsx";
        String password="1234565";
        setPassWord(path,password);
        log.info("finish");
    }
}
