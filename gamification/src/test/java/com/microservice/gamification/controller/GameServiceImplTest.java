package com.microservice.gamification.controller;

import com.microservice.gamification.repository.BadgeCardRepository;
import com.microservice.gamification.repository.ScoreCardRepository;
import com.microservice.gamification.service.Impl.GameServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class GameServiceImplTest {
    private GameServiceImpl gameService;

    @Mock
    private ScoreCardRepository scoreCardRepository;

    @Mock
    private BadgeCardRepository badgeCardRepository;

    @BeforeEach
    public void setUp() {

        MockitoAnnotations.initMocks(this);
    }
}
