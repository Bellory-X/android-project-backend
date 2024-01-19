package com.person.user.api.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record UserGuideFilter(
        @NotNull @Min(value = 0) Integer pageNumber,
        @NotNull @Min(value = 0)Integer pageSize,
        String search/*,
        Optional<Long> id,
        Optional<String> name,
        Optional<String> address,
        Optional<SortedColumn> sortedField*/
) {
}
