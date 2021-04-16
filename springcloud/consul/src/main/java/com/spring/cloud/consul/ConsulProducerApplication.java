package com.spring.cloud.consul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 参考链接：http://www.ityouknow.com/springcloud/2018/07/20/spring-cloud-consul.html
 * @author 石佳
 * @since 2020/9/17
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ConsulProducerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConsulProducerApplication.class, args);
    }
}
