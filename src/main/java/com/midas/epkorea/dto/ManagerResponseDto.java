package com.midas.epkorea.dto;

import com.midas.epkorea.domain.manager.Manager;
import com.midas.epkorea.util.ChangeDateTime;
import lombok.*;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

@Data
public class ManagerResponseDto {

    private PageDto pageInfo;

    private List<ManagerListDto> managerList;

    public ManagerResponseDto(Page<Manager> page) {
        this.pageInfo = new PageDto(page);
        managerList = new ArrayList<>();
        page.getContent().forEach(manager ->
            managerList.add(
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
