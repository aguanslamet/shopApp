package com.shopApp.controller;

import com.shopApp.dto.request.TransactionFromTroliRequest;
import com.shopApp.dto.request.TroliRequest;
import com.shopApp.dto.response.UserDetailsDto;
import com.shopApp.service.TroliService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/cart")
public class TroliItemController {

    @Autowired
    TroliService troliService;

    @PostMapping("/addCart")
    public ResponseEntity<?> addTroli(@RequestBody TroliRequest request){
        return troliService.addTroli(request);
    }

    @DeleteMapping("/removeProductInCart")
    public ResponseEntity<?> removeProductInTroli(Long idTroli){
        return troliService.removeProductInTroli(idTroli);
    }

    @PostMapping("/checkoutProductInCart")
    public ResponseEntity<?> checkoutProductInCart(@RequestBody List<TransactionFromTroliRequest> request){
        return troliService.checkoutProductInCart(request);
    }

    @GetMapping("/list")
    public ResponseEntity<?> listCart(){
        return troliService.listCart();
    }


}
