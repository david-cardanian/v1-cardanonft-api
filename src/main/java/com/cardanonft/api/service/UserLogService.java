package com.cardanonft.api.service;

import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class UserLogService {

    private final AuthService authService;

    public UserLogService(AuthService authService) {
        this.authService = authService;
    }

    public void logRequest(HttpServletRequest request, Object body) {
        String userId = request.getHeader("userId");
        String trackId = request.getHeader("trackId");

        StringBuilder stringBuilder = new StringBuilder();
        Map<String, String> parameters = buildParametersMap(request);

        stringBuilder.append("REQUEST ");
        stringBuilder.append("{ trackId: ").append(trackId).append(", ");
        stringBuilder.append("userId: ").append(userId).append(", ");
        stringBuilder.append("method=[").append(request.getMethod()).append("] ");
        stringBuilder.append("path=[").append(request.getRequestURI()).append("] ");
        stringBuilder.append("headers=[").append(buildHeadersMap(request)).append("] ");

        if (!parameters.isEmpty()) {
            stringBuilder.append("parameters=[").append(parameters).append("] ");
        }

        if (body != null) {
            stringBuilder.append("body=[" + body + "] ");
        }

        stringBuilder.append("} ");

        log.info(stringBuilder.toString());
    }

    public void logResponse(HttpServletRequest request, HttpServletResponse response, Object body) {
        String userId = request.getHeader("userId");
        String trackId = request.getHeader("trackId");

        long latency = calculateLatency(Long.parseLong(request.getHeader("startAt")));

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("RESPONSE ");
        stringBuilder.append("{ trackId: ").append(trackId).append(", ");
        stringBuilder.append("userId: ").append(userId).append(", ");
        stringBuilder.append("latency: ").append(latency).append("ms, ");
        stringBuilder.append("responseHeaders: [").append(buildHeadersMap(response)).append("] ");
        stringBuilder.append("responseBody: [").append(body).append("] }");

        log.info(stringBuilder.toString());
    }

    private String getUserId(String token) {
        return authService.getUserIdByToken(token);
    }

    private long calculateLatency(long startAt) {
        return System.currentTimeMillis() - startAt;
    }

    private Map<String, String> buildParametersMap(HttpServletRequest httpServletRequest) {
        Map<String, String> resultMap = new HashMap<>();
        Enumeration<String> parameterNames = httpServletRequest.getParameterNames();

        while (parameterNames.hasMoreElements()) {
            String key = parameterNames.nextElement();
            String value = httpServletRequest.getParameter(key);
            resultMap.put(key, value);
        }

        return resultMap;
    }

    private Map<String, String> buildHeadersMap(HttpServletRequest request) {
        Map<String, String> map = new HashMap<>();

        Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = request.getHeader(key);
            map.put(key, value);
        }

        return map;
    }

    private Map<String, String> buildHeadersMap(HttpServletResponse response) {
        Map<String, String> map = new HashMap<>();

        Collection<String> headerNames = response.getHeaderNames();
        for (String header : headerNames) {
            map.put(header, response.getHeader(header));
        }

        return map;
    }
}