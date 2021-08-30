package com.midas.epkorea.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConstructionDto {

    private int no;

    private String category;

    private String name;

    private String registrationDate;

    private boolean expose;



}
