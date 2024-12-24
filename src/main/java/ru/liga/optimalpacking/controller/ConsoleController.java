package ru.liga.optimalpacking.controller;

import an.awesome.pipelinr.Pipeline;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.liga.optimalpacking.packages.exportpackages.ExportPackagesCommand;
import ru.liga.optimalpacking.packages.importpackages.ImportPackagesCommand;
import ru.liga.optimalpacking.packages.importpackages.FileParcer;
import ru.liga.optimalpacking.packages.importpackages.dto.PackingAlgorithm;

import java.util.Scanner;

@Slf4j
@RequiredArgsConstructor
public class ConsoleController {

    private final Pipeline pipeline;

    public void listen() {
        try {
            var scanner = new Scanner(System.in);

            while (scanner.hasNextLine()) {
                String command = scanner.nextLine();
                if (command.equals("exit")) {
                    System.exit(0);
                }

                if (command.equals("export")) {
                    pipeline.send(new ExportPackagesCommand("[{\"parcels\":[{\"width\":3,\"height\":4}],\"empty\":false,\"id\":\"5eadeb00-b1c3-49d3-b66a-4ff2951b5836\"},{\"parcels\":[{\"width\":4,\"height\":3},{\"width\":2,\"height\":5}],\"empty\":false,\"id\":\"83af1def-ac1e-4b91-b28d-45170e66d43a\"},{\"parcels\":[{\"width\":4,\"height\":3},{\"width\":2,\"height\":5}],\"empty\":false,\"id\":\"83af1def-ac1e-4b91-b28d-45170e66d43a\"},{\"parcels\":[{\"width\":5,\"height\":2},{\"width\":3,\"height\":3}],\"empty\":false,\"id\":\"68b85da0-1ad4-4d57-bf61-af5cdceb63a6\"},{\"parcels\":[{\"width\":5,\"height\":2},{\"width\":3,\"height\":3}],\"empty\":false,\"id\":\"68b85da0-1ad4-4d57-bf61-af5cdceb63a6\"},{\"parcels\":[{\"width\":4,\"height\":2},{\"width\":2,\"height\":4}],\"empty\":false,\"id\":\"fe1fc21d-7f0c-4d94-8fcb-03e62dd5822e\"},{\"parcels\":[{\"width\":4,\"height\":2},{\"width\":2,\"height\":4}],\"empty\":false,\"id\":\"fe1fc21d-7f0c-4d94-8fcb-03e62dd5822e\"},{\"parcels\":[{\"width\":6,\"height\":1}],\"empty\":false,\"id\":\"50aa28d4-a899-4514-a9a5-f5335d509be3\"},{\"parcels\":[{\"width\":1,\"height\":6},{\"width\":5,\"height\":1}],\"empty\":false,\"id\":\"cb565ee2-b963-458a-a984-a5158e36721e\"},{\"parcels\":[{\"width\":1,\"height\":6},{\"width\":5,\"height\":1}],\"empty\":false,\"id\":\"cb565ee2-b963-458a-a984-a5158e36721e\"}]"));
                }
                else {
                    pipeline.send(new ImportPackagesCommand(FileParcer.readParcelsFromFile(command), 20, PackingAlgorithm.DensePacking));
                }
            }
        } catch (Exception exception) {
            log.error("Error", exception);
        }
    }
}


