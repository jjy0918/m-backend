package com.midas.epkorea.dto.response;

import com.midas.epkorea.domain.managerlog.ManagerLog;
import com.midas.epkorea.dto.ManagerLogDto;
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
public class ManagerLogResponseDto extends ResponseDto {
    private PageDto pageInfo;

    private List<ManagerLogDto> data;

    public ManagerLogResponseDto(Page<ManagerLog> page) {
        this.pageInfo = new PageDto(page);
        data = new ArrayList<>();
        page.getContent().forEach(managerLog ->
                data.add(
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
