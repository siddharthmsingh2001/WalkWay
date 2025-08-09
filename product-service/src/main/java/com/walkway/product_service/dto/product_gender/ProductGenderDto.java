package com.walkway.product_service.dto.product_gender;

import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ProductGenderDto {
    private Byte productGenderId;
    private String productGenderName;
    private LocalDateTime productGenderCreatedAt;
    private LocalDateTime productGenderUpdatedAt;
}