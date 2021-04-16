package com.example.dataway;

import net.hasor.spring.boot.EnableHasor;
import net.hasor.spring.boot.EnableHasorWeb;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 提供可视化界面，界面访问地址：http://127.0.0.1:8080/interface-ui/#/
 * 快速提供简单业务逻辑接口的操作，原理是将简单接口存到数据库实现数据落地，然后进行解析并执行
 * 提供接口执行，冒烟测试，发布，禁用，删除功能
*  适合快速开发简单逻辑功能需求
* @author 石佳
* @since 2020/4/28
*/
@EnableHasor()
@EnableHasorWeb()
@SpringBootApplication
public class DatawayApplication {

    public static void main(String[] args) {
        SpringApplication.run(DatawayApplication.class, args);
    }

}
