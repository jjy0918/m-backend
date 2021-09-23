package com.midas.epkorea.service;

import com.midas.epkorea.domain.manager.ManagerRepository;
import com.midas.epkorea.domain.user.User;
import com.midas.epkorea.domain.user.UserRespository;
import com.midas.epkorea.dto.ResponseDto;
import com.midas.epkorea.dto.request.UserRequestDto;
import com.midas.epkorea.exception.UserPresentException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRespository userRespository;

    private final ManagerRepository managerRepository;

    public ResponseEntity<ResponseDto> createUser(UserRequestDto requestDto) throws UserPresentException {
        Optional<User> user = userRespository.findById(requestDto.getId());

        if(user.isPresent()){
            throw new UserPresentException();
        }

        User newUser = User.builder()
                .id(requestDto.getId())
                .name(requestDto.getName())
                .password(requestDto.getPassword())
                .phoneNumber(requestDto.getPhoneNumber())
                .build();

        userRespository.save(newUser);

        ResponseDto responseDto =ResponseDto.builder()
                .message("create User")
                .data(newUser)
                .build();

        return new ResponseEntity<>(responseDto, HttpStatus.OK);

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        return managerRepository.findById(username).orElseThrow(()->new UsernameNotFoundException((username)));
    }
}
