package com.microservice.gamification.event;

import lombok.*;

import java.io.Serializable;

@RequiredArgsConstructor
@NoArgsConstructor(force = true)
@Getter @ToString @EqualsAndHashCode
public class MultiplicationSolvedEvent implements Serializable {
    private final Long multiplicationResultId;
    private final Long userId;
    private final boolean correct;
}
