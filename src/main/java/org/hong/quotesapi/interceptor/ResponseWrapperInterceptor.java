package org.hong.quotesapi.interceptor;

import static org.hong.quotesapi.constants.AppConstants.RESPONSE_WRAPPER_ANNOTATION;

import org.hong.quotesapi.annotation.ResponseWrapper;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

@Component
public class ResponseWrapperInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            final HandlerMethod handlerMethod = (HandlerMethod) handler;
            final Class<?> clazz = handlerMethod.getBeanType();
            final Method method = handlerMethod.getMethod();
            //获取此请求，是否需要返回值包装，设置一个属性标记
            if (clazz.isAnnotationPresent(ResponseWrapper.class)) {
                System.out.println("preHandle:" + clazz.getName());
                request.setAttribute(RESPONSE_WRAPPER_ANNOTATION, clazz.getAnnotation(ResponseWrapper.class));
            } else if (method.isAnnotationPresent(ResponseWrapper.class)) {
                System.out.println("preHandle:" + method.getName());
                request.setAttribute(RESPONSE_WRAPPER_ANNOTATION, method.getAnnotation(ResponseWrapper.class));
            }
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                                Exception ex) throws Exception {

    }
}
