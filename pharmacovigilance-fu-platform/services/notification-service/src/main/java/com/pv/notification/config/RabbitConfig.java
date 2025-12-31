package com.pv.notification.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    public static final String QUEUE_NAME = "followup-notifications";

    @Bean
    public Queue followupQueue() {
        return new Queue(QUEUE_NAME, true);
    }
}
