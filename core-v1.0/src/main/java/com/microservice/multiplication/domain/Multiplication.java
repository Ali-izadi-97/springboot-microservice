package com.microservice.multiplication.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Multiplication {
    private int factorA;
    private int factorB;
}
