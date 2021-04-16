package com.example.gateway.filter;

import com.example.gateway.config.UriConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

/**
 * 对请求进行token 检查，没有token直接异常
 * @author Jia Shi
 * @since 2020/12/23
 */
@Component
@Slf4j
public class RequestTokenFilter implements GlobalFilter, Ordered {
    @Autowired
    private UriConfig uriConfig;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 允许部分页面不需要携带token
        List<String> ignoreUrls = uriConfig.getIgnoreUris();
        if(ignoreUrls.contains(exchange.getRequest().getURI().getPath())){
            return chain.filter(exchange);
        }
        // TODO 改造成校验请求头中的token，而不是参数中的token
        String token = exchange.getRequest().getQueryParams().getFirst("token");
        if(StringUtils.isEmpty(token)){
            log.info(">>> token is empty <<<");
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }
        return chain.filter(exchange);
    }



    @Override
    public int getOrder() {
        return 0;
    }
}
