package com.walkway.product_service.dto.product;

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
public class ProductCreateDto {

    @NotBlank(message = "Product Name cannot be blank")
    private String productName;

    @NotNull(message = "Product needs to be a part of Category")
    private Byte productCategoryId;

    @NotNull(message = "Product must be assigned a Gender")
    private Byte productGenderId;

    private String productDescription;

}
