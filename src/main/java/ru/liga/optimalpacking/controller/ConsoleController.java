package ru.liga.optimalpacking.controller;

import an.awesome.pipelinr.Pipeline;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.liga.optimalpacking.packages.deleteparcel.DeleteParcelCommand;
import ru.liga.optimalpacking.packages.editparcel.EditParcelCommand;
import ru.liga.optimalpacking.packages.getparcel.GetParcelQuery;
import ru.liga.optimalpacking.packages.getparcels.GetParcelsQuery;
import ru.liga.optimalpacking.packages.importpackages.dto.PackingAlgorithm;

@Slf4j
@RequiredArgsConstructor
public class ConsoleController {
    private static final String ExportCommand = "export";
    private final Pipeline pipeline;

    public void deleteParcel(String name) {
        pipeline.send(new DeleteParcelCommand(name));

        log.info("Посылка {} удалена", name);
    }

    public void editParcel(String name, ru.liga.optimalpacking.packages.editparcel.dto.Parcel parcel) {
        pipeline.send(new EditParcelCommand(name, parcel));

        log.info("Посылка {} отредактирована", name);
    }

    public void getParcel(String name) {
        var parcel = pipeline.send(new GetParcelQuery(name));

        log.info("Посылка: {}", parcel.getParcel());
    }

    public void getParcels() {
        var parcels = pipeline.send(new GetParcelsQuery());

        log.info("Посылки: {}", parcels.parcels());
    }

    public void importPackages(String file, int maxTrucks, PackingAlgorithm packingAlgorithm) {
    }
}


