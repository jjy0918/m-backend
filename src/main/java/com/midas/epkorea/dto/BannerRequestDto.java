package com.midas.epkorea.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BannerRequestDto {

    private String name;

    private String image;

    public boolean checkBannerItem(){
        return name!=null && image!=null && !name.isEmpty() && !image.isEmpty();
    }

}
