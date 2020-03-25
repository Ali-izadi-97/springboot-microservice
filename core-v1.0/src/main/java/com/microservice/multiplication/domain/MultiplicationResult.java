package com.microservice.multiplication.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Data @Entity
@RequiredArgsConstructor
public class MultiplicationResult {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "USER_ID")
    private final User user;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "MULTIPLICATION_ID")
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


