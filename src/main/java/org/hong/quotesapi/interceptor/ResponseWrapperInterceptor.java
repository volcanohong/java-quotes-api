package org.hong.quotesapi.interceptor;

import static org.hong.quotesapi.constants.AppConstants.RESPONSE_WRAPPER_ANNOTATION;

import lombok.extern.slf4j.Slf4j;
import org.hong.quotesapi.annotation.ResponseWrapper;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * Response interceptor
 *
 * @author hong
 */
@Slf4j
@Component
public class ResponseWrapperInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            final HandlerMethod handlerMethod = (HandlerMethod) handler;
            final Class<?> clazz = handlerMethod.getBeanType();
            final Method method = handlerMethod.getMethod();
            //Set an attribute when the current request needs a general response wrapper
            if (clazz.isAnnotationPresent(ResponseWrapper.class)) {
                log.info("preHandle:" + clazz.getName());
                request.setAttribute(RESPONSE_WRAPPER_ANNOTATION, clazz.getAnnotation(ResponseWrapper.class));
            } else if (method.isAnnotationPresent(ResponseWrapper.class)) {
                log.info("preHandle:" + method.getName());
                request.setAttribute(RESPONSE_WRAPPER_ANNOTATION, method.getAnnotation(ResponseWrapper.class));
            }
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        log.info("postHandle::()");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                                Exception ex) throws Exception {
        log.info("afterCompletion::()");
    }
}
