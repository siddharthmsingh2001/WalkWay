package com.walkway.inventory_service.dto.inventory_location;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true)
public class InventoryLocationUpdateDto{

    private String locationAddress;

    @NotBlank(message = "City location is required")
    private String locationCity;

    @NotNull(message = "City postal code is required")
    private Integer locationPostal;

    @NotNull(message = "City state is required")
    private String locationState;

}
