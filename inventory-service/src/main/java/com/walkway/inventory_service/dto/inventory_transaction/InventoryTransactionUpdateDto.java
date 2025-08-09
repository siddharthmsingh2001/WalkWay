package com.walkway.inventory_service.dto.inventory_transaction;

import com.walkway.inventory_service.entity.ReferenceType;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InventoryTransactionUpdateDto {

    @NotNull(message = "Stock Id is necessary for every transaction")
    private Integer stockId;

    @NotNull(message = "Change in Stock is necessary")
    private Integer transactionChange;

    @NotNull(message = "The Reference Id from either the Inventory or Order Service is crucial")
    private Integer transactionReferenceId;

    @NotBlank(message = "The Reference Type is necessary for the the Reference Id")
    private ReferenceType transactionReferenceType;

}
