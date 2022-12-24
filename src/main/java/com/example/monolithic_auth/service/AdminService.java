package com.example.monolithic_auth.service;

import com.example.monolithic_auth.repository.UserRepository;
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
    public int deleteById(long id){
        Optional<Users> oUser = userRepository.findById(id);
        if(oUser.isPresent()){
            userRepository.delete(oUser.get());
            return 1;
        }
        return 0;
    }
}
