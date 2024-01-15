package com.roche.config;

import com.roche.service.FizzBuzzMetricsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.util.ContentCachingRequestWrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Component
public class FizzBuzzMetricsInterceptor implements HandlerInterceptor {

    @Autowired
    private FizzBuzzMetricsService metricsService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getMethod().equals("GET") && request.getRequestURI().contains("/compute")) {
           String requestParams = getRequestParams(request);
            metricsService.incrementMetric("requestParams: ", requestParams.toString());
        }
        return true;
    }


    private String getRequestParams(HttpServletRequest request) throws IOException {
       StringBuilder sb=new StringBuilder();
        ContentCachingRequestWrapper wrappedRequest = new ContentCachingRequestWrapper(request);
        Map<String, String[]> requestParam = wrappedRequest.getParameterMap();
        for(Map.Entry<String, String[]> map:requestParam.entrySet()){
            sb.append(map.getKey()+" : "+map.getValue()[0]+"; ");
        }
        return sb.toString();
    }
}
