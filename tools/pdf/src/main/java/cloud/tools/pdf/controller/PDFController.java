package cloud.tools.pdf.controller;

import cloud.tools.pdf.util.FileUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@RestController("/base64")
@Api("pdf")
public class PDFController {
    /**
     * 将base64字符串转化成pdf
     * @param base64 字符串
     * @param request 请求
     * @param response 响应
     * @author 石佳
     * @since 2020/9/17
     */
    @PostMapping("/base64/to/pdf")
    @ApiOperation("base64转换成pdf")
    public void base64ToPDF(@RequestBody String base64, HttpServletRequest request, HttpServletResponse response){
        FileUtil.downloadFileAction(base64,System.currentTimeMillis()+".pdf",request,response);
    }

    /**
     * 传入pdf，将pdf生成字符串
     * @param file pdf文件
     * @return java.lang.String base64编码的字符串
     * @author 石佳
     * @since 2020/9/17
     */
    @PostMapping("/pdf/to/base64")
    @ApiOperation("pdf转换成base64")
    public String pdfToBase64(@RequestParam("file")MultipartFile file){
        return FileUtil.FileToBase64(file);
    }
}
