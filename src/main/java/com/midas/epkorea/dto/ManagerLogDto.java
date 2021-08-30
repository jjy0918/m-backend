package com.midas.epkorea.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
