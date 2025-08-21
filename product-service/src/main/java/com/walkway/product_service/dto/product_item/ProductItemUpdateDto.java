package com.walkway.product_service.dto.product_item;

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
public class ProductItemUpdateDto {

    @NotNull(message = "Item must be associated to a product")
    private Integer productId;

    @NotNull(message = "Item must have a colour")
    private Byte productColourId;

    @NotNull(message = "Item must have a size")
    private Byte productSizeId;

    @NotNull(message = "Item must have a MSRP")
    private Integer productItemOriginalPrice;

    private Integer productItemSalePrice;

    @NotNull(message = "Item must have a unique Product Code")
    private Integer productItemCode;

}
