package com.microservice.gamification.controller;

import com.microservice.gamification.domain.GameStats;
import com.microservice.gamification.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stats")
public class UserStatsController {
    private final GameService gameService;

    @Autowired
    public UserStatsController(GameService gameService) {
        this.gameService = gameService;
    }

    public GameStats getStatsForUser(@RequestParam("userId") final Long userId) {
        return gameService.retrieveStatsForUser(userId);
    }
}
