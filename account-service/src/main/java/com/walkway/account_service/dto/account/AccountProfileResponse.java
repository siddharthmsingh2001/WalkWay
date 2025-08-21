package com.walkway.account_service.dto.account;

import com.walkway.account_service.entity.Gender;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Accessors(chain = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountProfileResponse implements Serializable {
    private UUID profileId;
    private String profileFirstName;
    private String profileLastName;
    private LocalDate profileDob;
    private Gender profileGender;
}
