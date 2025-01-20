package ru.liga.optimalpacking.infrastructure.controller;

import an.awesome.pipelinr.Pipeline;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.liga.optimalpacking.packages.deleteparcel.DeleteParcelCommand;
import ru.liga.optimalpacking.packages.editparcel.EditParcelCommand;
import ru.liga.optimalpacking.packages.getparcel.GetParcelQuery;
import ru.liga.optimalpacking.packages.getparcels.GetParcelsQuery;
import ru.liga.optimalpacking.packages.importpackages.ImportPackagesCommand;
import ru.liga.optimalpacking.packages.importpackages.dto.PackingAlgorithm;

@Slf4j
@ShellComponent
@RequiredArgsConstructor
public class OptimalPackingController {
    private final Pipeline pipeline;

    /**
     * Удаление посылки из репозитория
     *
     * @param name Название посылки
     */
    @ShellMethod(value = "Удаление посылки из репозитория", key = "delete")
    public void deleteParcel(@ShellOption({"--name"}) String name) {
        pipeline.send(new DeleteParcelCommand(name));

        log.info("Посылка {} удалена", name);
    }

    /**
     * Редактирование посылки
     * @param name текущее название
     * @param width ширина
     * @param height высота
     * @param newName новое название
     */
    @ShellMethod(value = "Редактирование посылки", key = "edit")
    public void editParcel(@ShellOption({"--name"}) String name,
                           @ShellOption({"--width"}) int width,
                           @ShellOption({"--height"}) int height,
                           @ShellOption({"--newname"}) String newName) {
        pipeline.send(new EditParcelCommand(
                name,
                new ru.liga.optimalpacking.packages.editparcel.dto.Parcel(width, height, newName)));

        log.info("Посылка {} отредактирована", name);
    }

    /**
     * Получение посылки по названию
     *
     * @param name Название посылки
     */
    @ShellMethod(value = "Получение посылки по названию", key = "get")
    public void getParcel(@ShellOption({"--name"}) String name) {
        var parcel = pipeline.send(new GetParcelQuery(name));

        log.info("Посылка: {}", parcel.getParcel());
    }

    /**
     * Получение списка посылок
     */
    @ShellMethod(value = "Получение списка посылок", key = "list")
    public void getParcels() {
        var parcels = pipeline.send(new GetParcelsQuery());

        log.info("Посылки: {}", parcels.parcels());
    }

    /**
     * Погрузка посылок
     *
     * @param file             Файл с данными по посылкам для погрузки
     * @param maxTrucks        Максимальное число грузовиков
     * @param packingAlgorithm Алгоритм упаковки
     */
    @ShellMethod(value = "Погрузка посылок", key = "import")
    public void importPackages(
            @ShellOption({"--userId"}) String userId,
            @ShellOption({"--file"}) String file,
            @ShellOption({"--maxTrucks"}) int maxTrucks,
            @ShellOption({"--packingAlgorithm"}) PackingAlgorithm packingAlgorithm) {
        pipeline.send(new ImportPackagesCommand(userId, file, maxTrucks, packingAlgorithm));
    }
}


