package com.walkway.product_service.dto.product_gender;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductGenderUpdateDto {

    @NotBlank(message = "Product gender name must not be blank")
    private String productGenderName;

}