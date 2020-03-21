package com.microservice.multiplication.service.Imp;

import com.microservice.multiplication.domain.Multiplication;
import com.microservice.multiplication.service.MultiplicationService;
import com.microservice.multiplication.service.RandomGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class MultiplicationServiceImp implements MultiplicationService {

    private RandomGeneratorService randomGeneratorService;

    @Autowired
    public MultiplicationServiceImp(RandomGeneratorService randomGenerator) {
        this.randomGeneratorService = randomGenerator;
    }
    @Override
    public Multiplication createRandomMultiplication() {
        int factorA = randomGeneratorService.generateRandomNumber();
        int factorB = randomGeneratorService.generateRandomNumber();
        return new Multiplication(factorA, factorB);
    }
}
