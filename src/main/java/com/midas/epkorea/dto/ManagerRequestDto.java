package com.midas.epkorea.dto;

import com.midas.epkorea.validation.Password;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ManagerRequestDto extends ManagerEditRequestDto {

    @Password
    private String password;


}
