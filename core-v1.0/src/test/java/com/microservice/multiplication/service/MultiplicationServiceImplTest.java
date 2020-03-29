package com.microservice.multiplication.service;

import com.microservice.multiplication.domain.Multiplication;
import com.microservice.multiplication.domain.MultiplicationResult;
import com.microservice.multiplication.domain.User;
import com.microservice.multiplication.service.Imp.MultiplicationServiceImp;
import com.microservice.multiplication.service.MultiplicationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;


@ExtendWith(SpringExtension.class)
@SpringBootTest
public class MultiplicationServiceImplTest {

    @Autowired
    private MultiplicationServiceImp multiplicationServiceImp;

    @Test
    public void checkCorrectResultTest() {
        Multiplication multiplication = new Multiplication(50, 60);
        User user = new User("john_doe");
        MultiplicationResult attempt = new
                MultiplicationResult(
                user, multiplication, 3000, false);
        boolean attemptResult = multiplicationServiceImp.checkAttempt(attempt);
        assertThat(attemptResult).isTrue();
    }

    @Test
    public void checkWrongResultTest() {
        Multiplication multiplication = new Multiplication(50, 60);
        User user = new User("john_doe");
        MultiplicationResult attempt = new
                MultiplicationResult(user, multiplication, 3010, false);
//        given(userRepository.findByAlias("john_doe")).willReturn(Optional.empty());
        boolean attemptResult = multiplicationServiceImp.checkAttempt(attempt);
        assertThat(attemptResult).isFalse();
    }

    @Test
    public void retrieveStatusTest() {

    }
}
