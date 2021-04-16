package com.demo.elasticsearch;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * SpringBoot整合Elasticsearch
 * 采用官方推荐的REST Client
 *  参考：https://www.jianshu.com/p/2ff05a83816e
* @author 石佳
* @since 2020/6/22
*/
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}