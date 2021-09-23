package com.midas.epkorea.dto;

import com.midas.epkorea.validation.Banner;
import com.midas.epkorea.validation.Category;
import com.midas.epkorea.validation.ImageList;
import com.midas.epkorea.validation.TableList;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ConstructionRequestDto {

    @NotBlank
    private String name;

    @Category
    private int category;

    private String thumbnail;

    @ImageList
    private List<String> detailImage;

    private boolean expose;

    @TableList
    private List<TableListRequestDto> tableList;

    @Banner
    private List<BannerRequestDto> banner;


}
