package com.shopApp.service.transaction.impl;

import com.shopApp.dto.request.ItemPostRequest;
import com.shopApp.dto.response.DefaultResponse;
import com.shopApp.helper.ProducerRebbitMq;
import com.shopApp.model.Transaction;
import com.shopApp.model.TransactionItemDetail;
import com.shopApp.repository.TransactionItemDetailRepository;
import com.shopApp.repository.TransactionRepository;
import com.shopApp.service.transaction.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    TransactionItemDetailRepository transactionItemDetailRepository;

    @Autowired
    TransactionRepository transactionRepository;
    private ProducerRebbitMq producerRebbitMq;

    public TransactionServiceImpl(ProducerRebbitMq producerRebbitMq) {
        this.producerRebbitMq = producerRebbitMq;
    }

    @Override
    public ResponseEntity<?> checkOutInListItemProduct(ItemPostRequest request) {
        try {
            Transaction transaction = new Transaction();
            transaction.setPaymentStatus(EPaymentStatus.PENDING);
            Date currentDate = new Date();
            transaction.setExpiredAt( new Date(currentDate.getTime()  + 45 * 60000 ));
            transaction.setTotalAmount(request.getPrice());
            TransactionItemDetail transactionItemDetail = new TransactionItemDetail();
            transactionItemDetail.setTransaction(transaction);
            transactionItemDetail.setProductStatus(EProductStatus.CHECKOUT);
            transactionItemDetail.setProductId(request.getId());
            transactionItemDetail.setProductname(request.getTitle());
            transactionItemDetail.setBasePrice(request.getPrice());
            transactionItemDetail.setTotalPrice(request.getPrice());
            transactionItemDetail.setQuantity(1L);
            transactionRepository.save(transaction);
            transactionItemDetailRepository.save(transactionItemDetail);

            Map<String,Object> mapResponse = new HashMap<>();
            mapResponse.put("id",transaction.getId());
            mapResponse.put("expiredAt",transaction.getExpiredAt());
            mapResponse.put("mess","transaksi berhasil dibuat");
            mapResponse.put("product",request);
            mapResponse.put("price",request.getPrice());
            mapResponse.put("status",EPaymentStatus.PENDING);
            producerRebbitMq.sendOrder(mapResponse);
            return new ResponseEntity<>(
                    DefaultResponse.builder()
                            .statusCode(200)
                            .message("Success")
                            .data(mapResponse.toString())
                            .build(),
                    HttpStatus.OK);

        }catch (Exception e){
            throw new IllegalArgumentException("error");
        }
    }
    public ResponseEntity<?> cellbackService(Map data) {
        Number id = (Number) data.get("id");
        Transaction transaction = transactionRepository.getFindById(id.longValue());
        Date dt = new Date();
            transaction.setPaymentStatus(EPaymentStatus.PAID);
            transaction.setPaidAt(dt);
            transactionRepository.save(transaction);
        data.put("id",transaction.getId());
        data.put("status_transaction",transaction.getPaymentStatus());
        return new ResponseEntity<>(
                DefaultResponse.builder()
                        .statusCode(200)
                        .message("Pembayaran Berhasil Dilakukan")
                        .build(),
                HttpStatus.OK);
    }

}
