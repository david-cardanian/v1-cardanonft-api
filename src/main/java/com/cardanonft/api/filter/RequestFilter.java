package com.cardanonft.api.filter;

import com.cardanonft.api.service.AuthService;
import org.springframework.stereotype.Component;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.UUID;

@Component
public class RequestFilter implements Filter {

    private final AuthService authService;

    public RequestFilter(AuthService authService) {
        this.authService = authService;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        CustomHttpServletRequestWrapper requestWrapper = new CustomHttpServletRequestWrapper((HttpServletRequest) request);
        addCustomHeaders(requestWrapper);

        chain.doFilter(requestWrapper, response);
    }

    private void addCustomHeaders(CustomHttpServletRequestWrapper requestWrapper) {
        String token = requestWrapper.getHeader("token");

        if (token != null) {
            String userId = authService.getUserIdByToken(token);
            requestWrapper.putHeader("userId", userId);
        }
        requestWrapper.putHeader("trackId", generateTrackId());
        requestWrapper.putHeader("startAt", String.valueOf(System.currentTimeMillis()));
    }

    private String generateTrackId() {
        return UUID.randomUUID().toString();
    }
}
