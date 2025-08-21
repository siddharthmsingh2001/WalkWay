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
@Table(name = "product_image")
@Accessors(chain = true)
@Entity
public class ProductImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_image_id")
    private Integer productImageId;

    @ManyToOne
    @JoinColumn(name = "product_item_id",referencedColumnName = "product_item_id",nullable = false)
    private ProductItem productItem;

    @Column(name = "product_image_url", nullable = false)
    private String productImageUrl;

    @Column(name = "product_image_created_at")
    private LocalDateTime productCreatedAt;

    @Column(name = "product_image_updated_at")
    private LocalDateTime productUpdatedAt;

    @PrePersist
    protected void onCreate(){
        productCreatedAt = productUpdatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate(){
        productUpdatedAt = LocalDateTime.now();
    }

}