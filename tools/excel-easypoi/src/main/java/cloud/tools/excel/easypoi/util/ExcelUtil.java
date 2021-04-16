package cloud.tools.excel.easypoi.util;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.params.ExcelExportEntity;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

/**
 * @author 石佳
 * @since 2020/08/06
 */
@Slf4j
public class ExcelUtil {
    /**
    *  导入excel
    * @param file 文件
    * @param headerRows 表头行数
    * @param pojoClass 类对象
    * @author 石佳
    * @since 2020/8/6
    */
    public static <T> List<T> importExcel(MultipartFile file, int headerRows, Class<T> pojoClass) {
        if(file==null){
            return null;
        }
        ImportParams importParams = new ImportParams();
        importParams.setHeadRows(headerRows);
        List<T> list;
        try {
            list = ExcelImportUtil.importExcel(file.getInputStream(),pojoClass,importParams);
        }catch (Exception e){
            e.printStackTrace();
            log.error("[monitor][表单功能]", e);
            return null;
        }
        return list;
    }

    /**
    *  导出excel
    * @param list 对象列表
    * @param pojoClass 对象类型
    * @param fileName 生成的文件名
    * @author 石佳
    * @since 2020/8/6
    */
    public static <T> void exportExcel(List<T> list,Class<T> pojoClass, String fileName, HttpServletResponse response) {
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(),pojoClass,list);
        if (workbook != null);
        downLoadExcel(fileName, response, workbook);
    }

    /**
    *  下载excel
    * @param fileName 文件名
    * @author 石佳
    * @since 2020/8/6
    */
    private static void downLoadExcel(String fileName, HttpServletResponse response, Workbook workbook) {
        try {
            response.setCharacterEncoding("UTF-8");
            response.setHeader("content-Type", "application/vnd.ms-excel");
            response.setHeader("Content-Disposition",
                    "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
            workbook.write(response.getOutputStream());
        } catch (IOException e) {
            log.error("[monitor][IO][表单功能]", e);
        }
    }

    /**
    *  动态导出excel
    * @param headList 表头列
    * @param dataList 内容列
    * @author 石佳
    * @since 2020/8/6
    */
    public static void exportExcelDynamics(List<ExcelExportEntity> headList, List<Map<String, Object>> dataList,String fileName,HttpServletResponse response) {
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(), headList,dataList);
        if (workbook != null);
        downLoadExcel(fileName, response, workbook);
    }
}
