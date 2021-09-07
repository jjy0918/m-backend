package com.midas.epkorea.dto;

import com.midas.epkorea.exception.RequiredValueException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ConstructionRequestDto {

    @NotBlank
    private String name;

    @Min(1)
    @Max(14)
    private int category;

    private String thumbnail;

    private List<String> detailImage;

    private boolean expose;

    private List<TableListRequestDto> tableList;

    private List<BannerRequestDto> banner;

    public void check() throws RequiredValueException {
        if(name==null || (category<1)){
            throw new RequiredValueException();
        }
    }

}
