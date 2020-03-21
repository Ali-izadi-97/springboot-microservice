package com.microservice.multiplication.domain;

import lombok.Data;

@Data
public final class User {
    private final String name;

    protected User() {
        name = null;
    }
}
