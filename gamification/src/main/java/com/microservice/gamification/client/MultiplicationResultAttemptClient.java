package com.microservice.gamification.client;

import com.microservice.gamification.client.dto.MultiplicationResultDto;

public interface MultiplicationResultAttemptClient {
    MultiplicationResultDto retriveMultiplicationResultId(final Long multiplicationId);
}
