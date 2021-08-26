package com.midas.epkorea.util;

import com.midas.epkorea.domain.manager.Manager;
import lombok.*;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
@ToString
public class ManagerResponseDto {

    private PageDto pageDto;

    private List<Manager> managerList;

    public ManagerResponseDto(Page page) {
        this.pageDto = new PageDto(page);

        managerList = page.getContent();
    }
}
