package com.cqie.interceptor;

import com.cqie.utils.Result;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.cqie.utils.JwtTokenUtil;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class TokenInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        response.setCharacterEncoding("utf-8");
        //如果已经登录，不拦截
        if (null != token) {
            //验证token是否正确
            boolean result = JwtTokenUtil.verify(token);
            if (!result) {
                String error = new ObjectMapper().writeValueAsString(Result.Failed(null, 500, "登录过期！！"));
                response.setContentType("application/json;charset=utf-8");
                response.getWriter().println(error);
                return false;
            }
            return true;
        } else {
            String error = new ObjectMapper().writeValueAsString(Result.Failed(null, 500, "没有登录！！"));
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().println(error);
            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }

}