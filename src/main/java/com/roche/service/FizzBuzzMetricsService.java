package com.roche.service;

import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class FizzBuzzMetricsService {

    private final Map<String, AtomicLong> apiMetrics = new ConcurrentHashMap<>();

    public void incrementMetric(Map<String,String> map) {
        apiMetrics.computeIfAbsent(map.toString(), k -> new AtomicLong()).incrementAndGet();
    }

    public Map<String, String> getMostFrequentRequestMetrics() {
        Map<String,String> mostFrequentRequest = new HashMap<>();
        Map<String, Long> result = new HashMap<>();
        apiMetrics.forEach((key, value) -> result.put(key, value.get()));
        Optional<Map.Entry<String, Long>> maxEntry = result.entrySet()
                .stream()
                .max(Comparator.comparing(Map.Entry::getValue));
        if(maxEntry.isPresent()){
            mostFrequentRequest.put("api",maxEntry.get().getKey());
            mostFrequentRequest.put("hitCount", String.valueOf(maxEntry.get().getValue()));
        }
        return mostFrequentRequest;
    }
}
