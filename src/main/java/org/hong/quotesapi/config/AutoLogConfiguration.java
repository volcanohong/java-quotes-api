package org.hong.quotesapi.config;

import lombok.extern.slf4j.Slf4j;
import org.hong.tool.logger.annotation.EnableLogit;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;

/**
 * Enable auto log
 *
 * @author hong
 */
@Slf4j
@Configuration
@ConditionalOnClass(EnableLogit.class)
@EnableLogit
public class AutoLogConfiguration {
    AutoLogConfiguration() {
        log.info("----- AutoLogConfiguration -----");
    }
}
