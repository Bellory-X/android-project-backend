package com.person.user.impl.domain;

import com.person.user.api.request.CreateUserRequest;
import com.person.user.api.request.UpdateUserRequest;
import com.person.platform.Validate;

public record User(
        UserId id,
        Name name,
        Address address,
        Description description
) {

    public User {
        Validate.requireNonNull(id, "id");
        Validate.requireNonNull(name, "name");
        Validate.requireNonNull(address, "address");
        Validate.requireNonNull(description, "description");
    }

    public static User create(UserId id, CreateUserRequest request) {
        return new User(
                id,
                new Name(request.name()),
                new Address(request.address()),
                new Description(request.description())
        );
    }

    public User update(UpdateUserRequest request) {
        return new User(
                this.id,
                new Name(request.name()),
                new Address(request.address()),
                new Description(request.description())
        );
    }
}
