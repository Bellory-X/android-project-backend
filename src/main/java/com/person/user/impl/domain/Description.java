package com.person.user.impl.domain;

import com.person.platform.Validate;

public record Description(String value) {

    public Description {
        Validate.requireNonNullAndNotEmpty(value, "value");
    }
}
