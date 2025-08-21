package com.walkway.product_service.repo;

import com.walkway.product_service.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Byte> {
    Optional<ProductCategory> ProductCategoryName(String productCategoryName);
    boolean existsByProductCategoryName(String name);
}
