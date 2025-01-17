package ru.liga.optimalpacking.packages.shared.middlewares;

public interface IRequestNotFound {

    default String GetNotFoundErrorMessage() {
        return "Ресурс не найден.";
    }
}
