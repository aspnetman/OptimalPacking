package ru.liga.packages.packingalgorithms;


import org.junit.Test;
import ru.liga.optimalpacking.packages.importpackages.PackingAlgorithms;
import ru.liga.optimalpacking.packages.importpackages.dto.Parcel;
import ru.liga.optimalpacking.packages.importpackages.entities.Truck;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class UniformPackingTest {

    @Test
    public void testUniformPackingWithSmallNumberOfParcels() {
        List<Parcel> parcels = Arrays.asList(
                new Parcel(1, 1),
                new Parcel(1, 1)
        );

        var packingResult = PackingAlgorithms.uniformPacking(parcels, 2);

        assertEquals(2, packingResult.trucks().size());
        assertFalse(packingResult.trucks().get(0).isEmpty());
        assertFalse(packingResult.trucks().get(1).isEmpty());
    }

    @Test
    public void testUniformPackingWithLargeNumberOfParcels() {
        List<Parcel> parcels = Arrays.asList(
                new Parcel(1, 1), new Parcel(1, 1), new Parcel(1, 1),
                new Parcel(1, 1), new Parcel(1, 1), new Parcel(1, 1),
                new Parcel(1, 1), new Parcel(1, 1), new Parcel(1, 1),
                new Parcel(1, 1), new Parcel(1, 1), new Parcel(1, 1)
        );

        var packingResult = PackingAlgorithms.uniformPacking(parcels, 15);

        assertEquals(12, packingResult.trucks().size());
        for (Truck truck : packingResult.trucks()) {
            assertFalse(truck.isEmpty());
        }
    }

    @Test
    public void testUniformPackingWithMaxTrucksLimit() {
        List<Parcel> parcels = Arrays.asList(
                new Parcel(1, 1), new Parcel(1, 1), new Parcel(1, 1),
                new Parcel(1, 1), new Parcel(1, 1), new Parcel(1, 1),
                new Parcel(1, 1), new Parcel(1, 1), new Parcel(1, 1),
                new Parcel(1, 1), new Parcel(1, 1), new Parcel(1, 1),
                new Parcel(1, 1), new Parcel(1, 1), new Parcel(1, 1)
        );

        var packingResult = PackingAlgorithms.uniformPacking(parcels, 10);

        assertEquals(10, packingResult.trucks().size());
        for (Truck truck : packingResult.trucks()) {
            assertFalse(truck.isEmpty());
        }
    }
}
