package org.hong.quotesapi.config;

import org.hong.tool.logger.annotation.EnableLogit;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(EnableLogit.class)
@EnableLogit
public class AutoLogConfiguration {
    AutoLogConfiguration() {
        System.out.println("----- AutoLogConfiguration -----");
    }
}
