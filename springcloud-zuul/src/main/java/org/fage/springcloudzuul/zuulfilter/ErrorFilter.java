package org.fage.springcloudzuul.zuulfilter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;

/**
 * @author Caizhf
 * @version 1.0
 * @date 上午9:14 2018/8/1
 * @description 错误发生时会在这个过滤器处理信息
 **/
@Component
public class ErrorFilter extends ZuulFilter{
    @Override
    public String filterType() {
        return "error";
    }

    @Override
    public int filterOrder() {
        return 10;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext requestContext = RequestContext.getCurrentContext();
        Throwable throwable = requestContext.getThrowable();
        requestContext.set("error.status_code", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        requestContext.set("error.exception", throwable.getCause());
        requestContext.set("error.message", throwable.getMessage());
        return null;
    }
}
