package com.walkway.product_service.dto.product_image;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.Accessors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Accessors(chain = true)
public class ProductImageCreateDto {

    @NotBlank(message = "Product Id cannot be blank")
    private Integer productItemId;

    @NotBlank(message = "Product Image url cannot be blank")
    private String productImageUrl;

}
