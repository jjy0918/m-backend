package com.midas.epkorea.util;

import com.midas.epkorea.domain.manager.Manager;
import lombok.*;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

@Data
public class ManagerResponseDto {

    private PageDto pageInfo;

    private List<ManagerListDto> managerList;

    public ManagerResponseDto(Page page) {
        this.pageInfo = new PageDto(page);
        managerList = new ArrayList<>();
        page.getContent().forEach(manager ->{
            Manager m = (Manager) manager;
            managerList.add(
              ManagerListDto.builder()
                      .id(m.getId())
                      .belong(m.getBelong())
                      .name(m.getName())
                      .no(m.getNo())
                      .registrationDate(ChangeDateTime.TimestampToString(m.getRegistrationDate()))
                      .build()
            );
        } );
    }

}
