package org.hong.quotesapi.handler;

import static java.util.Objects.nonNull;
import static org.hong.quotesapi.constants.AppConstants.RESPONSE_WRAPPER_ANNOTATION;

import org.hong.quotesapi.annotation.ResponseWrapper;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResponseWrapperHandler implements ResponseBodyAdvice {

    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert requestAttributes != null;
        HttpServletRequest request = requestAttributes.getRequest();
        ResponseWrapper responseWrapperAnn = (ResponseWrapper) request.getAttribute(RESPONSE_WRAPPER_ANNOTATION);
        System.out.println("responseWrapperAnn=" + nonNull(responseWrapperAnn));
        return responseWrapperAnn != null;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter methodParameter, MediaType mediaType, Class aClass,
                                  ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        System.out.println("ResponseWrapperHandler::beforeBodyWrite");

        //TODO global error control

        return ResponseEntity.ok().body(body);
    }
}
