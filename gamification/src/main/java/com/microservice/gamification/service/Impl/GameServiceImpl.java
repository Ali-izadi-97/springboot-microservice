package com.microservice.gamification.service.Impl;

import com.microservice.gamification.domain.BadgeCard;
import com.microservice.gamification.domain.GameStats;
import com.microservice.gamification.domain.ScoreCard;
import com.microservice.gamification.repository.BadgeCardRepository;
import com.microservice.gamification.repository.ScoreCardRepository;
import com.microservice.gamification.service.GameService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class GameServiceImpl implements GameService {
    private ScoreCardRepository scoreCardRepository;
    private BadgeCardRepository badgeCardRepository;

    public GameServiceImpl(ScoreCardRepository scoreCardRepository, BadgeCardRepository badgeCardRepository) {
        this.scoreCardRepository = scoreCardRepository;
        this.badgeCardRepository = badgeCardRepository;
    }

    @Override
    public GameStats newAttemptForUser(Long userId, Long multiplicationResultId, boolean correct) {
        if (correct) {
            ScoreCard scoreCard = new ScoreCard(userId, multiplicationResultId);
            log.info("User with id {} scored {} points forattempt id {}", userId, scoreCard.getScore(), multiplicationResultId);

            List<BadgeCard> badgeCards = processForBadges()
        }
    }

    private List<BadgeCard> processForBadges(final Long userId, final Long attempId) {
        List<BadgeCard> badgeCards = new ArrayList<>();
        int totalScore = scoreCardRepository.getTotalScoreForUser(userId);
        log.info("New score for user {} is {}", userId, totalScore);

        List<ScoreCard> scoreCardList = scoreCardRepository.findByUserIdOrderByScoreTimestampDesc(userId);
        List<BadgeCard> badgeCardList = badgeCardRepository.findByUserIdOrderByBadgeTimestampDesc(userId);
    }

    @Override
    public GameStats retrieveStatsForUser(Long userId) {
        return null;
    }
}
