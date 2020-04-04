package com.microservice.gamification.service.Impl;

import com.microservice.gamification.domain.Badge;
import com.microservice.gamification.domain.BadgeCard;
import com.microservice.gamification.domain.GameStats;
import com.microservice.gamification.domain.ScoreCard;
import com.microservice.gamification.repository.BadgeCardRepository;
import com.microservice.gamification.repository.ScoreCardRepository;
import com.microservice.gamification.service.GameService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class GameServiceImpl implements GameService {
    private ScoreCardRepository scoreCardRepository;
    private BadgeCardRepository badgeCardRepository;

    @Autowired
    public GameServiceImpl(ScoreCardRepository scoreCardRepository, BadgeCardRepository badgeCardRepository) {
        this.scoreCardRepository = scoreCardRepository;
        this.badgeCardRepository = badgeCardRepository;
    }

    @Override
    public GameStats newAttemptForUser(Long userId, Long multiplicationResultId, boolean correct) {
        if (correct) {
            ScoreCard scoreCard = new ScoreCard(userId, multiplicationResultId);
            log.info("User with id {} scored {} points forattempt id {}", userId, scoreCard.getScore(), multiplicationResultId);

            List<BadgeCard> badgeCards = processForBadges(userId, multiplicationResultId);
            return new GameStats(userId, scoreCard.getScore(), badgeCards.stream().map(BadgeCard::getBadge).collect(Collectors.toList()));
        }
        return GameStats.emptyStats(userId);
    }


    private List<BadgeCard> processForBadges(final Long userId, final Long attempId) {
        List<BadgeCard> badgeCards = new ArrayList<>();
        int totalScore = scoreCardRepository.getTotalScoreForUser(userId);
        log.info("New score for user {} is {}", userId, totalScore);

        List<ScoreCard> scoreCardList = scoreCardRepository.findByUserIdOrderByScoreTimestampDesc(userId);
        List<BadgeCard> badgeCardList = badgeCardRepository.findByUserIdOrderByBadgeTimestampDesc(userId);

        checkAndGiveBadgeBasedOnScore(badgeCardList, Badge.BRONZE_MULTIPLICATOR, totalScore, 100, userId)
        .ifPresent(badgeCards::add);
        checkAndGiveBadgeBasedOnScore(badgeCardList, Badge.SILVER_MULTIPLICATOR, totalScore, 500, userId)
        .ifPresent(badgeCards::add);
        checkAndGiveBadgeBasedOnScore(badgeCardList, Badge.GOLD_MULTIPLICATOR, totalScore, 999, userId)
        .ifPresent(badgeCards::add);

        if (scoreCardList.size() == 1 && !containsBadge(badgeCardList, Badge.FIRST_WON)) {
            BadgeCard firstWonBadge = giveBadgeToUser(Badge.FIRST_WON, userId);badgeCards.add(firstWonBadge);
        }
        return badgeCards;
    }

    private Optional<BadgeCard> checkAndGiveBadgeBasedOnScore(final List<BadgeCard> badgeCards,
                                                              final Badge badge, final int score,
                                                              final int scorethreshold, final Long userId) {
        if (score >= scorethreshold && !containsBadge(badgeCards, badge)) {
            return Optional.of(giveBadgeToUser(badge, userId));
        }
        return Optional.empty();
    }

    private boolean containsBadge(final List<BadgeCard> badgeCards, final Badge badge) {
        return badgeCards.stream().anyMatch(b -> b.getBadge().equals(badge));
    }

    private BadgeCard giveBadgeToUser(final Badge badge, final Long userId) {
        BadgeCard badgeCard = new BadgeCard(userId, badge);
        badgeCardRepository.save(badgeCard);
        log.info("User with id {} won a new badge: {}", userId, badge);
        return badgeCard;
    }

    @Override
    public GameStats retrieveStatsForUser(Long userId) {
        int score = scoreCardRepository.getTotalScoreForUser(userId);
        List<BadgeCard> badgeCards = badgeCardRepository.findByUserIdOrderByBadgeTimestampDesc(userId);
        return new GameStats(userId, score, badgeCards.stream().map(BadgeCard::getBadge).collect(Collectors.toList()));
    }
}
