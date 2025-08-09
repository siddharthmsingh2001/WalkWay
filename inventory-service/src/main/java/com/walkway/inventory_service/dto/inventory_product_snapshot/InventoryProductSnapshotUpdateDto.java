package com.walkway.inventory_service.dto.inventory_product_snapshot;

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
public class InventoryProductSnapshotUpdateDto {

    @NotNull(message = "Product Code is essential to create a Snapshot")
    private Integer productCode;

}
