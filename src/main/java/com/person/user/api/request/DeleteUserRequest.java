package com.person.user.api.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record DeleteUserRequest(@NotNull @Positive Long id) {
}
