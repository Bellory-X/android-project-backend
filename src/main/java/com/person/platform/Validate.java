package com.person.platform;

import com.google.common.collect.ImmutableList;

import java.math.BigDecimal;
import java.util.*;

public class Validate {

    public static Long identifier(Long id) {
        return requireNonNullAndMoreThanZero(id, "Id");
    }

    public static Long documentNumber(Long documentNumber) {
        return requireNonNullAndMoreThanZero(documentNumber, "document number");
    }

    public static Long requireNonNullAndMoreThanZero(Long value, String nameOfBeingChecked) {
        requireNonNull(value, nameOfBeingChecked);
        requireMoreThanZero(value, "%s must be more than 0, but {%d}".formatted(nameOfBeingChecked, value));
        return value;
    }

    public static Long requireNonNullAndMoreThanOrEqualToZero(Long value, String nameOfBeingChecked) {
        requireNonNull(value, nameOfBeingChecked);
        requireMoreThenOrEqualToZero(value, "%s must be more than or equal to 0".formatted(nameOfBeingChecked));
        return value;
    }

    public static <T> T requireNonNull(T value, String nameOfBeingChecked) {
        return Objects.requireNonNull(value, nameOfBeingChecked + " must be not null");
    }

    public static String requireNonNullAndNotEmpty(String value, String nameOfBeingChecked) {
        requireNonNull(value, nameOfBeingChecked);
        if (value.isEmpty()) {
            throw new IllegalArgumentException(nameOfBeingChecked);
        }
        return value;
    }

    public static <T> List<T> requireNonNullAndNonEmptyList(List<T> value, String nameOfBeingChecked) {
        requireNonNull(value, nameOfBeingChecked);
        requireNonEmptyCollection(value, "Elements in " + nameOfBeingChecked + " must be more than 1");
        return value;
    }

    public static <T> ImmutableList<T> requireNonNullAndNonEmptyList(ImmutableList<T> value, String nameOfBeingChecked) {
        requireNonNull(value, nameOfBeingChecked);
        requireNonEmptyCollection(value, "Elements in " + nameOfBeingChecked + " must be more than 1");
        return value;
    }

    public static <T> SortedSet<T> requireNonNullAndNonEmptySortedSet(SortedSet<T> value, String nameOfBeingChecked) {
        requireNonNull(value, nameOfBeingChecked);
        requireNonEmptyCollection(value, "Elements in " + nameOfBeingChecked + " must be more than 1");
        return value;
    }

    public static <T> Set<T> requireNonNullAndNonEmptySet(Set<T> value, String nameOfBeingChecked) {
        requireNonNull(value, nameOfBeingChecked);
        requireNonEmptyCollection(value, "Elements in " + nameOfBeingChecked + " must be more than 1");
        return value;
    }

    private static <T> void requireNonEmptyCollection(Collection<T> checkedCollection, String message) {
        if (checkedCollection.isEmpty()) {
            throw new IllegalArgumentException(message);
        }
    }

    private static void requireMoreThanZero(Long value, String message) {
        if (value < 1) {
            throw new IllegalArgumentException(message);
        }
    }

    private static void requireMoreThenOrEqualToZero(Long value, String message) {
        if (value < 0) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void requireNonNullAndMoreThanZero(BigDecimal value, String nameOfBeingChecked) {
        if (requireNonNull(value, nameOfBeingChecked).compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Value of %s must more then zero".formatted(value));
        }
    }
}
