package cloud.tools.pdf;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * 文本对比工具类
 * @author 石佳
 * @date 2021/4/8 16:31
 */
@Slf4j
public class TextCompareUtil {
    public static void main(String[] args)  {

        String synonymA = "V_TB_BANKSEND_EPAY";
        String[] splitA = synonymA.replace(" ","").split(",");
        List<String> listA = Arrays.asList(splitA);
        String  path = "D:\\code\\github\\cloud\\cloud\\tools\\pdf\\src\\main\\java\\cloud\\tools\\pdf\\xml\\";
        File file = new File(path);
        File[] files = file.listFiles();
//        List<String> unUsedMappers = new ArrayList<>();
        Set<String> unHaves = new HashSet<>();
        for(File file1:files){
            String fileName = file1.getName();
            String fileBody = FileReaderUtl.readFileByLines(path + fileName);
            // 检查是否有特定字符串
//            if(fileBody.contains("PS_C_HRIS_SMRT_VW")){
//                log.error("{} 含有异常信息",fileName);
//            }
            // 获取字符串中没有包含的数据
            List<String> unHaveList = getUnHaveList(listA, fileBody);
            // 获取字符串中包含的数据
            List<String> unHaveList2 = getUnHaveList(listA, unHaveList);
            unHaveList2.forEach(str->unHaves.add(str));
            // 有不可用的同义词就进行标记
//            if(!unHaveList2.isEmpty()){
//                unUsedMappers.add(fileName);
//                unHaveList2.forEach(str->unHaves.add(str));
//            }
        }
//        log.info("有不能使用的同义词的mapper：{}",unUsedMappers.toString());
//        log.info("不能使用的同义词：{}",unHaves.toString());
        log.info("使用到的同义词：{}",unHaves.toString());
    }

    // 有a，b两个列表，获取a中b没有的数据
    public static List<String> getUnHaveList(List<String> listA,List<String> listB){
        List<String> unHaves = new ArrayList<>();
        for(String str:listA){
            if(!listB.contains(str)){
                unHaves.add(str);
            }
        }
        return unHaves;
    }

    // 有a，b两个列表，获取a中b有的数据
    public static List<String> getHaveList(List<String> listA,List<String> listB){
        List<String> haves = new ArrayList<>();
        for(String str:listB){
            if(listA.contains(str)){
                haves.add(str);
            }
        }
        return haves;
    }

    // 获取a列表中b字符串不包含的数据
    public static List<String> getUnHaveList(List<String> listA,String strB){
        List<String> unHaves = new ArrayList<>();
        for(String str:listA){
            if(!strB.contains(str)){
                unHaves.add(str);
            }
        }
        return unHaves;
    }
}
