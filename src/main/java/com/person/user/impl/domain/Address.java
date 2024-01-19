package com.person.user.impl.domain;

import com.person.platform.Validate;

public record Address(String value) {

    public Address {
        Validate.requireNonNullAndNotEmpty(value, "value");
    }
}
