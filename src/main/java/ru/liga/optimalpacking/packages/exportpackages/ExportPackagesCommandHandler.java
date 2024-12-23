package ru.liga.optimalpacking.packages.exportpackages;

import an.awesome.pipelinr.Command;
import ru.liga.optimalpacking.packages.exportpackages.dto.ExportPackagesResponse;

// Принимает загруженные машины и отдаёт на выход файл со списком посылок
public class ExportPackagesCommandHandler implements Command.Handler<ExportPackagesCommand, ExportPackagesResponse> {
    @Override
    public ExportPackagesResponse handle(ExportPackagesCommand exportPackagesCommand) {
        return new ExportPackagesResponse("packages.json");
    }
}