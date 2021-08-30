package com.midas.epkorea.dto;

import com.midas.epkorea.domain.construction.Construction;
import com.midas.epkorea.domain.managerlog.ManagerLog;
import com.midas.epkorea.util.ChangeDateTime;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

@Data
public class ConstructionResponseDto {
    private PageDto pageInfo;

    private List<ConstructionDto> constructionDtoList;


    public ConstructionResponseDto(Page page) {
        this.pageInfo = new PageDto(page);
        this.constructionDtoList = new ArrayList<>();
        page.getContent().forEach(construction ->{
            Construction con = (Construction) construction;
            constructionDtoList.add(
                    ConstructionDto.builder()
                            .no(con.getNo())
                            .category(con.getCategoryDetail().getChild())
                            .registrationDate(ChangeDateTime.TimestampToString(con.getRegistrationDate()))
                            .name(con.getName())
                            .expose(con.isExpose())
                            .build()
            );
        } );
    }

}
