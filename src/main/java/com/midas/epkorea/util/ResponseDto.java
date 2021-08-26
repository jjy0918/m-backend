package com.midas.epkorea.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
public class ResponseDto {

    private Object data;
    private String message;

}
