package com.walkway.product_service.dto.product_category;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.Accessors;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ProductCategoryCreateDto {
    @NotBlank(message = "Product category name must not be blank")
    private String productCategoryName;
    private String productCategoryImage;
    private Byte productParentCategoryId;
}
