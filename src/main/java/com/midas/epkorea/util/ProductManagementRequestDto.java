package com.midas.epkorea.util;

import com.midas.epkorea.exception.RequiredValueException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductManagementRequestDto {

    private int category;

    private String name;

    private String image;

    private String catalog;

    private boolean expose;

    private List<ProductManagementTableListRequestDto> tableList;

    private String editor;

    public void check() throws RequiredValueException {
        if(name==null || (category<1)){
            throw new RequiredValueException();
        }
    }


}
