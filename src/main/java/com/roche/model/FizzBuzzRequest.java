package com.roche.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class FizzBuzzRequest {
    int int1 ;
    int int2 ;
    String str1 ;
    String str2;
    int limit;
}
