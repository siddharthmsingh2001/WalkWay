package com.walkway.account_service.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Accessors(chain = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "account_address")
@Entity
public class AccountAddress {

    @Id
    @GeneratedValue
    @Column(name = "address_id", updatable = false, nullable = false)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", referencedColumnName = "account_id", nullable = false)
    private Account accountId;

    @Column(name = "address_line1", nullable = false)
    private String addressLine1;

    @Column(name = "address_line2")
    private String addressLine2;

    @Column(name = "address_city", nullable = false)
    private String addressCity;

    @Column(name = "address_state", nullable = false)
    private String addressState;

    @Column(name = "address_zipcode", nullable = false)
    private String addressZipcode;

    @Column(name = "address_country", nullable = false)
    private String addressCountry;

    @Column(name = "address_created_at", updatable = false)
    private LocalDateTime addressCreatedAt;

    @Column(name = "address_updated_at")
    private LocalDateTime addressUpdatedAt;

    @PrePersist
    protected void onCreate() {
        addressCreatedAt = addressUpdatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        addressUpdatedAt = LocalDateTime.now();
    }

}
