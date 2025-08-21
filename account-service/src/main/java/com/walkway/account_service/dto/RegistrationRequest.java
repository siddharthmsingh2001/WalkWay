package com.walkway.account_service.dto;


import com.walkway.account_service.entity.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true)
@ToString
public class RegistrationRequest implements Serializable {

    @NotBlank(message = "Account Email mandatory")
    @Email(message = "Account Email is not well formated")
    private String accountEmail;

    @NotBlank(message = "Account Password mandatory")
    @Size(min = 7, message = "Password Length should be minimum 7 character")
    private String accountPassword;

    @NotBlank(message = "Account Phone number is mandatory")
    @Pattern(regexp = "^[6-9]\\d{9}$", message = "Phone number must be 10 digits")
    private String accountPhone;

    @NotBlank(message = "Account Role is mandatory")
    private Role accountRole;

    @Size(min = 2, message = "First Name Length should be minimum 2 character")
    @NotBlank(message = "Account First Name is mandatory")
    private String accountFirstName;

    @Size(min = 2, message = "Last Name Length should be minimum 2 character")
    @NotBlank(message = "Account Last Name is mandatory")
    private String accountLastName;
}
