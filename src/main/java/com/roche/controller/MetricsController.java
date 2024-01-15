package com.roche.controller;

import com.roche.service.FizzBuzzMetricsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/fizzbuzz/v1")
public class MetricsController {

    @Autowired
    private FizzBuzzMetricsService metricsService;

    @GetMapping("/metrics")
    public Map<String, Long> getCustomEndpointMetrics() {
        return metricsService.getAllMetrics();
    }
}

