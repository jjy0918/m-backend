package com.midas.epkorea.domain.managerlog;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Getter
@Builder
@ToString
@Table
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class ManagerLog {

    @Id
    private int no;

    private Timestamp loginDate;

    private String id;

    private String ip;

    private String sessionId;

    private boolean successLogin;

}
