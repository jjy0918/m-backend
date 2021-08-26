package com.midas.epkorea.exception;

import com.midas.epkorea.util.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler({PageException.class})
    public ResponseEntity<ResponseDto> pageException(){
        ResponseDto responseDto = ResponseDto.builder()
                .message("페이지 번호가 0이하이거나, 전체 페이지 개수보다 많습니다.")
                .build();
        return new ResponseEntity<ResponseDto>(responseDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({MissingServletRequestParameterException.class,RequiredValueException.class})
    public ResponseEntity<ResponseDto> missingServletRequestParameterException(){
        ResponseDto responseDto = ResponseDto.builder()
                .message("필수 인자가 입력되지 않았습니다.")
                .build();
        return new ResponseEntity<ResponseDto>(responseDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({UserPresentException.class})
    public ResponseEntity<ResponseDto> userPresentException(){
        ResponseDto responseDto = ResponseDto.builder()
                .message("이미 존재하는 계정입니다.")
                .build();
        return new ResponseEntity<ResponseDto>(responseDto, HttpStatus.BAD_REQUEST);
    }

}
