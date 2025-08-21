package com.walkway.product_service.repo;

import com.walkway.product_service.entity.ProductColour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductColourRepository extends JpaRepository<ProductColour, Byte> {
    Optional<ProductColour> findByProductColourName(String productColourName);
    boolean existsByProductColourName(String productColourName);
}
