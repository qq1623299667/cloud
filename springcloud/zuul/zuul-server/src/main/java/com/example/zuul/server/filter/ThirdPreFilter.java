package com.example.zuul.server.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

@Component
@Slf4j
public class ThirdPreFilter extends ZuulFilter {
    //    filterType方法：使用返回值设定Filter类型，可以设置pre、route、post、error。
    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    //    filterOrder方法：使用返回值确定当前类型的过滤器执行顺序，同一类型的过滤器，数值越小越先执行，如果每种类型都有一个过滤器的话，返回值写0就好了。
    @Override
    public int filterOrder() {
        return 3;
    }

    //    shouldFilter方法：使用返回值确定该Filter是否执行/生效，可以当作这个过滤器的开关，true：开，false：关。
    @Override
    public boolean shouldFilter() {
        RequestContext ctx = RequestContext.getCurrentContext();
        return (Boolean)ctx.get("logic-is-success");
    }

//    run方法：这里就写你这个过滤器的业务逻辑，想让该过滤器做什么都可以在这里写。
    @Override
    public Object run() throws ZuulException {
        log.info(">>> 这是 ThirdPreFilter <<<");
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        String b = request.getParameter("b");
        //如果 b 参数值为空则进入此逻辑
        if(null == b){
            //对该请求禁止路由，也就是禁止访问下游服务
            ctx.setSendZuulResponse(false);
//            设定responseBody供PostFilter使用
            ctx.setResponseBody("{\"status\":500,\"message\":\" b 参数为空！\"}");
            //logic-is-success保存于上下文，作为同类型下游Filter的执行开关
            ctx.set("logic-is-success", false);
            //到这里此Filter逻辑结束
            return null;
        }
        return null;
    }
}
