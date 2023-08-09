package com.shopApp.repository;

import com.shopApp.model.TransactionItemDetail;
import com.shopApp.service.transaction.impl.EProductStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransactionItemDetailRepository extends JpaRepository<TransactionItemDetail,Long> {

    @Query("SELECT t FROM TransactionItemDetail t Where t.productId=:productId AND t.productStatus=:eProductStatus")
    TransactionItemDetail findByProductIdWhereStatusTroli(Long productId,EProductStatus eProductStatus);

    @Query("SELECT t FROM TransactionItemDetail t Where t.productStatus=:eProductStatus AND t.createdBy = :id")
    List<TransactionItemDetail> findAllByidWhereTypetransaction(EProductStatus eProductStatus,Long id);

    @Query("SELECT t FROM TransactionItemDetail t Where t.id=:id AND t.productStatus=:eProductStatus")
    TransactionItemDetail findByIdWhereStatusTroli(Long id,EProductStatus eProductStatus);
}
