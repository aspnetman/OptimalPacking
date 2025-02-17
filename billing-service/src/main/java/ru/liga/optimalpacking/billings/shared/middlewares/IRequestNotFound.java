package ru.liga.optimalpacking.billings.shared.middlewares;

public interface IRequestNotFound {

    default String getNotFoundErrorMessage() {
        return "Ресурс не найден.";
    }
}