package com.ham.p2p.website.interceptor;

import com.ham.p2p.base.util.UserContext;
import com.ham.p2p.website.annotation.RequiredLogin;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        RequiredLogin requiredLogin = handlerMethod.getMethodAnnotation(RequiredLogin.class);
        if (requiredLogin != null) {
            if (UserContext.getCurrentUser() == null) {
                response.sendRedirect("/login.html");
                return false;
            }
        }
        return true;
    }
}
