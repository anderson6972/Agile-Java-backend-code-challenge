package com.primeit.agilejava.adapter.in.rest.dto;

import com.primeit.agilejava.domain.common.CustomGender;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CustomUserDTO {

    @NotNull
    private String username;
    @NotNull
    private String name;
    @Email
    @NotNull
    private String email;
    @NotNull
    private CustomGender gender;
    private String photo;
    private String country;
    private String state;
    private String city;
}
