package com.walkway.account_service.dto.account;

import com.walkway.account_service.entity.Role;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.UUID;

@ToString
@Getter
@Setter
@Accessors(chain = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountResponse implements Serializable {
    private UUID accountId;
    private String accountEmail;
    private Role accountRole;
    private String accountPhone;
}
