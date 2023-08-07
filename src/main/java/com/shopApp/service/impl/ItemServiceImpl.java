package com.shopApp.service.impl;

import com.shopApp.dto.request.ItemPostRequest;
import com.shopApp.dto.response.DefaultResponse;
import com.shopApp.model.Items;
import com.shopApp.repository.ItemRepository;
import com.shopApp.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    ItemRepository itemRepository;
    @Override
    public ResponseEntity<?> addItem(ItemPostRequest request) {
        try {
            if (request.getId() != null) {
                Items itemsEdit = itemRepository.getById(request.getId());
                itemsEdit.setId(request.getId());
                itemsEdit.setTitle(request.getTitle());
                itemsEdit.setDescription(request.getDescription());
                itemsEdit.setImage(request.getImage());
                itemsEdit.setPrice(request.getPrice());
                itemRepository.save(itemsEdit);
            } else {
                Items items = new Items();
                items.setTitle(request.getTitle());
                items.setDescription(request.getDescription());
                items.setImage(request.getImage());
                items.setPrice(request.getPrice());
                itemRepository.save(items);
            }
        }catch (Exception e){
            throw new IllegalArgumentException("error");
        }
        return new ResponseEntity<>(
                DefaultResponse.builder()
                        .statusCode(200)
                        .message("Success")
                        .build(),
                HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getItem(Integer page, Integer perPage, String keyword) {
        try {
            Pageable pageable = PageRequest.of(page, perPage);
            Page<Items> items = itemRepository.findByTitleContainingIgnoreCase(keyword, pageable);
            return new ResponseEntity<>(
                    DefaultResponse.builder()
                            .statusCode(200)
                            .message("Success")
                            .data(items)
                            .build(),
                    HttpStatus.OK);
        } catch (Exception e){
            throw new IllegalArgumentException("error");
        }
    }
}
