package com.walkway.product_service.dto.product;

import lombok.*;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ProductDto {

    private Integer productId;
    private String productName;
    private Byte productCategoryId;
    private Byte productGenderId;
    private String productDescription;
    private LocalDateTime productCreatedAt;
    private LocalDateTime productUpdatedAt;

}
