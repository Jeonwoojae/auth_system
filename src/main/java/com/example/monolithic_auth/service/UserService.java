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



    public UserResponseDto findByLoginForm(final UserRequestDto params){
        UserResponseDto entity = userRepository.findByEmailAndPassword(params.getEmail(),params.getPassword());
        return entity;
    }

    @Transactional
    public long updateUser(Users updateUser){
        Users user = userRepository.findById(updateUser.getId())
                .orElseThrow(() -> new RuntimeException("수정할 사용자를 찾을 수 없음."));
        user.update(updateUser);
        return updateUser.getId();
    }
}
