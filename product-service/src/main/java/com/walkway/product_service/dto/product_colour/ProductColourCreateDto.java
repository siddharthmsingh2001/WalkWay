package com.walkway.product_service.dto.product_colour;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.Accessors;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ProductColourCreateDto {

    @NotBlank(message = "Product colour name must not be blank")
    private String productColourName;

}
