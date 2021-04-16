package cloud.tools.pdf;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * base64和文件互转的工具类
 * 参考链接：
 * 文件上传：https://blog.csdn.net/winy_lm/article/details/85336191
 * 文件下载：https://blog.csdn.net/zknxx/article/details/72633444
 * @author 石佳
 * @since 2020/9/17
 */
@EnableSwagger2
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}