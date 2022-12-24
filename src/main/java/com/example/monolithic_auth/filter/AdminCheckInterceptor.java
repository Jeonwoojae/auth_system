package com.example.monolithic_auth.filter;

import com.example.monolithic_auth.utils.JwtProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@RequiredArgsConstructor
public class AdminCheckInterceptor implements HandlerInterceptor {


    private final JwtProvider jwtProvider;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("관리자 확인 인터셉터 실행");

        Cookie cookie = WebUtils.getCookie(request, "token");
        String token = cookie.getValue();
        String role = jwtProvider.getSubject(token);

        if(!role.equals("ROLE_ADMIN")) {
            log.info("ADMIN 권한이 없음.");

            response.sendRedirect("/home");//초기 화면으로 이동
            return false;
        }
        log.info("ADMIN 권한을 가진 사용자.");
        return true;
    }
}

