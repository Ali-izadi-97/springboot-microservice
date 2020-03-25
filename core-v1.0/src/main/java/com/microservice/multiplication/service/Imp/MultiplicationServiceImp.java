package com.microservice.multiplication.service.Imp;

import com.microservice.multiplication.domain.Multiplication;
import com.microservice.multiplication.domain.MultiplicationResult;
import com.microservice.multiplication.domain.User;
import com.microservice.multiplication.repository.MultiplicationRepository;
import com.microservice.multiplication.repository.MultiplicationResultRepository;
import com.microservice.multiplication.repository.UserRepository;
import com.microservice.multiplication.service.MultiplicationService;
import com.microservice.multiplication.service.RandomGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;


@Service
public class MultiplicationServiceImp implements MultiplicationService {

    private RandomGeneratorService randomGeneratorService;
    private MultiplicationResultRepository resultRepository;
    private UserRepository userRepository;
    private MultiplicationRepository multiplicationRepository;

    @Autowired
    public MultiplicationServiceImp(final RandomGeneratorService randomGenerator,
                                    final MultiplicationResultRepository multiplicationResultRepository,
                                    final UserRepository userRepository,
                                    final MultiplicationRepository multiplicationRepository) {
        this.randomGeneratorService = randomGenerator;
        this.resultRepository = multiplicationResultRepository;
        this.userRepository = userRepository;
        this.multiplicationRepository = multiplicationRepository;
    }

    @Override
    public Multiplication createRandomMultiplication() {
        int factorA = randomGeneratorService.generateRandomNumber();
        int factorB = randomGeneratorService.generateRandomNumber();

        Optional<Multiplication> checkNotSameFactor =
                multiplicationRepository.findByFactorAAndFactorB(factorA, factorB);
        while (checkNotSameFactor.isPresent()) {
            factorA = randomGeneratorService.generateRandomNumber();
            factorB = randomGeneratorService.generateRandomNumber();
        }
        return new Multiplication(factorA, factorB);
    }

    @Transactional
    @Override
    public boolean checkAttempt(final MultiplicationResult resultModel) {
        Optional<User> user = userRepository.findByName(resultModel.getUser().getName());

        boolean correct = resultModel.getResult() == resultModel.getMultiplication().getFactorB() *
                resultModel.getMultiplication().getFactorA();

        Assert.isTrue(!resultModel.isCorrect(),"dont send true as result of correct!");

        MultiplicationResult checkedResult = new MultiplicationResult(
                user.orElse(resultModel.getUser()),
                resultModel.getMultiplication(),
                resultModel.getResult(), correct);
        resultRepository.save(checkedResult);
        return correct;
    }

    @Override
    public List<MultiplicationResult> getStatusForUser(String userName) {
        return resultRepository.findTop5ByUserNameOrderByIdDesc(userName);
    }
}
