package com.midas.epkorea.dto;

import com.midas.epkorea.validation.Password;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDto {

    @NotBlank
    private String id;

    @NotBlank
    private String name;

    @Password
    private String password;

    @NotBlank
    private String phoneNumber;

}
