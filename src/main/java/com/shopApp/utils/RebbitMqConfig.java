package com.shopApp.utils;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RebbitMqConfig {
    @Value("${rabbitmq.queue.order}")
    String orderQueue;
    @Value("${rabbitmq.queue.result}")
    String resultQueue;

    @Value("${rabbitmq.exchange.order}")
    String order;

    @Value("${rabbitmq.order.key}")
    String orderKey;

    @Value("${rabbitmq.result.key}")
    String resultKey;

    @Bean
    public Queue queueOrder(){
        return new Queue(orderQueue);
    }
    @Bean
    public Queue queueResult(){
        return new Queue(resultQueue);
    }
    @Bean
    public TopicExchange topicExchange(){
        return new TopicExchange(order);
    }
    @Bean
    public Binding bindingOrder(){
        return BindingBuilder
                .bind(queueOrder())
                .to(topicExchange())
                .with(orderKey);
    }
    @Bean
    public Binding bindingResult(){
        return BindingBuilder
                .bind(queueResult())
                .to(topicExchange())
                .with(resultKey);
    }
    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
    public AmqpTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }

}
