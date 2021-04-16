package cloud.tools.pdf.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.util.Base64Utils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Base64;

/**
 * 自制文件工具类
 * @author 石佳
 * @since 2020/9/17
 */
@Slf4j
public class FileUtil {
    /**
     * 下载文件
     * @param file 文件
     * @param request 请求
     * @param response 回答
     * @author 石佳
     * @since 2020/9/17
     */
    public static void downloadFileAction(File file, HttpServletRequest request, HttpServletResponse response) {
        response.setCharacterEncoding(request.getCharacterEncoding());
        response.setContentType("application/octet-stream");
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            response.setHeader("Content-Disposition", "attachment; filename="+file.getName());
            IOUtils.copy(fis,response.getOutputStream());
            response.flushBuffer();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     *
     * @param base64String base64字符串
     * @param filename 文件名
     * @param request 请求
     * @param response 响应
     * @author 石佳
     * @since 2020/9/17
     */
    public static void downloadFileAction(String base64String,String filename,HttpServletRequest request, HttpServletResponse response) {
        response.setCharacterEncoding(request.getCharacterEncoding());
        response.setContentType("application/octet-stream");
        try {
            response.setHeader("Content-Disposition", "attachment; filename="+filename);
            log.info(">>> 待转成文件下载的base64字符串是 {} <<<",base64String);
            byte[] buf = Base64.getDecoder().decode(base64String);
            IOUtils.copy(new ByteArrayInputStream(buf),response.getOutputStream());
            response.flushBuffer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 将base64转换成文件
     * @param base64Content base64内容
     * @author 石佳
     * @since 2020/9/17
     */
    public static File base64StringToFile(String base64Content){
        BufferedInputStream bis = null;
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;

        File file = new File("/tmp");
        try {
            byte[] bytes = Base64Utils.decodeFromString(base64Content);
            ByteArrayInputStream byteInputStream = new ByteArrayInputStream(bytes);
            bis = new BufferedInputStream(byteInputStream);
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);

            byte[] buffer = new byte[1024];
            int length = bis.read(buffer);
            while(length != -1){
                bos.write(buffer, 0, length);
                length = bis.read(buffer);
            }
            bos.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try{
                if(bis!=null){
                    bis.close();
                }
                if(fos!=null){
                    fos.close();
                }
                if(bos!=null){
                    bos.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return file;
    }

    /**
     * 将文件转化成base64字符串
     * @param file 文件
     * @return java.lang.String
     * @author 石佳
     * @since 2020/9/17
     */
    public static String FileToBase64(File file) {
        FileInputStream fin =null;
        BufferedInputStream bin =null;
        ByteArrayOutputStream baos = null;
        BufferedOutputStream bout =null;
        try {
            fin = new FileInputStream(file);
            bin = new BufferedInputStream(fin);
            baos = new ByteArrayOutputStream();
            bout = new BufferedOutputStream(baos);
            byte[] buffer = new byte[1024];
            int len = bin.read(buffer);
            while(len != -1){
                bout.write(buffer, 0, len);
                len = bin.read(buffer);
            }
            //刷新此输出流并强制写出所有缓冲的输出字节
            bout.flush();
            byte[] bytes = baos.toByteArray();
            return Base64.getEncoder().encodeToString(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            try {
                if(fin!=null){
                    fin.close();
                }
                if(bin!=null){
                    bin.close();
                }
                if(bout!=null){
                    bout.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 将文件转换成base64
     * @param file 文件
     * @author 石佳
     * @since 2020/9/17
     */
    public static String FileToBase64(MultipartFile file) {
        try{
            File tmp = File.createTempFile("tmp", null);
            file.transferTo(tmp);
            String str = FileToBase64(tmp);
            log.info(">>> 编译后的字符串是 {} <<<",str);
            return str;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
