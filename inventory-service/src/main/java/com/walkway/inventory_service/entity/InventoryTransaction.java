package com.walkway.inventory_service.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "inventory_transaction")
public class InventoryTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer transactionId;

    @ManyToOne
    @JoinColumn(name = "transaction_stock_id", referencedColumnName = "stockId", nullable = false)
    private InventoryStock stock;

    @Column(name = "transaction_change", nullable = false)
    private Integer transactionChange;

    @Column(name = "transaction_reference_id", nullable = false)
    private Integer transactionReferenceId;

    @Enumerated(EnumType.STRING)
    @Column(name = "transaction_reference_type", nullable = false)
    private ReferenceType transactionReferenceType;

    @Column(name = "transaction_timestamp")
    private LocalDateTime transactionTimestamp;

    @PrePersist
    protected void onCreate() {
        transactionTimestamp = LocalDateTime.now();
    }
}
