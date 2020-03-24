package com.microservice.multiplication.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class MultiplicationResult {
    private final User user;
    private final Multiplication multiplication;
    private final int result;
    private final boolean correct;

    public MultiplicationResult() {
        user = null;
        multiplication = null;
        result = -1;
        correct = false;
    }
}


