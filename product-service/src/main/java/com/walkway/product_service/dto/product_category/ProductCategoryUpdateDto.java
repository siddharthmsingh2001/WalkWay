package com.walkway.product_service.dto.product_category;

import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.Accessors;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "product_category")
@Accessors(chain = true)
public class ProductCategoryUpdateDto {
    @NotBlank(message = "Product Category name cannot be empty")
    private String productCategoryName;
    private String productCategoryImage;
    private Byte productParentCategoryId;
}
