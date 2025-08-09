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
@Table(name = "product_category")
@Accessors(chain = true)
@Entity
public class ProductCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_category_id")
    private Byte productCategoryId;

    @Column(name = "product_category_name", nullable = false)
    private String productCategoryName;

    @ManyToOne
    @JoinColumn(name = "product_parent_category", referencedColumnName = "product_category_id", nullable = true)
    private ProductCategory productParentCategory;

    @Column(name = "product_category_image", nullable = true)
    private String productCategoryImage;

    @Column(name = "product_category_created_at", updatable = false)
    private LocalDateTime productCategoryCreatedAt;

    @Column(name = "product_category_updated_at")
    private LocalDateTime productCategoryUpdatedAt;

    @PrePersist
    protected void onCreate(){
        productCategoryCreatedAt = productCategoryUpdatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate(){
        productCategoryUpdatedAt = LocalDateTime.now();
    }

}
