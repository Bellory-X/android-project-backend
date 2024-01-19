package com.person.user.api.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record GetUserRequest(@NotNull @Positive Long id) {
}
