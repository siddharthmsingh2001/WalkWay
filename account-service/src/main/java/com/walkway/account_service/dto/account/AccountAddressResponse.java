package com.walkway.account_service.dto.account;

import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@Accessors(chain = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountAddressResponse implements Serializable {
    private UUID addressId;
    private String addressLine1;
    private String addressLine2;
    private String addressCity;
    private String addressState;
    private String addressZipcode;
    private String addressCountry;
}
