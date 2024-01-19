package com.person.user.api.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record UpdateUserRequest(
        @NotNull @Positive Long id,
        @NotBlank String name,
        @NotBlank String address,
        @NotBlank String description
) {
}
