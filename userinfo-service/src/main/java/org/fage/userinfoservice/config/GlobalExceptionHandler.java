package org.fage.userinfoservice.config;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.HandlerMethod;
import sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

/**
 * @author Caizhf
 * @version 1.0
 * @date 2018/10/19 9:25
 * @description
 **/
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     *  统一系统异常处理
     * @param req
     * @param rsp
     * @param e
     * @return
     */
   /* @ExceptionHandler(Exception.class)
    @ResponseBody
    public String handleServerInnerError(HttpServletRequest req, BusinessException e, HandlerMethod handler, WebRequest webRequest, NativeWebRequest nativeWebRequest) {
        System.out.println(handler);
        System.out.println(e.toString());
        handler.getReturnType();
        Method handlerMethod = handler.getMethod();
        Class resType = handlerMethod.getReturnType();
        Class[] reqType = handlerMethod.getParameterTypes();
        //获取泛型
        Type type = handlerMethod.getGenericReturnType();
        ParameterizedTypeImpl parameterizedType = (ParameterizedTypeImpl) type;
        Type[] mapArgs = parameterizedType.getActualTypeArguments();
        System.out.println(mapArgs[0]);



        System.out.println();
        return e.getMessage();
    }*/
}
