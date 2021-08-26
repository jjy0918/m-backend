package com.midas.epkorea.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ManagerLogDto {
    private int no;

    private String loginDate;

    private String id;

    private String ip;

    private String sessionId;

    private boolean successLogin;
}
