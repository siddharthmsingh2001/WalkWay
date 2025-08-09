package com.walkway.account_service.dto.account;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true)
public class AddressCreateRequest implements Serializable {
    private String addressLine1;
    private String addressLine2;
    private String addressCity;
    private String addressState;
    private String addressZipcode;
    private String addressCountry;
}
