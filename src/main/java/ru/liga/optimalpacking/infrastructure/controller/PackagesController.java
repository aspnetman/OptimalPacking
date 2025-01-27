package ru.liga.optimalpacking.infrastructure.controller;

import an.awesome.pipelinr.Pipeline;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.liga.optimalpacking.packages.deleteparcel.DeleteParcelCommand;
import ru.liga.optimalpacking.packages.editparcel.EditParcelCommand;
import ru.liga.optimalpacking.packages.exportpackages.ExportPackagesCommand;
import ru.liga.optimalpacking.packages.getparcel.GetParcelQuery;
import ru.liga.optimalpacking.packages.getparcels.GetParcelsQuery;
import ru.liga.optimalpacking.packages.importpackages.ImportPackagesCommand;
import ru.liga.optimalpacking.packages.importpackages.dto.PackingAlgorithm;

@ShellComponent
@RequiredArgsConstructor
public class PackagesController {
    private final Pipeline pipeline;

    /**
     * Удаление посылки из репозитория
     *
     * @param name Название посылки
     */
    @ShellMethod(value = "Удаление посылки из репозитория", key = "delete")
    public void deleteParcel(@ShellOption({"--name"}) String name) {
        pipeline.send(new DeleteParcelCommand(name));
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
    }

    /**
     * Получение посылки по названию
     *
     * @param name Название посылки
     */
    @ShellMethod(value = "Получение посылки по названию", key = "get")
    public void getParcel(@ShellOption({"--name"}) String name) {
        pipeline.send(new GetParcelQuery(name));
    }

    /**
     * Получение списка посылок
     */
    @ShellMethod(value = "Получение списка посылок", key = "list")
    public void getParcels() {
        pipeline.send(new GetParcelsQuery());
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

    /**
     * Выгрузка посылок
     *
     * @param userId     Идентификатор пользователя
     * @param trucksFile Файл с грузовиками и посылками
     */
    @ShellMethod(value = "Выгрузка посылок", key = "export")
    public void exportPackages(
            @ShellOption({"--userId"}) String userId,
            @ShellOption({"--trucksFile"}) String trucksFile) {
        pipeline.send(new ExportPackagesCommand(userId, trucksFile));
    }
}