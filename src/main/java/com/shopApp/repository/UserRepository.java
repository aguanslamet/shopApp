package com.shopApp.repository;


import com.shopApp.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users,Long> {
    Optional<Object> findByEmail(String email);

    Optional<Object> findByUsername(String username);
}
