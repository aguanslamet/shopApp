package com.shopApp.helper;

import com.shopApp.service.impl.PaymentAppImpl;
import com.shopApp.service.transaction.impl.TransactionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ConsumerKafka {
@Autowired
private TransactionServiceImpl transactionService;

@Autowired
private PaymentAppImpl paymentApp;

    @KafkaListener(topics = "result", groupId = "order")
    public void consumeReult(Map<String, Object> data){
        transactionService.cellbackService(data);
    }
    @KafkaListener(topics = "order", groupId = "order")
    public void consumeOrder(Map<String, Object> data){
        paymentApp.addOrderInPaymentApp(data);
    }
}
