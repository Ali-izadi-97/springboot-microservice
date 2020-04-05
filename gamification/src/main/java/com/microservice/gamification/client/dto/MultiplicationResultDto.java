package com.microservice.gamification.client.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.microservice.gamification.client.serializer.MultiplicationResultAttemptDeserializer;
import lombok.*;

@RequiredArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
@JsonDeserialize(using = MultiplicationResultAttemptDeserializer.class)
public final class MultiplicationResultDto {
    private final String userAlias;
    private final int multiplicationFactorA;
    private final int multiplicationFactorB;
    private final int resultAttempt;
    private final boolean correct;

    MultiplicationResultDto() {
        userAlias = null;
        multiplicationFactorA = -1;
        multiplicationFactorB = -1;
        resultAttempt = -1;
        correct = false;
    }
}
