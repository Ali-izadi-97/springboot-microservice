package com.microservice.gamification.event;

import com.microservice.gamification.service.GameService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class EventHandler {
    private GameService gameService;

    @Autowired
    public EventHandler(final GameService gameService) {
        this.gameService = gameService;
    }

    @RabbitListener(queues = "${multiolication.queue}")
    public void handleMultiplicationSolved(final MultiplicationSolvedEvent event) {
        log.info("Multiplication Solved Event received: {}", event.getMultiplicationResultId());

        try {
            gameService.newAttemptForUser(event.getUserId(),
                    event.getMultiplicationResultId(), event.isCorrect());
        } catch (Exception e) {
            log.error("Error when trying to process MultiplicationSolvedEvent", e);
            throw new AmqpRejectAndDontRequeueException(e);
        }
    }
}
