package com.midas.epkorea.dto.response;

import com.midas.epkorea.domain.manager.Manager;
import com.midas.epkorea.dto.ManagerListDto;
import com.midas.epkorea.dto.PageDto;
import com.midas.epkorea.dto.ResponseDto;
import com.midas.epkorea.util.ChangeDateTime;
import lombok.*;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ManagerResponseDto extends ResponseDto {

    private PageDto pageInfo;

    private List<ManagerListDto> data;

    public ManagerResponseDto(Page<Manager> page) {
        this.pageInfo = new PageDto(page);
        data = new ArrayList<>();
        page.getContent().forEach(manager ->
                data.add(
              ManagerListDto.builder()
                      .id(manager.getId())
                      .belong(manager.getBelong())
                      .name(manager.getName())
                      .no(manager.getNo())
                      .registrationDate(ChangeDateTime.timestampToString(manager.getRegistrationDate()))
                      .build()
            )
         );
    }

}
