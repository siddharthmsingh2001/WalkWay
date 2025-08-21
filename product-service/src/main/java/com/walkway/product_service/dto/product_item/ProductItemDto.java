package com.walkway.product_service.dto.product_item;

import lombok.*;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ProductItemDto {

    private Integer productItemId;
    private Integer productId;
    private Byte productColourId;
    private Byte productSizeId;
    private Integer productItemOriginalPrice;
    private Integer productItemSalePrice;
    private Integer productItemCode;
    private LocalDateTime productItemCreatedAt;
    private LocalDateTime productItemUpdatedAt;

}
