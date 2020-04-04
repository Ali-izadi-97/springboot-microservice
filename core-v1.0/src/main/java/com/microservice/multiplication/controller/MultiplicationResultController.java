package com.microservice.multiplication.controller;

import com.microservice.multiplication.domain.MultiplicationResult;
import com.microservice.multiplication.service.MultiplicationService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/results")
public class MultiplicationResultController {
    private final MultiplicationService multiplicationService;

    @Autowired
    public MultiplicationResultController(MultiplicationService multiplicationService) {
        this.multiplicationService = multiplicationService;
    }

    @PostMapping
    ResponseEntity<WrapResultResponse> postResult(@RequestBody @Valid MultiplicationResult multiplicationResult) {
        System.out.println(multiplicationResult);
        return ResponseEntity.ok(
                new WrapResultResponse(multiplicationService
                        .checkAttempt(multiplicationResult)));
    }


    @GetMapping("/{resultId}")
    public ResponseEntity<Optional<MultiplicationResult>> getResultById(@PathVariable("resultId") Long resultId) {
        return ResponseEntity.ok(multiplicationService.getResultById(resultId));
    }

    @AllArgsConstructor
    @NoArgsConstructor(force = true)
    @Getter
    public static final class WrapResultResponse {
        private final boolean correct;
    }
}
