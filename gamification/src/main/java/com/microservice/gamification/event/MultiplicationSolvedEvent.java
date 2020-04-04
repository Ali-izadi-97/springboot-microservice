package com.microservice.gamification.event;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@RequiredArgsConstructor
@Getter @ToString @EqualsAndHashCode
public class MultiplicationSolvedEvent implements Serializable {
    private final Long multiplicationResultId;
    private final Long userId;
    private final boolean correct;
}
