package com.person.user.impl.domain;

import com.person.platform.Validate;

public record UserId(Long value) {

    public UserId {
        Validate.identifier(value);
    }
}
