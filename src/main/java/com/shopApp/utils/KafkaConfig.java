package com.shopApp.utils;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {

    public NewTopic orderTopic(){
        return TopicBuilder.name("order").build();
    }
}
