package com.microservice.multiplication.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class Multiplication {
    private int factorA;
    private int factorB;
    private int result;

    public Multiplication(int factorA, int factorB) {
        this.factorA = factorA;
        this.factorB = factorB;
        this.result = factorA * factorB;
    }
}
