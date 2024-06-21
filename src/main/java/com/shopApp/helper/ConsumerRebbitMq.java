package com.shopApp.helper;

import com.shopApp.model.Transaction;
import com.shopApp.service.impl.PaymentAppImpl;
import com.shopApp.service.transaction.impl.TransactionServiceImpl;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ConsumerRebbitMq {

    @Autowired
    private TransactionServiceImpl transactionService;
    @Autowired
    private PaymentAppImpl paymentApp;

    @RabbitListener(queues = {"${rabbitmq.queue.result}"})
    public void consumeReult(Map<String, Object> data){
        transactionService.cellbackService(data);
    }
    @RabbitListener(queues = {"${rabbitmq.queue.order}"})
    public void consumeOrder(Map<String, Object> data){
        paymentApp.addOrderInPaymentApp(data);
    }
}
