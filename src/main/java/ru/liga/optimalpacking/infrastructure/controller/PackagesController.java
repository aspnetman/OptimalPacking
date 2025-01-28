package ru.liga.optimalpacking.infrastructure.controller;

import an.awesome.pipelinr.Pipeline;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.liga.optimalpacking.packages.deleteparcel.DeleteParcelCommand;
import ru.liga.optimalpacking.packages.deleteparcel.dto.DeleteParcelResponse;
import ru.liga.optimalpacking.packages.editparcel.EditParcelCommand;
import ru.liga.optimalpacking.packages.editparcel.dto.EditParcelResponse;
import ru.liga.optimalpacking.packages.exportpackages.ExportPackagesCommand;
import ru.liga.optimalpacking.packages.exportpackages.dto.ExportPackagesResponse;
import ru.liga.optimalpacking.packages.getparcel.GetParcelQuery;
import ru.liga.optimalpacking.packages.getparcel.dto.GetParcelResponse;
import ru.liga.optimalpacking.packages.getparcels.GetParcelsQuery;
import ru.liga.optimalpacking.packages.getparcels.dto.GetParcelsResponse;
import ru.liga.optimalpacking.packages.importpackages.ImportPackagesCommand;
import ru.liga.optimalpacking.packages.importpackages.dto.ImportPackagesResponse;
import ru.liga.optimalpacking.packages.importpackages.dto.PackingAlgorithm;
import ru.liga.optimalpacking.shared.dto.Response;
import ru.liga.optimalpacking.shared.dto.ResponseCode;


@RestController
@RequestMapping("/api/v1/packages")
@RequiredArgsConstructor
public class PackagesController {
    private final Pipeline pipeline;

    /**
     * Удаление посылки из репозитория
     *
     * @param name Название посылки
     */
    @DeleteMapping("/{name}")
    @Operation(summary = "Удаление посылки из репозитория")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Посылка успешно удалена",
                    headers = {@Header(name = "X-Operation-ID", description = "ID операции")},
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "Посылка не найдена",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Response.class)))
    })
    public ResponseEntity<Response<DeleteParcelResponse>> deleteParcel(@Parameter(description = "Название посылки") @PathVariable String name) {
        return new ResponseEntity<>(
                new Response<>(ResponseCode.OK, pipeline.send(new DeleteParcelCommand(name))),
                HttpStatus.OK);
    }

    /**
     * Редактирование посылки
     *
     * @param name     Текущее название
     * @param width    Ширина
     * @param height   Высота
     * @param newName  Новое название
     */
    @PutMapping("/{name}")
    @Operation(summary = "Редактирование посылки")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Посылка успешно отредактирована",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "400", description = "Некорректные данные",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "Посылка не найдена",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Response.class)))
    })
    public ResponseEntity<Response<EditParcelResponse>> editParcel(
            @Parameter(description = "Текущее название посылки") @PathVariable String name,
            @Parameter(description = "Ширина посылки") @RequestParam(required = false) Integer width,
            @Parameter(description = "Высота посылки") @RequestParam(required = false) Integer height,
            @Parameter(description = "Новое название посылки") @RequestParam(required = false) String newName) {
        return new ResponseEntity<>(
                new Response<>(ResponseCode.OK, pipeline.send(new EditParcelCommand(
                name,
                        new ru.liga.optimalpacking.packages.editparcel.dto.Parcel(width, height, newName)))), HttpStatus.OK);
    }

    /**
     * Получение посылки по названию
     *
     * @param name Название посылки
     */
    @GetMapping("/{name}")
    @Operation(summary = "Получение посылки по названию")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Посылка успешно получена",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "Посылка не найдена",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Response.class)))
    })
    public ResponseEntity<Response<GetParcelResponse>> getParcel(@Parameter(description = "Название посылки") @PathVariable String name) {
        return new ResponseEntity<>(
                new Response<>(ResponseCode.OK, pipeline.send(new GetParcelQuery(name))),
                HttpStatus.OK);
    }

    /**
     * Получение списка посылок
     */
    @GetMapping
    @Operation(summary = "Получение списка посылок")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Список посылок успешно получен",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Response.class)))
    })
    public ResponseEntity<Response<GetParcelsResponse>> getParcels() {
        return new ResponseEntity<>(
                new Response<>(ResponseCode.OK, pipeline.send(new GetParcelsQuery())),
                HttpStatus.OK);
    }

    /**
     * Погрузка посылок
     *
     * @param userId           Идентификатор пользователя
     * @param file             Файл с данными по посылкам для погрузки
     * @param maxTrucks        Максимальное число грузовиков
     * @param packingAlgorithm   Алгоритм упаковки
     */
    @PostMapping("/import")
    @Operation(summary = "Погрузка посылок")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Посылки успешно загружены",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "400", description = "Некорректные данные",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "500", description = "Ошибка загрузки файла",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Response.class)))
    })
    public ResponseEntity<Response<ImportPackagesResponse>> importPackages(
            @Parameter(description = "Идентификатор пользователя") @RequestParam String userId,
            @Parameter(description = "Файл с данными по посылкам для погрузки") @RequestParam String file,
            @Parameter(description = "Максимальное число грузовиков") @RequestParam(defaultValue = "10") int maxTrucks,
            @Parameter(description = "Алгоритм упаковки") @RequestParam(required = false) PackingAlgorithm packingAlgorithm) {
        return new ResponseEntity<>(
                new Response<>(ResponseCode.OK, pipeline.send(new ImportPackagesCommand(userId, file, maxTrucks, packingAlgorithm))),
                HttpStatus.OK);
    }

    /**
     * Выгрузка посылок
     *
     * @param userId     Идентификатор пользователя
     * @param trucksFile Файл с грузовиками и посылками
     */
    @PostMapping("/export")
    @Operation(summary = "Выгрузка посылок")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Посылки успешно выгружены",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "400", description = "Некорректные данные",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "500", description = "Ошибка записи в файл",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Response.class)))
    })
    public ResponseEntity<Response<ExportPackagesResponse>> exportPackages(
            @Parameter(description = "Идентификатор пользователя") @RequestParam String userId,
            @Parameter(description = "Файл с грузовиками и посылками") @RequestParam String trucksFile) {
        return new ResponseEntity<>(
                new Response<>(ResponseCode.OK, pipeline.send(new ExportPackagesCommand(userId, trucksFile))),
                HttpStatus.OK);
    }
}