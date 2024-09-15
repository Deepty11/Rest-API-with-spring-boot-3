package com.example.webSecurityRESTAPI.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.Getter;

@Data
public class AuthenticationRequest {

    @NotEmpty(message = "{required.field}")
    @Email(message = "{invalid.field}")
    String username;

    @NotEmpty(message = "{required.field}")
    String password;

    public String getUsername() {
        return username;
    }

}
