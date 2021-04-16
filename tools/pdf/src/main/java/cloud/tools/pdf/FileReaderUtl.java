package cloud.tools.pdf;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * 文件读取
 * @author 石佳
 * @date 2021/4/8 20:51
 */
public class FileReaderUtl {
    /**
     * 以行为单位读取文件，常用于读面向行的格式化文件
     */
    public static String readFileByLines(String fileName) {
        File file = new File(fileName);
        BufferedReader reader = null;
        StringBuilder stringBuilder = new StringBuilder();
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            int line = 1;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                // 显示行号
                stringBuilder.append(tempString);
                line++;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        String  path = "D:\\code\\github\\cloud\\cloud\\tools\\pdf\\src\\main\\java\\cloud\\tools\\pdf\\xml\\";
        File file = new File(path);
        File[] files = file.listFiles();
        for(File file1:files){
            String fileName = file1.getName();
            String fileBody = readFileByLines(path + fileName);
        }
    }
}
