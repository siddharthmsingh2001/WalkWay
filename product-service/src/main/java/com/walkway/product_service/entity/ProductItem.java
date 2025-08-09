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
@Table(name = "product_item")
@Accessors(chain = true)
@Entity
public class ProductItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_item_id")
    private Integer productItemId;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "product_id", nullable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "product_item_colour_id" ,referencedColumnName = "product_colour_id", nullable = false)
    private ProductColour productColour;

    @ManyToOne
    @JoinColumn(name = "product_item_size_id" , referencedColumnName = "product_size_id", nullable = false)
    private ProductSize productSize;

    @Column(name = "product_item_original_price", nullable = false)
    private Integer productItemOriginalPrice;

    @Column(name = "product_item_sale_price")
    private Integer productItemSalePrice;

    @Column(name = "product_item_code", nullable = false, unique = true)
    private Integer productItemCode;

    @Column(name = "product_item_created_at")
    private LocalDateTime productItemCreatedAt;

    @Column(name = "product_item_updated_at")
    private LocalDateTime productItemUpdatedAt;

    @PrePersist
    protected void onCreate(){
        productItemCreatedAt = productItemUpdatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate(){
        productItemUpdatedAt = LocalDateTime.now();
    }

}
