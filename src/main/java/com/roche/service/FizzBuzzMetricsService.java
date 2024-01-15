package com.roche.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class FizzBuzzMetricsService {

    private final Map<String, AtomicLong> bodyMetrics = new ConcurrentHashMap<>();

    public void incrementMetric(String endpoint, String requestParams) {
        String metricKey = endpoint + "." + requestParams;
        bodyMetrics.computeIfAbsent(metricKey, k -> new AtomicLong()).incrementAndGet();
    }

    public Map<String, Long> getAllMetrics() {
        Map<String, Long> result = new HashMap<>();
        bodyMetrics.forEach((key, value) -> result.put(key, value.get()));
        return result;
    }
}
