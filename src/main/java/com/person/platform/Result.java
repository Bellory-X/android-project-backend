package com.person.platform;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public sealed interface Result<T> permits Result.Success, Result.Failure {

    static <T> Result<T> pipeline(T value) {
        return success(value);
    }

    static <T> Result<T> success(T value) {
        return new Success<>(value);
    }

    static <T> Result<T> failure(RuntimeException exception) {
        return new Failure<>(exception);
    }

    default <R> Result<R> map(Function<T, R> function) {
        return switch (this) {
            case Success<T>(var value) -> Result.success(function.apply(value));
            case Failure<T>(var exception) -> Result.failure(exception);
        };
    }

    default <R> Result<R> flatMap(Function<T, Result<R>> function) {
        return switch (this) {
            case Success<T>(var value) -> function.apply(value);
            case Failure<T>(var exception) -> Result.failure(exception);
        };
    }

    default Result<T> onSuccess(Consumer<T> consumer) {
        return switch (this) {
            case Success<T>(var value) -> {
                consumer.accept(value);
                yield this;
            }
            case Failure<T> ignored -> this;
        };
    }

    default Result<T> onFailure(Consumer<RuntimeException> consumer) {
        return switch (this) {
            case Success<T> ignored -> this;
            case Failure<T>(var exception) -> {
                consumer.accept(exception);
                yield this;
            }
        };
    }

    default T orElseThrow() {
        return switch (this) {
            case Success<T>(var value) -> value;
            case Failure<T>(var exception) -> throw exception;
        };
    }

    static <R> Result<R> runCatching(Supplier<R> supplier) {
        try {
            return Result.success(supplier.get());
        } catch (RuntimeException exception) {
            return Result.failure(exception);
        }
    }

    record Success<T>(T value) implements Result<T> {

        public Success {
            Objects.requireNonNull(value, "Success value require non null");
        }
    }

    record Failure<T>(RuntimeException exception) implements Result<T> {

        public Failure {
            Objects.requireNonNull(exception, "Failure exception require non null");
        }
    }
}
