package com.midas.epkorea.domain.manager;

import com.midas.epkorea.dto.ManagerRequestDto;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Table
@Entity
@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
public class Manager {

    @Id
    private int no;
    private Timestamp registrationDate;

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


    public void createManagerByManagerRequest(ManagerRequestDto managerRequestDto){

        this.id= managerRequestDto.getId();
        this.name=managerRequestDto.getName();
        this.belong= managerRequestDto.getBelong();
        this.phoneNumber=managerRequestDto.getPhoneNumber();
        this.password=managerRequestDto.getPassword();

        this.pmUpsSts = managerRequestDto.isPmUpsSts();
        this.pmCooling = managerRequestDto.isPmCooling();
        this.pmLighting = managerRequestDto.isPmLighting();
        this.pmRailroad = managerRequestDto.isPmRailroad();

        this.cmUpsSts = managerRequestDto.isCmUpsSts();
        this.cmCooling = managerRequestDto.isCmCooling();
        this.cmLighting = managerRequestDto.isCmLighting();
        this.cmRailroad = managerRequestDto.isCmRailroad();

    }


}
