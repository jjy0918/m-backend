package com.midas.epkorea.service;

import com.midas.epkorea.domain.manager.Manager;
import com.midas.epkorea.domain.manager.ManagerRepository;
import com.midas.epkorea.domain.user.User;
import com.midas.epkorea.domain.user.UserRespository;
import com.midas.epkorea.dto.LoginRequestDto;
import com.midas.epkorea.dto.ResponseDto;
import com.midas.epkorea.exception.UserNotPresentException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final ManagerRepository managerRepository;
    private final UserRespository userRespository;


    public ResponseEntity<ResponseDto> login(LoginRequestDto requestDto) throws UserNotPresentException {
        if(requestDto.getType().equals("MANAGER") || requestDto.getType().equals("ADMIN")){
            Optional<Manager> managerOptional = managerRepository.findById(requestDto.getId());

            ResponseDto responseDto = ResponseDto.builder()
                    .data(managerOptional.orElseThrow(UserNotPresentException::new))
                    .message("login success")
                    .build();

            return new ResponseEntity<>(responseDto,HttpStatus.OK);

        }
        else if(requestDto.getType().equals("USER")){
            Optional<User> userOptional = userRespository.findById(requestDto.getId());

            ResponseDto responseDto = ResponseDto.builder()
                    .data(userOptional.orElseThrow(UserNotPresentException::new))
                    .message("login success")
                    .build();

            return new ResponseEntity<>(responseDto,HttpStatus.OK);
        }
        else{
            ResponseDto responseDto = ResponseDto.builder()
                    .message("Type Not Found")
                    .build();

            return new ResponseEntity<>(responseDto, HttpStatus.NOT_FOUND);
        }
    }


}
