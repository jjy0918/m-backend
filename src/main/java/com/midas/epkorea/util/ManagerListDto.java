package com.midas.epkorea.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ManagerListDto {

    private int no;

    private String id;

    private String name;

    private String belong;

    private String registrationDate;


}
