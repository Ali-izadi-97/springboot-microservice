package com.microservice.gamification.client;

import com.microservice.gamification.client.dto.MultiplicationResultDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class MultiplicationResultAttemptClientImpl implements MultiplicationResultAttemptClient {
    private final RestTemplate restTemplate;
    private final String multiplicationHost;

    @Autowired
    public MultiplicationResultAttemptClientImpl(final RestTemplate restTemplate,
                                                 @Value("${multiplicationHost}") final String multiplicationHost) {
        this.restTemplate = restTemplate;
        this.multiplicationHost = multiplicationHost;
    }


    @Override
    public MultiplicationResultDto retriveMultiplicationResultId(final Long multiplicationResultId) {
        return restTemplate.getForObject(multiplicationHost + "/results/" +
                multiplicationResultId, MultiplicationResultDto.class);
    }
}
