package com.midas.epkorea.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductManagementListDto {

    private int no;

    private String category;

    private String name;

    private String catalog;

    private String registrationDate;

    private boolean expose;


}
