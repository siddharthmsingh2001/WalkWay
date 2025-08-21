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
@Table(name = "product_gender")
@Accessors(chain = true)
@Entity
public class ProductGender {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_gender_id")
    private Byte productGenderId;

    @Column(name = "product_gender_name", nullable = false, unique = true)
    private String productGenderName;

    @Column(name = "product_gender_created_at", updatable = false)
    private LocalDateTime productGenderCreatedAt;

    @Column(name = "product_gender_updated_at")
    private LocalDateTime productGenderUpdatedAt;

    @PrePersist
    protected void onCreate(){
        productGenderCreatedAt = productGenderUpdatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate(){
        productGenderUpdatedAt = LocalDateTime.now();
    }
}
