package com.shopApp.repository;

import com.shopApp.model.OrderList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderListRepository extends JpaRepository<OrderList, Long> {
    OrderList findTopByTransactionIdOrderByIdDesc(Long transactionId);
}
