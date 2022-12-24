package com.example.monolithic_auth.controller;

import com.example.monolithic_auth.domain.Users;
import com.example.monolithic_auth.dto.UserJoinDto;
import com.example.monolithic_auth.service.UserService;
import com.example.monolithic_auth.utils.JwtProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Value("${jwt.expired-time}")
    private String exp;
    private final UserService userService;
    private JwtProvider jwtProvider;


    @Autowired
    public UserController(UserService userService, JwtProvider jwtProvider){
        this.jwtProvider = jwtProvider;
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


    @PutMapping
    public long updateUser(HttpServletResponse response, @RequestBody Users newUser){
        long res = userService.updateUser(newUser);
        log.info("토큰 갱신");
        String token = jwtProvider.createToken(newUser);

        log.info("쿠키에 토큰 저장");
        Cookie cookie = new Cookie("token",token);
        cookie.setDomain("localhost");
        cookie.setPath("/"); //모든 위치에서 저장
        cookie.setMaxAge(Integer.parseInt(exp));
        cookie.setSecure(false);
        response.addCookie(cookie);

        return res;
    }


}
