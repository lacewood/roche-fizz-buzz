package com.roche.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
public class FizzBuzzMetricsServiceTest {

    @InjectMocks
    FizzBuzzMetricsService fizzBuzzMetricsService;
    @Test
    public void getAllMetrics_test() throws Exception {
        Map<String,String> map=new HashMap<>();
        map.put("endpoint","/abcd");
        fizzBuzzMetricsService.incrementMetric(map);
        Map<String, Long> result = fizzBuzzMetricsService.getAllMetrics();
        Assert.assertNotNull(result);
    }

}