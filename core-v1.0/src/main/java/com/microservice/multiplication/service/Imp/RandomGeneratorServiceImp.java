package com.microservice.multiplication.service.Imp;

import com.microservice.multiplication.service.RandomGeneratorService;

import java.util.Random;


public class RandomGeneratorServiceImp implements RandomGeneratorService {
    final static int MINIMUM_FACTOR = 11;
    final static int MAXIMUM_FACTOR = 99;

    @Override
    public int generateRandomNumber() {
        return new Random().nextInt((MAXIMUM_FACTOR - MINIMUM_FACTOR) + 1) + MINIMUM_FACTOR;
    }
}
