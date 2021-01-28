package org.hong.quotesapi.config;

import org.hong.quotesapi.interceptor.ResponseWrapperInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfiguration implements WebMvcConfigurer {

    @Autowired
    private ResponseWrapperInterceptor responseWrapperInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(responseWrapperInterceptor);
    }
}
