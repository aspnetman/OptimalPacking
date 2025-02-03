package ru.liga.optimalpacking.billings.shared.exceptions;

import lombok.Getter;

@Getter
public class InvalidOperationException extends RuntimeException {

    public InvalidOperationException(String message) {
        super(message);
    }

}