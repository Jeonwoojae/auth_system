package com.example.monolithic_auth.jwt;

import com.example.monolithic_auth.domain.Role;
import com.example.monolithic_auth.domain.Users;
import com.example.monolithic_auth.utils.JwtProvider;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


class JwtProviderTest {
    final JwtProvider jwtProvider = new JwtProvider();

    @Test
    public void 토큰발급_테스트(){
        Users user = Users.builder()
                .email("dnwo@naver.com")
                .name("woojae")
                .password("1234")
                .role(Role.USER)
                .build();

       var jwt = jwtProvider.createToken(user);
        System.out.println(jwt);
    }

    @Test
    public void 토큰역할확인(){
        Users user = Users.builder()
                .email("dnwo@naver.com")
                .name("woojae")
                .password("1234")
                .role(Role.USER)
                .build();

        String jwt = jwtProvider.createToken(user);
        String res = jwtProvider.getSubject(jwt);

        assertEquals("ROLE_USER", res);
    }

}