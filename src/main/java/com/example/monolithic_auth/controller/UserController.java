package com.example.monolithic_auth.controller;

import com.example.monolithic_auth.domain.Users;
import com.example.monolithic_auth.dto.UserJoinDto;
import com.example.monolithic_auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/signin")
    public Map<String, Object> save(@RequestBody UserJoinDto newUsers){
        Map<String, Object>response = new HashMap<>();
        Users users = userService.save(newUsers);
        if (users != null){
            response.put("result","SUCCESS");
            response.put("user", users);
        } else {
            response.put("result","FAIL");
            response.put("reason", "회원 가입 실패");
        }
        return response;
    }

    @GetMapping("/{id}")
    public Map<String, Object> findUserById(@PathVariable("id") long id){
        Map<String, Object> response = new HashMap<>();

        Optional<Users> user = userService.findById(id);
        if(user != null) {
            response.put("result","SUCCESS");
            response.put("user",user);
        }else {
            response.put("result", "FAIL");
            response.put("reason", "일치하는 회원 정보가 없습니다.");
        }
        return response;
    }


    @DeleteMapping("/{id}")
    public Map<String, Object> delete(@PathVariable("id") long id){
        Map<String, Object> response = new HashMap<>();
        if(userService.deleteById(id) > 0) {
            response.put("result","SUCCESS");
        } else {
            response.put("result","FAIL");
            response.put("reason","일치하는 회원정보가 없습니다.");
        }
        return response;
    }


}
