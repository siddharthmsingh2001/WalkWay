package com.walkway.product_service.repo;

import com.walkway.product_service.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    Optional<Product> findByProductName(String productName);
    boolean existsByProductName(String productName);
}
