package com.midas.epkorea.util;

import com.midas.epkorea.exception.RequiredValueException;
import lombok.*;
import org.joda.time.DateTime;

import java.sql.Timestamp;

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

    public void checkRequiredValue() throws RequiredValueException {
        if(this.id==null || this.name==null || this.belong == null || this.phoneNumber==null || this.password==null){
            throw new RequiredValueException();
        }
    }

}
