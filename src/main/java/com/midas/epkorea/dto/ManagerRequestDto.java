package com.midas.epkorea.dto;

import com.midas.epkorea.exception.RequiredValueException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ManagerRequestDto {

    @NotBlank
    private String id;
    @NotBlank
    private String name;
    @NotBlank
    private String belong;
    @NotBlank
    private String phoneNumber;
    @NotBlank
    private String password;

    private boolean pmUpsSts;
    private boolean pmCooling;
    private boolean pmLighting;
    private boolean pmRailroad;

    private boolean cmUpsSts;
    private boolean cmCooling;
    private boolean cmLighting;
    private boolean cmRailroad;

    @NotBlank
    private String role;

    public void checkRequiredValue() throws RequiredValueException {
        if(!this.role.equals("ADMIN") && !this.role.equals("MANAGER") && !this.role.equals("USER")){
            throw new RequiredValueException();
        }
    }

}
