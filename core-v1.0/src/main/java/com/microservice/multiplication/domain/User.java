package com.microservice.multiplication.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public final class User {
    private final String name;

    protected User() {
        name = null;
    }
}
