package com.midas.epkorea.dto;

import com.midas.epkorea.domain.construction.Construction;
import com.midas.epkorea.util.ChangeDateTime;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

@Data
public class ConstructionResponseDto {
    private PageDto pageInfo;

    private List<ConstructionDto> constructionDtoList;


    public ConstructionResponseDto(Page<Construction> page) {
        this.pageInfo = new PageDto(page);
        this.constructionDtoList = new ArrayList<>();
        page.getContent().forEach(construction ->
            constructionDtoList.add(
                    ConstructionDto.builder()
                            .no(construction.getNo())
                            .category(construction.getCategoryDetail().getChild())
                            .registrationDate(ChangeDateTime.timestampToString(construction.getRegistrationDate()))
                            .name(construction.getName())
                            .expose(construction.isExpose())
                            .build()
            )
        );
    }

}
