package com.midas.epkorea.service;

import com.midas.epkorea.domain.manager.Manager;
import com.midas.epkorea.domain.manager.ManagerRepository;
import com.midas.epkorea.domain.managerlog.ManagerLog;
import com.midas.epkorea.domain.managerlog.ManagerLogRepository;
import com.midas.epkorea.domain.user.User;
import com.midas.epkorea.domain.user.UserRespository;
import com.midas.epkorea.dto.request.LoginRequestDto;
import com.midas.epkorea.dto.ResponseDto;
import com.midas.epkorea.dto.response.UserResponseDto;
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
    private final ManagerLogRepository managerLogRepository;

    private void setManagerLog(boolean successLogin,String id,String ip,String sessionId){
        ManagerLog managerLog= ManagerLog.builder()
                .id(id)
                .ip(ip)
                .sessionId(sessionId)
                .successLogin(successLogin)
                .build();

        managerLogRepository.save(managerLog);
    }

    public void setManagerLogFailure(String id, String ip, String sessionId){
        setManagerLog(false,id,ip,sessionId);
    }

    public void setManagerLogSuccess(String id, String ip, String sessionId){
        setManagerLog(true,id,ip,sessionId);
    }


    public ResponseEntity<ResponseDto> login(LoginRequestDto requestDto,String ip,String sessionId) throws UserNotPresentException {
        if(requestDto.getType().equals("MANAGER") || requestDto.getType().equals("ADMIN")){
            Optional<Manager> managerOptional = managerRepository.findByIdAndPassword(requestDto.getId(),requestDto.getPassword());

            setManagerLog(managerOptional.isPresent(),requestDto.getId(),ip,sessionId);
            UserResponseDto userResponseDto = new UserResponseDto(managerOptional.orElseThrow(UserNotPresentException::new));
            ResponseDto responseDto = ResponseDto.builder()
                    .data(userResponseDto)
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
