package com.walkway.inventory_service.dto.inventory_location;

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
public class InventoryLocationDto {
    private Integer locationId;
    private String locationAddress;
    private String locationCity;
    private Integer locationPostal;
    private String locationState;
}
