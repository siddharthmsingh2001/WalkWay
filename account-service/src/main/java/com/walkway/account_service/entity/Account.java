package com.walkway.account_service.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "account")
@Accessors(chain = true)
@Entity
public class Account {

    @Id
    @Column(name = "account_id", updatable = false, nullable = false)
    private UUID accountId;

    @Column(name = "account_email", nullable = false, unique = true)
    private String accountEmail;

    @Enumerated(EnumType.STRING)
    @Column(name = "account_role", nullable = false)
    private Role accountRole;

    @Column(name = "account_phone", nullable = true)
    private String accountPhone;

    @Column(name = "account_created_at", updatable = false)
    private LocalDateTime accountCreatedAt;

    @Column(name = "account_updated_at")
    private LocalDateTime accountUpdatedAt;

    @PrePersist
    protected void onCreate(){
        accountCreatedAt = accountUpdatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate(){
        accountUpdatedAt = LocalDateTime.now();
    }
}
