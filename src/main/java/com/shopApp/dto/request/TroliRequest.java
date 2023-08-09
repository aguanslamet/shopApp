package com.shopApp.dto.request;

import com.shopApp.service.transaction.impl.EProductStatus;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TroliRequest {
    private Long productId;
    private String title;
    private Long price;
}
