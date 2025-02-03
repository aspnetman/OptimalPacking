package ru.liga.optimalpacking.packages.shared.dto;

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
public class Response<DataType> {
    private static final int RESULT_TYPE_POSITION = 6;
    private static final char SUCCESS = '0';

    @NotNull
    @Schema(description = "Расширенная информация по статусу обработки запроса", requiredMode = REQUIRED)
    private @Valid ResponseMetaData result;

    @Nullable
    @Schema(description = "Полезная нагрузка ответа")
    private DataType data;

    public Response(@NotNull ResponseCode responseCode) {
        this.result = new ResponseMetaData(responseCode);
    }

    public Response(@NotNull ResponseCode responseCode, @Nullable DataType data) {
        this.result = new ResponseMetaData(responseCode);
        this.data = data;
    }

    public ResponseEntity<Response<DataType>> asResponseEntity() {
        return ResponseEntity.status(result.getStatus()).body(this);
    }
}