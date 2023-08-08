package com.shopApp.service.transaction;

import com.shopApp.dto.request.ItemPostRequest;
import com.shopApp.dto.request.TroliRequest;
import org.springframework.http.ResponseEntity;

public interface TransactionService {

    ResponseEntity<?> checkOutInListItemProduct(ItemPostRequest request);
}
