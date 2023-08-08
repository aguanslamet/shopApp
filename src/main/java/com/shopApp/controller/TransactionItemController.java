package com.shopApp.controller;

import com.shopApp.dto.request.ItemPostRequest;
import com.shopApp.dto.request.TroliRequest;
import com.shopApp.service.transaction.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/item")
public class TransactionItemController {
@Autowired
    TransactionService transactionService;

@PostMapping("/checkOutInListItemProduct")
    public ResponseEntity<?> checkOutInListItemProduct(@RequestBody ItemPostRequest request){
        return transactionService.checkOutInListItemProduct(request);
    }

}
