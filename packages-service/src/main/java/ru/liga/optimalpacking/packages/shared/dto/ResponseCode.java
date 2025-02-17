package ru.liga.optimalpacking.packages.shared.dto;

import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Getter
@ToString
public enum ResponseCode {
    OK(HttpStatus.OK, "00000", HttpStatus.OK.getReasonPhrase()),
    NOT_FOUND(HttpStatus.NOT_FOUND, "00001", "Not found"),
    NON_AUTHORITATIVE_INFORMATION(
            HttpStatus.NON_AUTHORITATIVE_INFORMATION,
            "00002",
            HttpStatus.NON_AUTHORITATIVE_INFORMATION.getReasonPhrase()),
    PARTIAL_CONTENT(HttpStatus.PARTIAL_CONTENT, "00003", HttpStatus.PARTIAL_CONTENT.getReasonPhrase()),
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "01003", HttpStatus.BAD_REQUEST.getReasonPhrase()),
    FORBIDDEN(HttpStatus.FORBIDDEN, "01005", HttpStatus.FORBIDDEN.getReasonPhrase()),
    INTERNAL_SERVER_ERROR(
            HttpStatus.INTERNAL_SERVER_ERROR, "02004", HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()),
    UNPROCESSABLE_ENTITY(HttpStatus.UNPROCESSABLE_ENTITY, "00422", "Unprocessable Entity");

    private static final String SERVICE_PREFIX = "WBEX"; // у каждого сервиса в РБСП 4-х символьных префикс

    private final int status;
    private final String code;
    private final String message;

    ResponseCode(HttpStatus httpStatus, String code, String message) {
        this.status = httpStatus.value();
        this.code = String.join(".", SERVICE_PREFIX, code);
        this.message = message;
    }
}
