package com.midas.epkorea.dto;

import com.midas.epkorea.domain.managerlog.ManagerLog;
import com.midas.epkorea.util.ChangeDateTime;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

@Data
public class ManagerLogResponseDto {
    private PageDto pageInfo;

    private List<ManagerLogDto> managerLogList;

    public ManagerLogResponseDto(Page page) {
        this.pageInfo = new PageDto(page);
        managerLogList = new ArrayList<>();
        page.getContent().forEach(managerLog ->{
            ManagerLog ml = (ManagerLog) managerLog;
            managerLogList.add(
                    ManagerLogDto.builder()
                            .id(ml.getId())
                            .no(ml.getNo())
                            .loginDate(ChangeDateTime.TimestampToString(ml.getLoginDate()))
                            .ip(ml.getIp())
                            .sessionId(ml.getSessionId())
                            .successLogin(ml.isSuccessLogin())
                            .build()
            );
        } );
    }
}
