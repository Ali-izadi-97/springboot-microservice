package com.microservice.multiplication.domain;

import lombok.Data;

@Data
public final class Multiplication {
    private final int factorA;
    private final int factorB;

    public Multiplication() {
        this(0, 0);
    }
}
