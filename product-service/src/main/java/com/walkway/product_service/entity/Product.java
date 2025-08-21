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
@Table(name = "product")
@Accessors(chain = true)
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Integer productId;

    @Column(name = "product_name", nullable = false, unique = true)
    private String productName;

    @ManyToOne
    @JoinColumn(name = "product_category_id" ,referencedColumnName = "product_category_id" ,nullable = false)
    private ProductCategory productCategory;

    @ManyToOne
    @JoinColumn(name = "product_gender_id", referencedColumnName = "product_gender_id",nullable = false)
    private ProductGender productGender;

    @Column(name = "product_description", columnDefinition = "TEXT", nullable = true)
    private String productDescription;

    @Column(name = "product_created_at")
    private LocalDateTime productCreatedAt;

    @Column(name = "product_updated_at")
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
