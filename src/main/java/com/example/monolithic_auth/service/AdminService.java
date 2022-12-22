package com.example.monolithic_auth.service;

import com.example.monolithic_auth.domain.UserRepository;
import com.example.monolithic_auth.domain.Users;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {
    private final UserRepository userRepository;

    public AdminService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public List<Users> getUserList(){
        return userRepository.findAll();
    }

    @Transactional
    public long updateUser(Users updateUser){
        Users user = userRepository.findById(updateUser.getId())
                .orElseThrow(() -> new RuntimeException("수정할 사용자를 찾을 수 없음."));
        user.update(updateUser);
        return updateUser.getId();
    }
}
