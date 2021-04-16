package cloud.tools.excel.easypoi.controller;

import cloud.tools.excel.easypoi.entity.CustomerList;
import cloud.tools.excel.easypoi.util.ExcelUtil;
import cn.afterturn.easypoi.excel.entity.params.ExcelExportEntity;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 石佳
 * @since 2020/08/06
 */
@RestController("/test")
@Slf4j
public class TestController {

    /**
     * 导入excel
     * 地址在：http://localhost:8080/swagger-ui.html
     */
    @PostMapping("/excel/import")
    @ApiOperation(value = "导入excel", notes = "具体描述")
    public List<CustomerList> importExcel(@RequestParam("file") MultipartFile file) {
        List<CustomerList> personList = ExcelUtil.importExcel(file, 1, CustomerList.class);
        log.info(">>> 解析到的用户数据是 {} <<<", JSON.toJSONString(personList));
        return personList;
    }

    /**
     * 动态导入excel
     * 针对表头是动态的情况的导入
     * 地址在：http://localhost:8080/swagger-ui.html
     */
    @PostMapping("/excel/import/dynamics")
    @ApiOperation(value = "动态导入excel", notes = "具体描述")
    public List<Map<String,Object>> importExcelDynamics(@RequestParam("file") MultipartFile file) {
        List<Map> personList = ExcelUtil.importExcel(file, 1, Map.class);
        log.info(">>> 解析到的用户数据是 {} <<<", JSON.toJSONString(personList));
        // 对象强转
        List<Map<String,Object>> personListMap = new ArrayList<>();
        personList.forEach(personListMap::add);
        return personListMap;
    }


    /**
     * 导出excel
     * 地址在：http://localhost:8080/excel/export
     */
    @GetMapping("/excel/export")
    @ApiOperation(value = "导出excel", notes = "具体描述")
    public void exportExcel(HttpServletResponse response){
        try {
            List<CustomerList> personList = new ArrayList<>();
            for(int i =0;i<10;i++){
                CustomerList customerList = new CustomerList();
                customerList.setName("asdas"+i);
                personList.add(customerList);
            }
            ExcelUtil.exportExcel(personList,CustomerList.class,"客户表.xls",response);
        } catch (Exception e) {
            log.info("getCustomerPage", e);
        }
    }

    /**
     * 动态导出excel
     * 针对表头不是固定的情况的导出
     * 地址在：http://localhost:8080/excel/export/dynamics
     */
    @GetMapping("/excel/export/dynamics")
    @ApiOperation(value = "动态导出excel", notes = "具体描述")
    public void exportExcelDynamics(HttpServletResponse response){
        List<ExcelExportEntity> headList = new ArrayList<>(); //表头列
        // 填充表头列
        fillingExcelHead(headList);
        List<Map<String, Object>> dataList = new ArrayList<>(); //内容列
        // 填充内容列
        fillingExcelData(dataList);
        ExcelUtil.exportExcelDynamics(headList,dataList,"动态excel.xls",response);
    }

    private void fillingExcelData(List<Map<String, Object>> dataList) {
        for(int j=0;j<3;j++){ // 3行数据
            Map<String, Object> stringObjectHashMap = new HashMap<>();
            for(int i=0;i<10;i++){
                String key = i+"级";
                stringObjectHashMap.put(key, RandomUtils.nextInt(0,100));
            }
            dataList.add(stringObjectHashMap);
        }
    }

    private void fillingExcelHead(List<ExcelExportEntity> headList) {
        for(int i=0;i<10;i++){
            ExcelExportEntity excelExportEntity = new ExcelExportEntity(i+"级",i+"级");
            headList.add(excelExportEntity);
        }
    }
}