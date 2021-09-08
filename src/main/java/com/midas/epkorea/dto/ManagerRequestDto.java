package com.midas.epkorea.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ManagerRequestDto extends ManagerEditRequestDto {

    @NotBlank
    private String password;


}
