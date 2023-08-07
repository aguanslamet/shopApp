package com.shopApp.repository;

import com.shopApp.model.Items;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Items,Long> {
    Page<Items> findByTitleContainingIgnoreCase(String keyword, Pageable pageable);
}
