package com.midas.epkorea.util;

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
        if(title==null || text==null){
            return false;
        }
        return true;
    }

}
