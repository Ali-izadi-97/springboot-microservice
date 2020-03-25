package com.microservice.multiplication;

import com.microservice.multiplication.domain.Multiplication;
import com.microservice.multiplication.domain.MultiplicationResult;
import com.microservice.multiplication.domain.User;
import com.microservice.multiplication.repository.MultiplicationRepository;
import com.microservice.multiplication.repository.MultiplicationResultRepository;
import com.microservice.multiplication.repository.UserRepository;
import com.microservice.multiplication.service.Imp.MultiplicationServiceImp;
import com.microservice.multiplication.service.RandomGeneratorService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.boot.test.context.SpringBootTest;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
/**
 * import org.springframework.test.context.junit4.SpringRunner;
 * import org.junit.runner.RunWith;
 * @RunWith(SpringRunner.class)
 *
 * use @ExtendWith instead
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
@Slf4j
class MultiplicationApplicationTests {
    private MultiplicationServiceImp multiplicationServiceImp;

    @Mock
    private RandomGeneratorService randomGeneratorService;

    @Mock
    private MultiplicationResultRepository multiplicationResultRepository;
    @Mock
    private MultiplicationRepository multiplicationRepository;
    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        multiplicationServiceImp = new MultiplicationServiceImp(randomGeneratorService,
                multiplicationResultRepository, userRepository, multiplicationRepository);
    }
    @Test
    public void createRandomMultiplicationTest() {
        given(randomGeneratorService.generateRandomNumber()).willReturn(50, 30);
        Multiplication multiplication = multiplicationServiceImp.createRandomMultiplication();
        log.info(multiplication.getFactorA() + "   " + multiplication.getFactorB());

        assertThat(multiplication.getFactorA()).isEqualTo(50);
        assertThat(multiplication.getFactorB()).isEqualTo(30);
//        assertThat(multiplication.getResult()).isEqualTo(1500);
    }

    @Test
    public void checkCorrectAttemptTest() {
        Multiplication multiplication = new Multiplication(50, 60);
        User user = new User("john_doe");
        MultiplicationResult result = new MultiplicationResult(user, multiplication, 3000, true);
        boolean attemptResult = multiplicationServiceImp.checkAttempt(result);

        assertThat(attemptResult).isTrue();
    }
}
