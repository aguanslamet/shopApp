package com.shopApp.service.transaction.impl;

import com.shopApp.dto.request.TransactionFromTroliRequest;
import com.shopApp.dto.request.TroliRequest;
import com.shopApp.dto.response.DefaultResponse;
import com.shopApp.dto.response.UserDetailsDto;
import com.shopApp.model.Transaction;
import com.shopApp.model.TransactionItemDetail;
import com.shopApp.repository.TransactionItemDetailRepository;
import com.shopApp.repository.TransactionRepository;
import com.shopApp.service.TroliService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TroliServiceImpl implements TroliService {

    @Autowired
    TransactionItemDetailRepository transactionItemDetailRepository;

    @Autowired
    TransactionRepository transactionRepository;
    @Override
    public ResponseEntity<?> addTroli(TroliRequest request) {
        Authentication getAuthentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsDto profile = (UserDetailsDto) getAuthentication.getPrincipal();

        TransactionItemDetail transactionItemDetail= transactionItemDetailRepository.findByProductIdWhereStatusTroli(request.getProductId(),EProductStatus.TROLI);

/*      jika product yg sama berdasarkan
        id product sudah ada dengan status masih di di cart maka
        hanya akan mengubah quantity dan harganya saja
 */
        try {
            if (transactionItemDetail != null) {
                Long updateQuantity = transactionItemDetail.getQuantity() + 1;
                Long updatePrice = updateQuantity * transactionItemDetail.getBasePrice();
                transactionItemDetail.setQuantity(updateQuantity);
                transactionItemDetail.setTotalPrice(updatePrice);
                transactionItemDetailRepository.save(transactionItemDetail);
            } else {
                // jika belum ada di cart add new prduct in cart
                TransactionItemDetail transactionItemDetails = new TransactionItemDetail();
                transactionItemDetails.setProductId(request.getProductId());
                transactionItemDetails.setProductname(request.getTitle());
                transactionItemDetails.setBasePrice(request.getPrice());
                transactionItemDetails.setTotalPrice(request.getPrice());
                transactionItemDetails.setProductStatus(EProductStatus.TROLI);
                transactionItemDetails.setQuantity(1L);

                transactionItemDetailRepository.save(transactionItemDetails);
            }
            return new ResponseEntity<>(
                    DefaultResponse.builder()
                            .statusCode(200)
                            .message("Product "+ request.getTitle()+ " Success")
                            .data(request)
                            .build(),
                    HttpStatus.OK);
        }catch (Exception e){
            throw new IllegalArgumentException("error");
        }
    }

    @Override
    public ResponseEntity<?> removeProductInTroli(Long idTroli) {
        try {
        TransactionItemDetail transactionItemDetail= transactionItemDetailRepository.findByIdWhereStatusTroli(idTroli,EProductStatus.TROLI);
        transactionItemDetailRepository.delete(transactionItemDetail);
            return new ResponseEntity<>(
                    DefaultResponse.builder()
                            .statusCode(200)
                            .message("Remove "+ transactionItemDetail.getProductname()+ " Success")
                            .build(),
                    HttpStatus.OK);
    } catch (Exception e){
            throw new IllegalArgumentException("error");
        }
    }
    @Override
    public ResponseEntity<?> checkoutProductInCart(List<TransactionFromTroliRequest> request) {
        try {
            Transaction transaction = new Transaction();
            transaction.setPaymentStatus(EPaymentStatus.PENDING);
            Long totalAmount = 0L;
            List<TransactionItemDetail> itemDetails =new ArrayList<>();
            for (TransactionFromTroliRequest prod : request) {
                TransactionItemDetail transactionItemDetails = transactionItemDetailRepository.getById(prod.getId());
                transactionItemDetails.setProductStatus(EProductStatus.CHECKOUT);
                totalAmount += transactionItemDetails.getTotalPrice();
                transactionItemDetails.setTransaction(transaction);
                itemDetails.add(transactionItemDetails);
            }
            Date currentDate = new Date();
            transaction.setExpiredAt( new Date(currentDate.getTime()  + 45 * 60000 ));
            transaction.setTotalAmount(totalAmount);
            transactionRepository.save(transaction);
            transactionItemDetailRepository.saveAll(itemDetails);

            Map<String,Object> mapResponse = new HashMap<>();
            mapResponse.put("mess","transaksi berhasil dibuat");
            mapResponse.put("product",request.stream().toList());
            mapResponse.put("price",totalAmount);
            mapResponse.put("status",EPaymentStatus.PENDING);
            return new ResponseEntity<>(
                    DefaultResponse.builder()
                            .statusCode(200)
                            .message("Success")
                            .data(mapResponse)
                            .build(),
                    HttpStatus.OK);
        }catch (Exception e){
            throw new IllegalArgumentException("error");
        }
    }

    public ResponseEntity<?> listCart() {
        Authentication getAuthentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsDto profile = (UserDetailsDto) getAuthentication.getPrincipal();
        try {
            List<TransactionItemDetail> transactionItemDetails = transactionItemDetailRepository.findAllByidWhereTypetransaction(EProductStatus.TROLI,profile.getId());
            return new ResponseEntity<>(
                    DefaultResponse.builder()
                            .statusCode(200)
                            .message("Success")
                            .data(transactionItemDetails.stream().toList())
                            .build(),
                    HttpStatus.OK);
        } catch (Exception e) {
            throw new IllegalArgumentException("error");
        }
    }

    }
