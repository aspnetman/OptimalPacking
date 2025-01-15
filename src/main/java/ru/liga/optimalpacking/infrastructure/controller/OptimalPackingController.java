package ru.liga.optimalpacking.infrastructure.controller;

import an.awesome.pipelinr.Pipeline;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.liga.optimalpacking.packages.deleteparcel.DeleteParcelCommand;
import ru.liga.optimalpacking.packages.editparcel.EditParcelCommand;
import ru.liga.optimalpacking.packages.getparcel.GetParcelQuery;
import ru.liga.optimalpacking.packages.getparcels.GetParcelsQuery;
import ru.liga.optimalpacking.packages.importpackages.ImportPackagesCommand;
import ru.liga.optimalpacking.packages.importpackages.dto.PackingAlgorithm;

@Slf4j
@RequiredArgsConstructor
public class OptimalPackingController {
    private final Pipeline pipeline;

    /**
     * Удаление посылки из репозитория
     *
     * @param name Название посылки
     */
    public void deleteParcel(String name) {
        pipeline.send(new DeleteParcelCommand(name));

        log.info("Посылка {} удалена", name);
    }

    /**
     * Редактирование посылки
     *
     * @param name   Текущее название
     * @param parcel Данные посылки для редактирования
     */
    public void editParcel(String name, ru.liga.optimalpacking.packages.editparcel.dto.Parcel parcel) {
        pipeline.send(new EditParcelCommand(name, parcel));

        log.info("Посылка {} отредактирована", name);
    }

    /**
     * Получение посылки по названию
     *
     * @param name Название посылки
     */
    public void getParcel(String name) {
        var parcel = pipeline.send(new GetParcelQuery(name));

        log.info("Посылка: {}", parcel.getParcel());
    }

    /**
     * Получение списка посылок
     */
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
    public void importPackages(String file, int maxTrucks, PackingAlgorithm packingAlgorithm) {
        pipeline.send(new ImportPackagesCommand(file, maxTrucks, packingAlgorithm));
    }
}


