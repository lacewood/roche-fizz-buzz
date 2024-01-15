package com.roche.controller;

import com.roche.model.FizzBuzzRequest;
import com.roche.service.FizzBuzzService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/fizzbuzz/v1")
public class FizzBuzzController {

    Logger logger = LogManager.getLogger(FizzBuzzController.class);
    @Autowired
    private FizzBuzzService fizzBuzzService;

    @ApiOperation(value = "Compute FizzBuzz logic",
            notes = "implement a get endpoint to compute fizz-buzz logic")
    @ApiResponses(value = {@ApiResponse(code = 400, message = "invalid request"),
            @ApiResponse(code = 404, message = "not found")})
    @GetMapping(value = "/compute", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<String>> compute(@RequestParam(name = "int1") int int1, @RequestParam(name = "int2") int int2
            , @RequestParam(name = "str1") String str1, @RequestParam(name = "str2") String str2, @RequestParam(name = "limit") int limit) {
        logger.info("request received for fizz-buzz");
        StopWatch sw = new StopWatch();
        sw.start();
        FizzBuzzRequest fizzBuzzRequest = FizzBuzzRequest.builder().int1(int1).int2(int2).str1(str1).str2(str2).limit(limit).build();
        List<String> list = fizzBuzzService.compute(fizzBuzzRequest);
        sw.stop();
        logger.info("response time: {}" , sw.getTotalTimeMillis());
        return new ResponseEntity<>(
                list, HttpStatus.OK);
    }

}
