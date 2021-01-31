package org.hong.quotesapi.handler;

import static java.util.Objects.nonNull;
import static org.hong.quotesapi.constants.AppConstants.RESPONSE_WRAPPER_ANNOTATION;

import lombok.extern.slf4j.Slf4j;
import org.hong.quotesapi.annotation.ResponseWrapper;
import org.hong.quotesapi.entity.response.BaseResponse;
import org.hong.quotesapi.entity.response.ErrorResponse;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * Response wrapper handler
 *
 * When there is an exception, it will trigger {@link ResponseExceptionHandler} first,
 * and the body received could be {@link ErrorResponse}.
 *
 * @author hong
 */
@Slf4j
@Order(0)
@ControllerAdvice
public class ResponseWrapperHandler implements ResponseBodyAdvice {

    /**
     * Check if the request has the attribute `RESPONSE_WRAPPER_ANNOTATION`
     *
     * Since in the {@link org.hong.quotesapi.interceptor.ResponseWrapperInterceptor},
     * the attribute `RESPONSE_WRAPPER_ANNOTATION` is set when the class/method has {@link ResponseWrapper}
     *
     * @return true when the current method is supported (annotated)
     */
    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        log.info("ResponseWrapperHandler::supports...");
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert nonNull(requestAttributes);
        HttpServletRequest request = requestAttributes.getRequest();
        ResponseWrapper responseWrapperAnn = (ResponseWrapper) request.getAttribute(RESPONSE_WRAPPER_ANNOTATION);
        log.info("responseWrapperAnn=" + nonNull(responseWrapperAnn));
        return nonNull(responseWrapperAnn);
    }

    /**
     * Wrap the response
     *
     * @param body object received
     * @return {@link BaseResponse} or {@link ErrorResponse}
     */
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter methodParameter, MediaType mediaType, Class aClass,
                                  ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        log.info("ResponseWrapperHandler::beforeBodyWrite...");
        if (body instanceof ErrorResponse) {
            return body;
        }
        return BaseResponse.builder().status(HttpStatus.OK).body(body).build();
    }
}
