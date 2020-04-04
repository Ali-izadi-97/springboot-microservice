package com.microservice.gamification.service.Impl;

import com.microservice.gamification.domain.LeaderBoardRow;
import com.microservice.gamification.repository.ScoreCardRepository;
import com.microservice.gamification.service.LeaderBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LeaderBoardServiceImpl implements LeaderBoardService {
    private ScoreCardRepository scoreCardRepository;

    @Autowired
    public LeaderBoardServiceImpl(final ScoreCardRepository scoreCardRepository) {
        this.scoreCardRepository =  scoreCardRepository;
    }

    @Override
    public List<LeaderBoardRow> getCurrentLeaderBoard() {
        return scoreCardRepository.findFirst10();
    }
}
