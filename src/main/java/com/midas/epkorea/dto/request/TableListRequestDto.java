package com.midas.epkorea.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TableListRequestDto {

    private String title;
    private String text;

    public boolean checkTableListItem(){
        return title!=null && text!=null && !title.isEmpty() && !text.isEmpty();
    }

}
