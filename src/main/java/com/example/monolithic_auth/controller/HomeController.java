package com.example.monolithic_auth.controller;

import com.example.monolithic_auth.domain.Users;
import com.example.monolithic_auth.dto.UserRequestDto;
import com.example.monolithic_auth.service.LoginService;
import com.example.monolithic_auth.service.UserService;
import com.example.monolithic_auth.utils.JwtProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@CrossOrigin(origins = "*")
@RestController
@Slf4j
public class HomeController {
    @Value("${jwt.expired-time}")
    private String exp;
    private final LoginService loginService;
    private final JwtProvider jwtProvider;
    private final UserService userService;

    @Autowired
    public HomeController(LoginService loginService,UserService userService, JwtProvider jwtProvider){
        this.loginService = loginService;
        this.jwtProvider = jwtProvider;
        this.userService = userService;
    }

    @PostMapping("/login")
    public String login(
            HttpServletResponse response,
            @RequestBody final UserRequestDto params){

        if(!loginService.login(params)) return null;

        log.info("토큰 발급");
        Users nowUser = userService.findByEmail(params.getEmail());
        String token = jwtProvider.createToken(nowUser);

        log.info("쿠키에 토큰 저장");
        Cookie cookie = new Cookie("token",token);
        cookie.setDomain("localhost");
        cookie.setPath("/"); //모든 위치에서 저장
        cookie.setMaxAge(Integer.parseInt(exp));
        cookie.setSecure(false);
        response.addCookie(cookie);

        return token;
    }
}
