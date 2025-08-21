package com.walkway.product_service.repo;

import com.walkway.product_service.entity.ProductGender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductGenderRepository extends JpaRepository<ProductGender, Byte> {
    Optional<ProductGender> findByProductGenderName(String productGenderName);
    boolean existsByProductGenderName(String productGenderName);
}
