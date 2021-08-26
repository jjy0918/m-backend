package com.midas.epkorea.util;

import com.midas.epkorea.domain.manager.Manager;
import com.midas.epkorea.domain.managerlog.ManagerLog;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

@Data
@ToString
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
