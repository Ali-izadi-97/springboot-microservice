package com.microservice.multiplication.service;

import com.microservice.multiplication.domain.Multiplication;
import com.microservice.multiplication.domain.MultiplicationResult;

import java.util.List;

public interface MultiplicationService {
    Multiplication createRandomMultiplication();
    boolean checkAttempt(final MultiplicationResult resultAttempt);
    List<MultiplicationResult> getStatusForUser(String userName);
}
