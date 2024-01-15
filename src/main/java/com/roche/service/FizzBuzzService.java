package com.roche.service;

import com.roche.model.FizzBuzzRequest;

import java.util.List;


public interface FizzBuzzService {

    public List<String> compute(FizzBuzzRequest fizzBuzzRequest);

}
