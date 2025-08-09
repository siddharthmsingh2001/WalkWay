package com.walkway.inventory_service.dto.inventory_transaction;

import com.walkway.inventory_service.entity.ReferenceType;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InventoryTransactionDto {

    private Integer transactionId;
    private Integer stockId;
    private Integer transactionChange;
    private Integer transactionReferenceId;
    private ReferenceType transactionReferenceType;
    private LocalDateTime transactionTimestamp;

}
