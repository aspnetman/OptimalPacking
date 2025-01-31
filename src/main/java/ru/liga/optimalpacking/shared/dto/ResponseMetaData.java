package ru.liga.optimalpacking.shared.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

import java.time.LocalDateTime;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;


/**
 * Результат обработки запроса
 */
@Valid
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ResponseMetaData {
    @Schema(description = "Дата и время", requiredMode = REQUIRED)
    private LocalDateTime timestamp;

    @Min(value = 100)
    @Max(value = 527)
    @Schema(description = "Http статус ответа", requiredMode = REQUIRED)
    private int status;

    @NotBlank
    @Size(max = 10)
    @Schema(description = "Код результата обработки запроса", requiredMode = REQUIRED)
    private String code;

    @NotBlank
    @Schema(description = "Описание кода обработки запроса", requiredMode = REQUIRED)
    private String message;

    public ResponseMetaData(@NonNull ResponseCode responseCode) {
        this.timestamp = LocalDateTime.now();
        this.status = responseCode.getStatus();
        this.code = responseCode.getCode();
        this.message = responseCode.getMessage();
    }
}
