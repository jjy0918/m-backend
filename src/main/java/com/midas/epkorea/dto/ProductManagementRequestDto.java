package com.midas.epkorea.dto;

import com.midas.epkorea.validation.Category;
import com.midas.epkorea.validation.TableList;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductManagementRequestDto {

    @Category
    private int category;

    @NotBlank
    private String name;

    private String image;

    private String catalog;

    private boolean expose;

    @TableList
    private List<TableListRequestDto> tableList;

    private String editor;


}
