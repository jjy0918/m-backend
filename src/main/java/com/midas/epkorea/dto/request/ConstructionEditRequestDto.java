package com.midas.epkorea.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ConstructionEditRequestDto extends ConstructionRequestDto{

    private List<Integer> deleteDetailImageNum;
    private List<Integer> deleteTableNum;
    private List<Integer> deleteBannerNum;

}
