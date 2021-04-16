package com.example.zuul.server.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.POST_TYPE;

@Component
@Slf4j
public class PostFilter extends ZuulFilter {
//    ErrorFilter类是在pre、route、post过滤器中发生类异常才会触发error过滤器执行，
//    并且触发类error后再执行post过滤器。所以说这里可以再pre过滤器中设置权限，
//    不符合可以抛出异常，这样再error接收到可以做相应的处理。
//    过滤器让代码不容易理解,特别是如果别人不懂的话,很容易找不到问题点
    @Override
    public String filterType() {
        return POST_TYPE;
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        log.info(">>> 这是 PostFilter <<<");
        //从RequestContext获取上下文
        RequestContext ctx = RequestContext.getCurrentContext();
        ctx.getResponse().setCharacterEncoding("utf-8");
        //获取上下文中保存的responseBody
        String responseBody = ctx.getResponseBody();
        //如果responseBody不为空，则说明流程有异常发生
        if (null != responseBody){
            //设定返回状态码
            ctx.setResponseStatusCode(500);
            //替换响应报文
            ctx.setResponseBody(responseBody);
        }
        return null;
    }
}
