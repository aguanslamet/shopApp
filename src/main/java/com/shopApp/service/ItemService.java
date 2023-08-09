package com.shopApp.service;

import com.shopApp.dto.request.ItemPostRequest;
import org.springframework.http.ResponseEntity;

public interface ItemService {
    ResponseEntity<?> addItem(ItemPostRequest request);
    ResponseEntity<?> getItem(Integer page, Integer perPage, String keyword);
    ResponseEntity<?> simulatorPay(Long transactionId);
}
