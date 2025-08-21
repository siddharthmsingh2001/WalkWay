package com.walkway.product_service.repo;

import com.walkway.product_service.entity.ProductItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductItemRepository extends JpaRepository<ProductItem, Integer> {

    Optional<ProductItem> findByProductItemCode(Integer productItemCode);
    boolean existsByProductItemCode(Integer productItemCode);
}
