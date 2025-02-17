package ru.liga.optimalpacking.billings.shared.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

/**
 * Общая часть ответа
 */
@Data
@Valid
@NoArgsConstructor
public class Response<T> {
    private static final int RESULT_TYPE_POSITION = 6;
    private static final char SUCCESS = '0';

    @NotNull
    @Schema(description = "Расширенная информация по статусу обработки запроса", requiredMode = REQUIRED)
    private @Valid ResponseMetaData result;

    @Nullable
    @Schema(description = "Полезная нагрузка ответа")
    private T data;

    public Response(@NotNull ResponseCode responseCode) {
        this.result = new ResponseMetaData(responseCode);
    }

    public Response(@NotNull ResponseCode responseCode, @Nullable T data) {
        this.result = new ResponseMetaData(responseCode);
        this.data = data;
    }

    public ResponseEntity<Response<T>> asResponseEntity() {
        return ResponseEntity.status(result.getStatus()).body(this);
    }
}