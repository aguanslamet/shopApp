package com.shopApp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.shopApp.service.transaction.impl.EProductStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@Table(name = "transaction_item_detail")
public class TransactionItemDetail extends AuditableCart<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "product_id")
    private Long productId;

    @Column(name = "product_name")
    private String productname;

    @Column(name = "base_price")
    private Long basePrice;

    @Column(name = "quantity")
    private Long quantity;

    @Column(name = "total_price")
    private Long totalPrice;

    @Enumerated(EnumType.STRING)
    @Column(name = "product_status")
    private EProductStatus productStatus;

    @JsonBackReference
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "transaction_id")
    private Transaction transaction;
}
