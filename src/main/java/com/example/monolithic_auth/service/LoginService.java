package com.example.monolithic_auth.service;

import com.example.monolithic_auth.domain.Users;
import com.example.monolithic_auth.jwt.JwtProvider;
import com.example.monolithic_auth.repository.UserRepository;
import com.example.monolithic_auth.dto.UserRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.example.monolithic_auth.utils.EncryptionUtils.matches;


@Service
@RequiredArgsConstructor
public class LoginService {

    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;

    public boolean login(UserRequestDto user){
        Users nowUsers = userRepository.findByEmail(user.getEmail());
        if (nowUsers == null) return false;

        if (!matches(user.getPassword(), nowUsers.getPassword())){
            return false;
        }


        return true;
    }
}
