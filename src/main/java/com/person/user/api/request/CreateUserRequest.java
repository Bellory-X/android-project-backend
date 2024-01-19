package com.person.user.api.request;

import jakarta.validation.constraints.NotBlank;

public record CreateUserRequest(
        @NotBlank String name,
        @NotBlank String address,
        @NotBlank String description
) {
}
