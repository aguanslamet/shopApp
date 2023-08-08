package com.shopApp.service;

import com.shopApp.dto.request.TransactionFromTroliRequest;
import com.shopApp.dto.request.TroliRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TroliService {
    ResponseEntity<?> addTroli(TroliRequest request);

    ResponseEntity<?> removeProductInTroli(Long idTroli);

    ResponseEntity<?> checkoutProductInCart(List<TransactionFromTroliRequest> request);

    ResponseEntity<?> listCart();
}
