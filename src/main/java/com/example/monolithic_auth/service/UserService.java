package com.example.monolithic_auth.service;

import com.example.monolithic_auth.domain.Role;
import com.example.monolithic_auth.domain.Users;
import com.example.monolithic_auth.dto.UserJoinDto;
import com.example.monolithic_auth.repository.UserRepository;
import com.example.monolithic_auth.dto.UserRequestDto;
import com.example.monolithic_auth.dto.UserResponseDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public Users save(UserJoinDto inputUsers){
        Users users = Users.builder()
                .name(inputUsers.getName())
                .email(inputUsers.getEmail())
                .password(inputUsers.getPassword())
                .role(Role.USER)
                .build();
        return userRepository.save(users);
    }

    @Transactional(readOnly=true)
    public Optional<Users> findById(Long id) {
        return userRepository.findById(id);
    }

    @Transactional
    public Users findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    @Transactional
    public int deleteById(long id){
        Optional<Users> oUser = userRepository.findById(id);
        if(oUser.isPresent()){
            userRepository.delete(oUser.get());
            return 1;
        }
        return 0;
    }

    public UserResponseDto findByLoginForm(final UserRequestDto params){
        UserResponseDto entity = userRepository.findByEmailAndPassword(params.getEmail(),params.getPassword());
        return entity;
    }
}
