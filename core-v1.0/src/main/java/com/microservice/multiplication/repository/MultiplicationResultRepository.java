package com.microservice.multiplication.repository;

import com.microservice.multiplication.domain.MultiplicationResult;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MultiplicationResultRepository extends CrudRepository<MultiplicationResult, Long> {
    List<MultiplicationResult> findTop5ByUserNameOrderByIdDesc(String userName);
}
