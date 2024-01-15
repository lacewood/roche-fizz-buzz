package com.roche.service.helper;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class FizzBuzzExecutorCallable implements Callable<List<String>> {
    int int1;
    int int2;
    String str1;
    String str2;
    int startLimit;
    int endLimit;

    public FizzBuzzExecutorCallable(int int1, int int2, String str1, String str2, int startLimit, int endLimit) {
        this.int1 = int1;
        this.int2 = int2;
        this.str1 = str1;
        this.str2 = str2;
        this.startLimit = startLimit;
        this.endLimit = endLimit;
    }

    @Override
    public List<String> call() throws Exception {
        return computeByLimit();
    }

    private List<String> computeByLimit() {
        List<String> data = new ArrayList<>();
        for (int i = this.startLimit; i <= this.endLimit; i++) {
            boolean op1 = i % this.int1 == 0;
            boolean op2 = i % this.int2 == 0;
            if (op1 && op2) {
                data.add(this.str1 + this.str2);
            } else if (op1) {
                data.add(this.str1);
            } else if (op2) {
                data.add(this.str2);
            } else {
                data.add(String.valueOf(i));
            }
        }
        return data;
    }
}
