package com.walkway.product_service.dto.product_category;

import lombok.*;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ProductCategoryDto {
    private Byte productCategoryId;
    private String productCategoryName;
    private String productCategoryImage;
    private Byte productParentCategoryId;
    private LocalDateTime productCategoryCreatedAt;
    private LocalDateTime productCategoryUpdatedAt;
}
