package com.walkway.product_service.dto.product_size;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.Accessors;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ProductSizeCreateDto {

    @NotBlank(message = "Product size name must not be blank")
    private String productSizeName;

    @NotNull(message = "Product order must be not be valid")
    private Byte productSizeOrder;

}
