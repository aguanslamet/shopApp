package com.shopApp.dto.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ItemPostRequest {
    private Long id;
    private String title;
    private String image;
    private String description;
    private Long price;
}
