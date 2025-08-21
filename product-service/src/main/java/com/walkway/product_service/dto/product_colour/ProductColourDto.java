package com.walkway.product_service.dto.product_colour;

import lombok.*;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ProductColourDto {
    private Byte productColourId;
    private String productColourName;
    private LocalDateTime productColourCreatedAt;
    private LocalDateTime productColourUpdatedAt;
}
