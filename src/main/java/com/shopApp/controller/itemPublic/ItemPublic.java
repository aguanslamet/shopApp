package com.shopApp.controller.itemPublic;

import com.shopApp.dto.request.UserRequest;
import com.shopApp.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/itemPublic")
public class ItemPublic {
    @Autowired
    ItemService itemService;

    @GetMapping()
    public ResponseEntity<?> getItem(@RequestParam(name ="page", defaultValue = "0") Integer page,
                                     @RequestParam(name = "per-page",defaultValue = "10") Integer perPage,
                                     @RequestParam(name = "keyword")String keyword){
        return itemService.getItem(page,perPage,keyword);
    }

    @GetMapping("/simulatorPay")
    public ResponseEntity<?> simulatorPay(@RequestParam(name ="idTransaction") Long transactionId){
        return itemService.simulatorPay(transactionId);
    }
}
