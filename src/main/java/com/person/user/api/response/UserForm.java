package com.person.user.api.response;

public record UserForm(
        Long id,
        String name,
        String address,
        String description
) {
}
