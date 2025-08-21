package com.walkway.account_service.repo;

import com.walkway.account_service.entity.Account;
import com.walkway.account_service.entity.AccountAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AccountAddressRepository extends JpaRepository<AccountAddress, UUID> {
    List<AccountAddress> findByAccountId(Account accountId);
}
