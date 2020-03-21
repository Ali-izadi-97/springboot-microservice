package com.microservice.multiplication;

import com.microservice.multiplication.domain.Multiplication;
import com.microservice.multiplication.service.Imp.MultiplicationServiceImp;
import com.microservice.multiplication.service.RandomGeneratorService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        multiplicationServiceImp = new MultiplicationServiceImp(randomGeneratorService);
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
}
