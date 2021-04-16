package com.example.xxjob;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * xxjob使用
 * 执行器
 *      执行器相当于一个或者多个相同名字的执行客户端，executor.appname代表执行器的名字，
 *      执行器需要在web控制台配置并人工选择执行
 *      新增的时候AppName必须和配置中的executor.appname一致
 * 使用文档见src\main\resources\doc\xxjob.html
 * @author Jia Shi
 * @since 2020/12/4
 */
@SpringBootApplication
public class XxjobApplication {

    public static void main(String[] args) {
        SpringApplication.run(XxjobApplication.class, args);
    }

}
