package com.wuyunbin.member.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wuyunbin
 */
@Configuration
public class RabbitConfig {

    @Bean
    public Queue info() {
        return new Queue("info");
    }
}
