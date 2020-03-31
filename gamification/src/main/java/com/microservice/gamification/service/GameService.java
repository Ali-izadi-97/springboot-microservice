package com.microservice.gamification.service;

import com.microservice.gamification.domain.GameStats;

public interface GameService {
    /**
     * Process a new attempt from a given user.(contains event pattern)
     * @param userId the user's unique id
     * @param multiplicationResultId the attempt id, can be used to retrieveextra data if needed
     * @param correct indicates if the attempt was correct
     *
     * @return a {@link GameStats} object containing the new
    score and badge cards obtained
     */
    GameStats newAttemptForUser(Long userId, Long multiplicationResultId, boolean correct);
    GameStats retrieveStatsForUser(Long userId);
}
