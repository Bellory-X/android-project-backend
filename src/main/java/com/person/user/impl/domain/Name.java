package com.person.user.impl.domain;

import com.person.platform.Validate;

public record Name(String value) {

    public Name {
        Validate.requireNonNullAndNotEmpty(value, "value");
    }
}
