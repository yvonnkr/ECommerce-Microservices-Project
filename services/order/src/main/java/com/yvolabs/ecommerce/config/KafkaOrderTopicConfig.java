package com.yvolabs.ecommerce.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

/**
 * @author Yvonne N
 * @version 1.0
 * @since 21/07/2024
 */
@Configuration
public class KafkaOrderTopicConfig {

    @Bean
    public NewTopic ordersTopic() {
        return TopicBuilder
                .name("order_topic")
                .build();
    }
}
