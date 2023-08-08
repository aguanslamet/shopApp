package com.shopApp.dto.request;

import com.shopApp.service.transaction.impl.EProductStatus;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TroliRequest {
    private Long productId;
    private String title;
    private Long price;
}
