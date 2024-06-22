package com.shopApp.helper;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ProducerKafka {

    public KafkaTemplate kafkaTemplate;

    public ProducerKafka(KafkaTemplate kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendOrder(Map data){
        kafkaTemplate.send("order",data);
    }
    public void sendResult(Map data){
        kafkaTemplate.send("result",data);
    }
}
