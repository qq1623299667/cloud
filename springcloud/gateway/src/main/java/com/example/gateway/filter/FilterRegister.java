package com.example.gateway.filter;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 过滤器注册
 * @author Jia Shi
 * @since 2020/12/11
 */
@Configuration
public class FilterRegister {
    @Bean
    public RouteLocator customerRouteLocator(RouteLocatorBuilder builder){
        return builder.routes().route(r->
            r.path("/customer/**")
            .filters(f->f.filter(new RequestTimeFilter()).addRequestHeader("X-Response-Default-Foo","Default-Bar"))
            .uri("http://httpbin.org:80/get")
            .order(0)
            .id("customer_filter_router")
        ).build();
    }
}
