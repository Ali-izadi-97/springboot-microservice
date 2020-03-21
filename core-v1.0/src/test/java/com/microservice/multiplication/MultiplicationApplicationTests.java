package com.microservice.multiplication;

import com.microservice.multiplication.domain.Multiplication;
import com.microservice.multiplication.service.MultiplicationService;
import com.microservice.multiplication.service.RandomGeneratorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
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
class MultiplicationApplicationTests {
    @MockBean
    private RandomGeneratorService randomGeneratorService;

    @Autowired
    private MultiplicationService multiplicationService;

    @Test
    void contextLoads() {
    }

    @Test
    public void createRandomMultiplicationTest() {
        given(randomGeneratorService.generateRandomNumber()).willReturn(50, 30);
//        Multiplication multiplication = multiplicationService.createRandomMultiplication();
//
//        assertThat(multiplication.getFactorA()).isEqualTo(50);
//        assertThat(multiplication.getFactorB()).isEqualTo(30);
//        assertThat(multiplication.getResult()).isEqualTo(1500);
    }
}
