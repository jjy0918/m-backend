package com.midas.epkorea.dto;

import com.midas.epkorea.validation.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ManagerEditRequestDto {

    @NotBlank
    private String id;
    @NotBlank
    private String name;

    private String belong;

    private String phoneNumber;

    private boolean pmUpsSts;
    private boolean pmEss;
    private boolean pmCooling;
    private boolean pmLighting;
    private boolean pmRailroad;

    private boolean cmUpsSts;
    private boolean cmEss;
    private boolean cmCooling;
    private boolean cmLighting;
    private boolean cmRailroad;

    @Role
    private String role;

}
