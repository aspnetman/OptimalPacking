package ru.liga.optimalpacking.infrastructure.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.liga.optimalpacking.packages.shared.exceptions.InvalidOperationException;
import ru.liga.optimalpacking.packages.shared.exceptions.NotFoundException;
import ru.liga.optimalpacking.shared.dto.Response;
import ru.liga.optimalpacking.shared.dto.ResponseCode;

@RestControllerAdvice
public class ResponseControllerAdvice {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Response<Object>> handle(NotFoundException exception) {

        Response<Object> response = new Response<>(ResponseCode.NOT_FOUND);

        response.getResult().setMessage(exception.getMessage());

        return response.asResponseEntity();
    }

    @ExceptionHandler(InvalidOperationException.class)
    public ResponseEntity<Response<Object>> handle(InvalidOperationException exception) {

        Response<Object> response = new Response<>(ResponseCode.UNPROCESSABLE_ENTITY);

        response.getResult().setMessage(exception.getMessage());

        return response.asResponseEntity();
    }
}