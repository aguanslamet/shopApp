package com.shopApp.controller;

import com.shopApp.dto.request.ItemPostRequest;
import com.shopApp.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/item")
public class ItemController {

    @Autowired
    ItemService itemService;

    @PostMapping("/addAndEditItem")
    public ResponseEntity<?> addItem(@RequestBody ItemPostRequest request){
        return itemService.addItem(request);
    }
}
