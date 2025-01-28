package ru.liga.optimalpacking.shared.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

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

    public Response(@NotNull ResponseMetaData result) {
        this.result = result;
    }

    public Response(@NotNull ResponseCode responseCode) {
        this.result = new ResponseMetaData(responseCode);
    }

    public Response(@NotNull ResponseCode responseCode, @Nullable DataType data) {
        this.result = new ResponseMetaData(responseCode);
        this.data = data;
    }

    public static <T> ResponseEntity<Response<T>> buildBadRequest() {
        return new Response<T>(ResponseCode.BAD_REQUEST).asResponseEntity();
    }

    public static <T> ResponseEntity<Response<T>> buildInternalServerError() {
        return new Response<T>(ResponseCode.INTERNAL_SERVER_ERROR).asResponseEntity();
    }

    public static <T> ResponseEntity<Response<T>> buildOk() {
        return new Response<T>(ResponseCode.OK).asResponseEntity();
    }

    public ResponseEntity<Response<DataType>> asResponseEntity() {
        return ResponseEntity.status(result.getStatus()).body(this);
    }

    @JsonIgnore
    public boolean isError() {
        return !isSuccess();
    }

    @JsonIgnore
    public boolean isSuccess() {
        return result != null
                && isNotBlank(result.getCode())
                && SUCCESS == result.getCode().charAt(RESULT_TYPE_POSITION);
    }
}