package com.roche.config;

import com.roche.service.FizzBuzzMetricsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.util.ContentCachingRequestWrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Component
public class FizzBuzzMetricsInterceptor implements HandlerInterceptor {

    @Autowired
    private FizzBuzzMetricsService metricsService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (request.getMethod().equals("GET") && request.getRequestURI().contains("/compute")) {
            String requestParams = getRequestParams(request);
            Map<String, String> data = new HashMap<>();
            data.put("endPoint", request.getRequestURI());
            data.put("requestParams", requestParams.toString());
            metricsService.incrementMetric(data);
        }
        return true;
    }


    private String getRequestParams(HttpServletRequest request) {
        StringBuilder sb = new StringBuilder();
        ContentCachingRequestWrapper wrappedRequest = new ContentCachingRequestWrapper(request);
        Map<String, String[]> requestParam = wrappedRequest.getParameterMap();
        for (Map.Entry<String, String[]> map : requestParam.entrySet()) {
            sb.append(map.getKey() + " : " + map.getValue()[0] + "; ");
        }
        return sb.toString();
    }
}
