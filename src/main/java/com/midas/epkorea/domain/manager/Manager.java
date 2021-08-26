package com.midas.epkorea.domain.manager;

import lombok.*;

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
public class Manager {

    @Id
    private int no;

    private String id;

    private String name;

    private Timestamp registrationDate;

    private String phoneNumber;

    private String password;

    private String belong;

    private boolean pmUpsSts;
    private boolean pmCooling;
    private boolean pmLighting;
    private boolean pmRailroad;

    private boolean cmUpsSts;
    private boolean cmCooling;
    private boolean cmLighting;
    private boolean cmRailroad;

}
