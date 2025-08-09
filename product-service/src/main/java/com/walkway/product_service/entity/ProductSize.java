package com.walkway.product_service.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "product_size")
@Accessors(chain = true)
@Entity
public class ProductSize {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_size_id")
    private Byte productSizeId;

    @Column(name = "product_size_name", nullable = false, unique = true)
    private String productSizeName;

    @Column(name = "product_size_order", nullable = false, unique = true)
    private Byte productSizeOrder;

    @Column(name = "product_size_created_at")
    private LocalDateTime productSizeCreatedAt;

    @Column(name = "product_size_updated_at")
    private LocalDateTime productSizeUpdatedAt;

    @PrePersist
    protected void onCreate(){
        productSizeCreatedAt = productSizeUpdatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate(){
        productSizeUpdatedAt = LocalDateTime.now();
    }

}
