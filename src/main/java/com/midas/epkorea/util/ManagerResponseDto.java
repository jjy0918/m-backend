package com.midas.epkorea.util;

import com.midas.epkorea.domain.manager.Manager;
import lombok.*;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

@Data
@ToString
public class ManagerResponseDto {

    private PageDto page;

    private List<ManagerListDto> managerList;

    public ManagerResponseDto(Page page) {
        this.page = new PageDto(page);
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
