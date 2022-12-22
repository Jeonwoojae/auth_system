package com.example.monolithic_auth.service;

import com.example.monolithic_auth.domain.Users;
import com.example.monolithic_auth.domain.UserRepository;
import com.example.monolithic_auth.domain.UserRequestDto;
import com.example.monolithic_auth.domain.UserResponseDto;
import com.example.monolithic_auth.utils.EncryptionUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.example.monolithic_auth.utils.EncryptionUtils.matches;


@Service
@RequiredArgsConstructor
public class LoginService {

    private final UserRepository userRepository;

    public boolean login(UserRequestDto user){
        Users nowUsers = userRepository.findByEmail(user.getEmail());
        if (nowUsers == null) return false;

        if (!matches(user.getPassword(), nowUsers.getPassword())){
            return false;
        }
        return true;
    }
}
