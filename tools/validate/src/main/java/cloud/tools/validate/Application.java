package cloud.tools.validate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 参考：使用SpringBoot进行优雅的数据验证 https://mp.weixin.qq.com/s?src=11&timestamp=1611836986&ver=2856&signature=aK0xY4NWIM*v1I1p3U8phLW79QKjWlhoWWGrjLJgNsICJcyB2E6B2JlfWpWsO23wVCzdSRXwiRbaWOBbTe1oyGf5DfZPazx9MM8Oq93AlkT8P5XqRTrDnVxzrlZ-NXlN&new=1
 * @author Jia Shi
 * @since 2021/1/28
 */
@EnableSwagger2
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}