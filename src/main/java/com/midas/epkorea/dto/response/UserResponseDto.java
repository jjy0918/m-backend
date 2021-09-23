package com.midas.epkorea.dto.response;

import com.midas.epkorea.domain.manager.Manager;
import lombok.Getter;

@Getter
public class UserResponseDto {

    private String id;

    private String name;

    private String role;

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

    public UserResponseDto(Manager manager){
        this.id=manager.getId();
        this.name=manager.getName();
        this.role=manager.getRole();
        this.pmUpsSts = manager.isPmUpsSts();
        this.pmEss=manager.isPmEss();
        this.pmCooling= manager.isCmCooling();
        this.pmLighting=manager.isPmLighting();
        this.pmRailroad=manager.isPmRailroad();
        this.cmUpsSts=manager.isCmUpsSts();
        this.cmEss=manager.isCmEss();
        this.cmCooling= manager.isPmCooling();
        this.cmLighting=manager.isCmLighting();
        this.cmRailroad=manager.isCmRailroad();
    }



}
