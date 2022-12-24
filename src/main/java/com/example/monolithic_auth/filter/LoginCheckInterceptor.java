package com.example.monolithic_auth.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 컨트롤러가 호출되기 전에 호출되어
 * 로그인 상태 확인
 */
@Slf4j
public class LoginCheckInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("로그인 확인 인터셉터 실행");

        Cookie cookie = WebUtils.getCookie(request, "token");

        if(cookie == null) {
            log.info("비로그인 사용자로 확인됨");

            response.sendRedirect("/login");
            return false;
        }
        log.info("로그인된 사용자.");
        return true;
    }
}
