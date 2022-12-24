package com.example.monolithic_auth.service;

import com.example.monolithic_auth.domain.Users;
import com.example.monolithic_auth.repository.UserRepository;
import com.example.monolithic_auth.dto.UserRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.example.monolithic_auth.utils.EncryptionUtils.matches;


@Service
@RequiredArgsConstructor
@Slf4j
public class LoginService {

    private final UserRepository userRepository;

    public boolean login(UserRequestDto user){
        Users nowUsers = userRepository.findByEmail(user.getEmail());
        if (nowUsers == null){
            log.info("email이 틀렸습니다.");
            return false;
        }
        if (!matches(user.getPassword(), nowUsers.getPassword())){
            log.info("비밀번호가 틀렸습니다.");
            return false;
        }
        log.info("로그인 성공");

        return true;
    }
}
