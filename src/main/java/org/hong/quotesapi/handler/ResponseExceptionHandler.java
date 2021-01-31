package org.hong.quotesapi.handler;

import lombok.extern.slf4j.Slf4j;
import org.hong.quotesapi.constants.ResponseCode;
import org.hong.quotesapi.entity.error.BaseError;
import org.hong.quotesapi.entity.response.ErrorResponse;
import org.hong.quotesapi.exception.AuthorNotFoundException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import java.util.Collections;

/**
 * Handle controller exception in general
 *
 * ControllerAdvice是一个@Component，用于定义@ExceptionHandler，@InitBinder和@ModelAttribute方法，
 * 适用于所有使用@RequestMapping方法。
 *
 * 可以加package信息 @ControllerAdvice("org.hong.quotesapi.controller")
 *
 * @author hong
 */
@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class ResponseExceptionHandler {

    /**
     * Handle generic exception
     *
     * @return {@link ErrorResponse}
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorResponse handleGenericException(final Exception ex ,final WebRequest request) {
        log.info("ResponseExceptionHandler::handleGenericException...");
        return ErrorResponse.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Collections.emptyList())
                .build();
    }

    /**
     * Handle not found exception
     * @return {@link ErrorResponse}
     */
//    @ExceptionHandler(value = { NotFoundException.class, AuthorNotFoundException.class })
    @ExceptionHandler(AuthorNotFoundException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public Object handleNotFoundException(RuntimeException ex, WebRequest request) {
        log.info("ResponseExceptionHandler::handleNotFoundException...");
        ErrorResponse errorResponse = ErrorResponse.builder()
                .status(HttpStatus.BAD_REQUEST)
                .body(Collections.emptyList())
                .build();
        errorResponse.addError(
                BaseError.builder()
                        .code(ResponseCode.AUTHOR_NOT_FOUND)
                        .message(ex.getMessage())
                        .build()
        );
        return ResponseEntity.badRequest().body(errorResponse);
    }
}
