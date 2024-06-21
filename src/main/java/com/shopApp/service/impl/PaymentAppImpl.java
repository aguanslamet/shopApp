package com.shopApp.service.impl;

import com.shopApp.dto.response.DefaultResponse;
import com.shopApp.model.OrderList;
import com.shopApp.model.Transaction;
import com.shopApp.repository.OrderListRepository;
import com.shopApp.service.transaction.impl.EPaymentStatus;
import org.aspectj.weaver.ast.Or;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;

@Service
public class PaymentAppImpl {

    private final OrderListRepository orderListRepository;

    public PaymentAppImpl(OrderListRepository orderListRepository) {
        this.orderListRepository = orderListRepository;
    }


    public ResponseEntity<?> addOrderInPaymentApp(Map<String, Object> data) {
        Number id = (Number) data.get("id");

        OrderList transaction = orderListRepository.findTopByTransactionIdOrderByIdDesc(id.longValue());
        if (transaction != null){
            return new ResponseEntity<>(
                    DefaultResponse.builder()
                            .statusCode(200)
                            .message("Waktu Tunggu Pembayaran sudah habis")
                            .build(),
                    HttpStatus.OK);
        } else {
            transaction = new OrderList();
            transaction.setTransactionId(id.longValue());
            transaction.setExpiredAt(new Timestamp((Long) data.get("expiredAt")));
            orderListRepository.save(transaction);
        }
            return new ResponseEntity<>(
                    DefaultResponse.builder()
                            .statusCode(200)
                            .message("Waktu Tunggu Pembayaran sudah habis")
                            .build(),
                    HttpStatus.OK);
    }
}
