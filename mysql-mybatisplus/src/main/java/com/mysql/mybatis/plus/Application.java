package com.mysql.mybatis.plus;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * SpringBoot整合mybatis-plus，参考：https://blog.csdn.net/fxbin123/article/details/86907711
 * 利用MpGenerator生成代码，快速生成脚手架，然后在这个基础上快速迭代开发
 * 动态数据源切换，参考：https://cloud.tencent.com/developer/article/1497712
 * 建表语句存放在 resource/init文件夹中
* @author 石佳
* @since 2020/6/22
*/
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}