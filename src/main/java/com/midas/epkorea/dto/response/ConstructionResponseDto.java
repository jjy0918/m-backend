package com.midas.epkorea.dto.response;

import com.midas.epkorea.domain.construction.Construction;
import com.midas.epkorea.dto.ConstructionDto;
import com.midas.epkorea.dto.PageDto;
import com.midas.epkorea.dto.ResponseDto;
import com.midas.epkorea.util.ChangeDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConstructionResponseDto extends ResponseDto {
    private PageDto pageInfo;

    private List<ConstructionDto> data;


    public ConstructionResponseDto(Page<Construction> page) {
        super();
        this.pageInfo = new PageDto(page);
        this.data = new ArrayList<>();
        page.getContent().forEach(construction ->
                data.add(
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
