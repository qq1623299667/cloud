package com.example.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RestController;

/**
 * 鉴权+限流+日志
 * Spring Cloud Gateway根据作用范围划分为GatewayFilter和GlobalFilter，二者区别如下：
 *      GatewayFilter : 需要通过spring.cloud.routes.filters 配置在具体路由下，
 *          只作用在当前路由上或通过spring.cloud.default-filters配置在全局，作用在所有路由上
 *      GlobalFilter : 全局过滤器，不需要在配置文件中配置，作用在所有的路由上，
 *          最终通过GatewayFilterAdapter包装成GatewayFilterChain可识别的过滤器，
 *          它为请求业务以及路由的URI转换为真实业务服务的请求地址的核心过滤器，不需要配置，系统初始化时加载，
 *          并作用在每个路由上。
 * @author Jia Shi
 * @since 2020/12/8
 */
@SpringBootApplication
@RestController
@EnableEurekaClient
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }
}
