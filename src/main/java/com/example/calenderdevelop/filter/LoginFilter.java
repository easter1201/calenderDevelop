package com.example.calenderdevelop.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class LoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException
    {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        //요청 로그
        String requestURI = httpRequest.getRequestURI();
        log.info("request URI = {}", requestURI);
        //로그인 / 로그아웃 요청일 경우 필터 무시(인증 예외)
        if(requestURI.equals("/login") || requestURI.equals("/logout")){
            chain.doFilter(request, response);
            return;
        }
        //인증 여부 확인
        Cookie[] cookies = httpRequest.getCookies();
        boolean isAuth = false;
        //쿠키값이 있는 경우
        if(cookies != null){
            for(Cookie cookie : cookies){
                if("userId".equals(cookie.getName())){
                    isAuth = true;
                    break;
                }
            }
        }
        //인증 실패 시 401에러 반환
        if(!isAuth){
            log.warn("인증 실패");
            httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            httpResponse.getWriter().write("로그인 필요");
            return;
        }
        //인증 성공 시 요청 전달
        chain.doFilter(request, response);
    }
}
