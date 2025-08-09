package com.walkway.product_service.dto.product_gender;

import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.Accessors;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ProductGenderCreateDto{

    @NotBlank(message = "Product gender name must not be blank")
    private String productGenderName;

}