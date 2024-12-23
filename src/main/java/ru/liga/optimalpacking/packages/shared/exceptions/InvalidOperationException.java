package ru.liga.optimalpacking.packages.shared.exceptions;

import lombok.Getter;

@Getter
public class InvalidOperationException extends RuntimeException {
    private final String message;

    public InvalidOperationException(String message) {
        this.message = message;
    }

}
