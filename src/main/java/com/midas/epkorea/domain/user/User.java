package com.midas.epkorea.domain.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.sql.Timestamp;

@Table
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@DynamicInsert
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int no;

    private String id;

    private String name;

    private String password;

    private String role;

    private String phoneNumber;

    private Timestamp registrationDate;


}
