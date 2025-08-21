package com.walkway.product_service.dto.product_colour;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductColourUpdateDto {
    @NotBlank(message = "Product colour name must not be blank")
    private String productColourName;
}
