package com.roche.service.impl;

import com.roche.model.FizzBuzzRequest;
import com.roche.service.helper.FizzBuzzExecutorCallable;
import com.roche.service.FizzBuzzService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Service
public class FizzBuzzServiceImpl implements FizzBuzzService {

    public static int RECORD_SIZE = 10000;

    Logger logger = LogManager.getLogger(FizzBuzzServiceImpl.class);

    public List<String> compute(FizzBuzzRequest fizzBuzzRequest) {
        int numberOfRanges = fizzBuzzRequest.getLimit() > RECORD_SIZE ? 4 : 1; // if request limit size is big , create 4 threads
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfRanges);
        List<String> data = new ArrayList<>();
        try {
            int rangeStart = 1;
            int rangeEnd;
            // Calculate the interval between each range
            int interval = fizzBuzzRequest.getLimit() / numberOfRanges;
            List<Callable<List<String>>> tasks = new ArrayList<>();
            for (int i = 1; i <= numberOfRanges; i++) {
                // Calculate the end of the current range
                rangeEnd = i * interval;
                // Output the current range
                logger.info("Task: {} start: {} end: {}", i, rangeStart, rangeEnd);
                Callable<List<String>> callable = new FizzBuzzExecutorCallable(fizzBuzzRequest.getInt1(), fizzBuzzRequest.getInt2(), fizzBuzzRequest.getStr1(), fizzBuzzRequest.getStr2(), rangeStart, rangeEnd);
                tasks.add(callable);
                rangeStart = rangeEnd + 1;
            }
            List<Future<List<String>>> finalData = executorService.invokeAll(tasks);
            for (Future<List<String>> fut : finalData) {
                data.addAll(fut.get());
            }
            logger.info("total records computed: {}", data.size());
        } catch (Exception ex) {
            executorService.shutdown();
            logger.error("exception occurred while computing fizz-buzz logic :{} ", ex.getMessage());
        } finally {
            executorService.shutdown();
        }
        return data;
    }


}
