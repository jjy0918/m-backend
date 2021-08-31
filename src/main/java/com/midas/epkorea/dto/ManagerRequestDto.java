package com.midas.epkorea.dto;

import com.midas.epkorea.exception.RequiredValueException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ManagerRequestDto {
    
    private String id;
    private String name;
    private String belong;
    private String phoneNumber;
    private String password;

    private boolean pmUpsSts;
    private boolean pmCooling;
    private boolean pmLighting;
    private boolean pmRailroad;

    private boolean cmUpsSts;
    private boolean cmCooling;
    private boolean cmLighting;
    private boolean cmRailroad;

    private String role;

    public void checkRequiredValue() throws RequiredValueException {
        if(this.id==null || this.name==null || this.belong == null || this.phoneNumber==null || this.password==null){
            throw new RequiredValueException();
        }
        if(this.role!=null && !this.role.equals("ADMIN") && !this.role.equals("MANAGER") && !this.role.equals("USER")){
            throw new RequiredValueException();
        }
    }

}
