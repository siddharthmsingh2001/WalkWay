package com.walkway.product_service.dto.product_image;

import com.walkway.product_service.entity.ProductItem;
import lombok.*;
import lombok.experimental.Accessors;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Accessors(chain = true)
public class ProductImageDto {
    private Integer productImageId;
    private Integer productItemId;
    private String productImageUrl;
    private LocalDateTime productCreatedAt;
    private LocalDateTime productUpdatedAt;
}
