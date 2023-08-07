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
    @GetMapping()
    public ResponseEntity<?> getItem(@RequestParam(name ="page", defaultValue = "0") Integer page,
                                     @RequestParam(name = "per-page",defaultValue = "10") Integer perPage,
                                     @RequestParam(name = "keyword")String keyword){
        return itemService.getItem(page,perPage,keyword);
    }

    @PostMapping("/addAndEditItem")
    public ResponseEntity<?> addItem(@RequestBody ItemPostRequest request){
        return itemService.addItem(request);
    }


}
