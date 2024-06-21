package com.shopApp.helper;

import com.shopApp.model.Transaction;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class PublishersRebbitMq {
    @Value("${rabbitmq.exchange.order}")
    private String order;
    @Value("${rabbitmq.order.key}")
    private String orderKey;
    @Value("${rabbitmq.result.key}")
    private String resultKey;
    private RabbitTemplate rabbitTemplate;

    public PublishersRebbitMq(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendOrder(Map<String, Object> transaction) {
        rabbitTemplate.convertAndSend(order, orderKey, transaction);
    }
    public void sendResult(Map<String, Object> transaction) {
        rabbitTemplate.convertAndSend(order, resultKey, transaction);
    }
}
