package org.fage.springcloudzuul.zuulfilter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

/**
 * @author Caizhf
 * @version 1.0
 * @date 上午10:36 2018/7/31
 * @description 测试网关过滤器
 **/
@Component
public class AccessFilter extends ZuulFilter{
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public String filterType() {
        return "pre";
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
    public Object run() {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        //此处加入try与catch只是为了展示如果有异常，该怎么返回异常信息给前端
        try {
            logger.info("客户机访问URI：" + request.getRequestURI().toString());
            logger.info("客户机访问的方法：" + request.getMethod());
            Enumeration<String> nameEnum = request.getParameterNames();
            logger.info("客户机请求参数--\\/");
            while (nameEnum.hasMoreElements()) {
                String paramName = nameEnum.nextElement();
                String paramValue = request.getParameter(paramName);
                logger.info("paramName:" + paramName + "|" + "paramValue:" + paramValue);
            }

            String accessToken = request.getParameter("accessToken");
            if (StringUtils.isEmpty(accessToken)) {
                logger.warn("拒绝进入，客户机没有携带AccessToken！");
                requestContext.setResponseStatusCode(401);
                requestContext.setSendZuulResponse(false);
                requestContext.setResponseBody("<h1>客户机没有携带AccessToken，拒绝进入！</h1>");
            }
            logger.info("accessToken=" + accessToken);
        }catch(Exception ex){
            requestContext.set("error.status_code", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            requestContext.set("error.exception", ex);
            requestContext.set("error.message", ex.getMessage());
        }
        return null;
    }
}
