package com.walkway.account_service.repo;

import com.walkway.account_service.entity.Account;
import com.walkway.account_service.entity.AccountProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AccountProfileRepository extends JpaRepository<AccountProfile, UUID> {
    Optional<AccountProfile> findByAccountId(Account account);
}
