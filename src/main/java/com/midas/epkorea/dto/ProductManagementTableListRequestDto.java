package com.midas.epkorea.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductManagementTableListRequestDto {

    private String title;
    private String text;

    public boolean checkTableListItem(){
        return title!=null && text!=null;
    }

}
