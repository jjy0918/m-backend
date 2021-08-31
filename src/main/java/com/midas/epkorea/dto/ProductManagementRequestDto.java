package com.midas.epkorea.dto;

import com.midas.epkorea.exception.RequiredValueException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductManagementRequestDto {

    @Min(value = 1,message = "1보다 작은 값은 사용할 수 없습니다.")
    @Max(value = 12,message = "12보다 큰 값은 사용할 수 없습니다.")
    private int category;

    @NotBlank
    private String name;

    private String image;

    private String catalog;

    private boolean expose;

    private List<TableListRequestDto> tableList;

    private String editor;

    public void check() throws RequiredValueException {
        if(name==null || (category<1)){
            throw new RequiredValueException();
        }
    }


}
