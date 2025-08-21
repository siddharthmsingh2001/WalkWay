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
@Table(name = "product_colour")
@Accessors(chain = true)
@Entity
public class ProductColour {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_colour_id")
    private Byte productColourId;

    @Column(name = "product_colour_name", nullable = false, unique = true)
    private String productColourName;

    @Column(name = "product_colour_created_at")
    private LocalDateTime productColourCreatedAt;

    @Column(name = "product_colour_updated_at")
    private LocalDateTime productColourUpdatedAt;

    @PrePersist
    protected void onCreate(){
        productColourCreatedAt = productColourUpdatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate(){
        productColourUpdatedAt = LocalDateTime.now();
    }

}
