package com.walkway.account_service.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Table(name = "account_profile")
@Entity
public class AccountProfile {

    @Id
    @GeneratedValue
    @Column(name = "profile_id", updatable = false, nullable = false)
    private UUID profileId;

    /*
    Using LAZY for @OneToOne is fine, just remember it requires a session when accessed — just in case you're doing any JSON serialization (e.g., in REST responses), it may throw errors unless properly handled (via DTOs).
    and can give error like: org.hibernate.LazyInitializationException: could not initialize proxy - no Session
     */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", referencedColumnName = "account_id", nullable = false, unique = true)
    private Account accountId;

    @Column(name = "profile_first_name")
    private String profileFirstName;

    @Column(name = "profile_last_name")
    private String profileLastName;

    @Column(name = "profile_dob")
    private LocalDate profileDob;

    @Enumerated(EnumType.STRING)
    @Column(name = "profile_gender")
    private Gender profileGender;

    @Column(name = "profile_created_at", updatable = false)
    private LocalDateTime profileCreatedAt;

    @Column(name = "profile_updated_at")
    private LocalDateTime profileUpdatedAt;

    @PrePersist
    protected void onCreate() {
        profileCreatedAt = profileUpdatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        profileUpdatedAt = LocalDateTime.now();
    }

}
