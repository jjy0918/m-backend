package com.midas.epkorea.exception;

import com.midas.epkorea.dto.ResponseDto;
import org.apache.tomcat.util.http.fileupload.impl.SizeLimitExceededException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
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
        return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({MissingServletRequestParameterException.class,RequiredValueException.class})
    public ResponseEntity<ResponseDto> missingServletRequestParameterException(){
        ResponseDto responseDto = ResponseDto.builder()
                .message("필수 인자가 입력되지 않았습니다.")
                .build();
        return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<ResponseDto> notValidException(MethodArgumentNotValidException ex){
        ResponseDto responseDto = ResponseDto.builder()
                .message(ex.getBindingResult().getAllErrors().get(0).getDefaultMessage())
                .build();
        return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({UserPresentException.class})
    public ResponseEntity<ResponseDto> userPresentException(){
        ResponseDto responseDto = ResponseDto.builder()
                .message("이미 존재하는 계정입니다.")
                .build();
        return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({UserNotPresentException.class})
    public ResponseEntity<ResponseDto> userNotPresentException(){
        ResponseDto responseDto = ResponseDto.builder()
                .message("존재하지 않는 계정입니다.")
                .build();
        return new ResponseEntity<>(responseDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({ProductManagementNotPresentException.class})
    public ResponseEntity<ResponseDto> productManagementNotPresentException(){
        ResponseDto responseDto = ResponseDto.builder()
                .message("항목이 존재하지 않습니다.")
                .build();

        return new ResponseEntity<>(responseDto,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({FileNameNotFoundException.class})
    public ResponseEntity<ResponseDto> fileNameNotFoundException(){
        ResponseDto responseDto = ResponseDto.builder()
                .message("파일이 존재하지 않습니다.")
                .build();

        return new ResponseEntity<>(responseDto,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({SizeLimitExceededException.class})
    public ResponseEntity<ResponseDto> SizeLimitExceededException(){
        ResponseDto responseDto = ResponseDto.builder()
                .message("10MB 보다 큰 파일은 등록할 수 없습니다.")
                .build();

        return new ResponseEntity<>(responseDto,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({FileExtentionException.class})
    public ResponseEntity<ResponseDto> FileExtentionException(FileExtentionException ex){
        ResponseDto responseDto = ResponseDto.builder()
                .message(ex.getMessage())
                .build();

        return new ResponseEntity<>(responseDto,HttpStatus.BAD_REQUEST);
    }

}
