package com.cardanonft.interceptor;

import com.cardanonft.api.service.UserLogService;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.DispatcherType;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LogRequestInterceptor implements HandlerInterceptor {

    private final UserLogService userLogService;

    public LogRequestInterceptor(UserLogService userLogService) {
        this.userLogService = userLogService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        if (DispatcherType.REQUEST.name().equals(request.getDispatcherType().name()) && request.getMethod().equals(HttpMethod.GET.name())) {
            userLogService.logRequest(request, null);
        }

        return true;
    }
}