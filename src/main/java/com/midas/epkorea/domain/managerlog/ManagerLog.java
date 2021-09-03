package com.midas.epkorea.domain.managerlog;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Builder
@ToString
@Table
@Entity
@DynamicInsert
@NoArgsConstructor
@AllArgsConstructor
public class ManagerLog {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int no;

    private Timestamp loginDate;

    private String id;

    private String ip;

    private String sessionId;

    private boolean successLogin;

}
