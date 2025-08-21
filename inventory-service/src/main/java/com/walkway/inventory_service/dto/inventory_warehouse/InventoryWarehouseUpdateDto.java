package com.walkway.inventory_service.dto.inventory_warehouse;

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
public class InventoryWarehouseUpdateDto {

    @NotNull(message = "Warehouse Name is compulsory")
    private String warehouseName;

    @NotNull(message = "Warehouse location id is necessary")
    private Integer locationId;

}
