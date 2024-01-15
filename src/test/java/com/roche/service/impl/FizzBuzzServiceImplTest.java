package com.roche.service.impl;

import com.roche.model.FizzBuzzRequest;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
public class FizzBuzzServiceImplTest {

    @InjectMocks
    FizzBuzzServiceImpl fizzBuzzService;

    @Test
    public void compute_usingSingleThread() throws Exception {
        FizzBuzzRequest fizzBuzzRequest = FizzBuzzRequest.builder().int1(3).int2(5).str1("fizz").str2("buzz").limit(200).build();
        List<String> result = fizzBuzzService.compute(fizzBuzzRequest);
        Assert.assertNotNull(result);
        Assert.assertEquals(200, result.size());
    }

    @Test
    public void compute_usingMultipleThread() throws Exception {
        FizzBuzzRequest fizzBuzzRequest = FizzBuzzRequest.builder().int1(3).int2(5).str1("fizz").str2("buzz").limit(20000).build();
        List<String> result = fizzBuzzService.compute(fizzBuzzRequest);
        Assert.assertNotNull(result);
        Assert.assertEquals(20000, result.size());
    }

}