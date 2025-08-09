package com.walkway.product_service.dto.product_size;

import lombok.*;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ProductSizeDto {

    private Byte productSizeId;
    private String productSizeName;
    private Byte productSizeOrder;
    private LocalDateTime productSizeCreatedAt;
    private LocalDateTime productSizeUpdatedAt;

}
