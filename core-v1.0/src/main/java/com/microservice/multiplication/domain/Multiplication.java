package com.microservice.multiplication.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public final class Multiplication {
    private final int factorA;
    private final int factorB;

    public Multiplication(int factorA, int factorB) {
        this.factorA = factorA;
        this.factorB = factorB;
    }
}
