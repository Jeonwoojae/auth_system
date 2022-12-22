package com.example.monolithic_auth.service;

import com.example.monolithic_auth.domain.Users;
import com.example.monolithic_auth.domain.UserRepository;
import com.example.monolithic_auth.domain.UserRequestDto;
import com.example.monolithic_auth.domain.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final UserRepository userRepository;

    public boolean login(UserRequestDto user){
        Users nowUsers = userRepository.findByEmail(user.getEmail());
        if (nowUsers == null) return false;

        if (!nowUsers.getPassword().equals(user.getPassword())){
            return false;
        }
        return true;
    }
}
