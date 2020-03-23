package com.microservice.multiplication.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public final class Multiplication {
    private final int factorA;
    private final int factorB;

    public Multiplication() {
        this(0, 0);
    }
}

