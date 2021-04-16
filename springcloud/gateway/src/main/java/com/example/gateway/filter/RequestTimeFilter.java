package com.example.gateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 时间过滤器
 * @author Jia Shi
 * @since 2020/12/11
 */
@Slf4j
public class RequestTimeFilter implements GatewayFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String beginTimeName = "beginTime";
        exchange.getAttributes().put(beginTimeName, System.currentTimeMillis());
        return chain.filter(exchange).then(
                Mono.fromRunnable(()->{
                    Long beginTime = exchange.getAttribute(beginTimeName);
                    if(beginTime !=null){
                        log.info(exchange.getRequest().getURI().getRawPath()+" : "+(System.currentTimeMillis() - beginTime)+"ms");
                    }
                })
        );
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
