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

        String requestURI = httpRequest.getRequestURI();
        log.info("request URI = {}", requestURI);

        if(requestURI.equals("/login") || requestURI.equals("/logout")){
            chain.doFilter(request, response);
            return;
        }

        Cookie[] cookies = httpRequest.getCookies();
        boolean isAuth = false;

        if(cookies != null){
            for(Cookie cookie : cookies){
                if("userId".equals(cookie.getName())){
                    isAuth = true;
                    break;
                }
            }
        }

        if(!isAuth){
            log.warn("인증 실패");
            httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            httpResponse.getWriter().write("로그인 필요");
            return;
        }

        chain.doFilter(request, response);
    }
}
