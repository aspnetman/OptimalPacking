package ru.liga.optimalpacking.packages.shared.middlewares;

public interface IRequestNotFound {

    default String getNotFoundErrorMessage() {
        return "Ресурс не найден.";
    }
}
