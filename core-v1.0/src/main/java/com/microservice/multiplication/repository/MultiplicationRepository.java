package com.microservice.multiplication.repository;

import com.microservice.multiplication.domain.Multiplication;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MultiplicationRepository extends CrudRepository<Multiplication, Long> {
    Optional<Multiplication> findByFactorAAndFactorB(Integer fa, Integer fb);
}
