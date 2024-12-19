package ru.liga.packages.importpackages;

import org.junit.Test;
import ru.liga.optimalpacking.packages.importpackages.ImportPackagesCommand;
import ru.liga.optimalpacking.packages.importpackages.ImportPackagesCommandHandler;
import ru.liga.optimalpacking.packages.importpackages.dto.Parcel;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ImportPackagesCommandHandlerTest {

    @Test
    public void testSimplePacking() {
        var importPackagesCommandHandler = new ImportPackagesCommandHandler();
        // Тестовый случай: одна посылка, которая точно помещается в кузов
        List<Parcel> parcels = new ArrayList<>();
        parcels.add(new Parcel(3, 4)); // Ширина 3, Высота 4

        int expectedMachines = 1;
        int actualMachines = importPackagesCommandHandler.handle(new ImportPackagesCommand(parcels)).getTotalMachinesNeeded();

        assertEquals(expectedMachines, actualMachines);
    }

    @Test
    public void testMultipleParcelsOneTruck() {
        var importPackagesCommandHandler = new ImportPackagesCommandHandler();
        // Тестовый случай: несколько посылок, которые вместе помещаются в один кузов
        List<Parcel> parcels = new ArrayList<>();
        parcels.add(new Parcel(3, 4)); // Ширина 3, Высота 4
        parcels.add(new Parcel(2, 5)); // Ширина 2, Высота 5
        parcels.add(new Parcel(1, 6)); // Ширина 1, Высота 6

        int expectedMachines = 1;
        int actualMachines = importPackagesCommandHandler.handle(new ImportPackagesCommand(parcels)).getTotalMachinesNeeded();

        assertEquals(expectedMachines, actualMachines);
    }

    @Test
    public void testMultipleParcelsTwoTrucks() {
        var importPackagesCommandHandler = new ImportPackagesCommandHandler();
        // Тестовый случай: несколько посылок, которые требуют двух кузовов
        List<Parcel> parcels = new ArrayList<>();
        parcels.add(new Parcel(6, 6)); // Ширина 6, Высота 6
        parcels.add(new Parcel(3, 4)); // Ширина 3, Высота 4
        parcels.add(new Parcel(2, 5)); // Ширина 2, Высота 5
        parcels.add(new Parcel(1, 6)); // Ширина 1, Высота 6

        int expectedMachines = 2;
        int actualMachines = importPackagesCommandHandler.handle(new ImportPackagesCommand(parcels)).getTotalMachinesNeeded();

        assertEquals(expectedMachines, actualMachines);
    }

    @Test
    public void testInvalidParcel() {
        var importPackagesCommandHandler = new ImportPackagesCommandHandler();
        // Тестовый случай: посылка, которая не помещается в кузов
        List<Parcel> parcels = new ArrayList<>();
        parcels.add(new Parcel(7, 7)); // Ширина 7, Высота 7

        int expectedMachines = 0;
        int actualMachines = importPackagesCommandHandler.handle(new ImportPackagesCommand(parcels)).getTotalMachinesNeeded();

        assertEquals(expectedMachines, actualMachines);
    }

    @Test
    public void testEmptyInput() {
        var importPackagesCommandHandler = new ImportPackagesCommandHandler();
        // Тестовый случай: пустой список посылок
        List<Parcel> parcels = new ArrayList<>();

        int expectedMachines = 0;
        int actualMachines = importPackagesCommandHandler.handle(new ImportPackagesCommand(parcels)).getTotalMachinesNeeded();

        assertEquals(expectedMachines, actualMachines);
    }
}
