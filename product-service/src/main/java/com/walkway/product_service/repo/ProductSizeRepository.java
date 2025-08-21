package com.walkway.product_service.repo;

import com.walkway.product_service.entity.ProductSize;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductSizeRepository extends JpaRepository<ProductSize, Byte> {
    Optional<ProductSize> findByProductSizeName(String productSizeName);
    Optional<ProductSize> findByProductSizeOrder(Byte productSizeOrder);
    boolean existsByProductSizeName(String productSizeName);
    boolean existsByProductSizeOrder(Byte productSizeOrder);
}
