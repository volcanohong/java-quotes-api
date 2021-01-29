package org.hong.quotesapi.config;

import lombok.extern.slf4j.Slf4j;
import org.hong.quotesapi.interceptor.ResponseWrapperInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author hong
 */
@Slf4j
@Configuration
public class InterceptorConfiguration implements WebMvcConfigurer {

    @Autowired
    private ResponseWrapperInterceptor responseWrapperInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        log.info("----- InterceptorConfiguration -----");
        registry.addInterceptor(responseWrapperInterceptor);
    }
}
