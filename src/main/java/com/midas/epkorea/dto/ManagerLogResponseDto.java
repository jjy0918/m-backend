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

    public ManagerLogResponseDto(Page<ManagerLog> page) {
        this.pageInfo = new PageDto(page);
        managerLogList = new ArrayList<>();
        page.getContent().forEach(managerLog ->
            managerLogList.add(
                    ManagerLogDto.builder()
                            .id(managerLog.getId())
                            .no(managerLog.getNo())
                            .loginDate(ChangeDateTime.timestampToString(managerLog.getLoginDate()))
                            .ip(managerLog.getIp())
                            .sessionId(managerLog.getSessionId())
                            .successLogin(managerLog.isSuccessLogin())
                            .build()
            )
        );
    }
}
