package ru.liga.packages.packingalgorithms;

import org.junit.Test;
import ru.liga.optimalpacking.packages.importpackages.PackingAlgorithms;
import ru.liga.optimalpacking.packages.importpackages.dto.Parcel;
import ru.liga.optimalpacking.packages.importpackages.entities.Truck;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class DensePackingTest {

    @Test
    public void testDensePackingWithSmallNumberOfParcels() {
        List<Parcel> parcels = Arrays.asList(
                new Parcel(1, 1),
                new Parcel(1, 1)
        );

        var packingResult = PackingAlgorithms.densePacking(parcels);

        assertEquals(1, packingResult.trucks().size());
        assertFalse(packingResult.trucks().get(0).isEmpty());
    }

    @Test
    public void testDensePackingWithLargeNumberOfParcels() {
        List<Parcel> parcels = Arrays.asList(
                new Parcel(1, 1), new Parcel(1, 1), new Parcel(1, 1),
                new Parcel(1, 1), new Parcel(1, 1), new Parcel(1, 1),
                new Parcel(1, 1), new Parcel(1, 1), new Parcel(1, 1),
                new Parcel(1, 1), new Parcel(1, 1), new Parcel(1, 1)
        );

        var packingResult = PackingAlgorithms.densePacking(parcels);

        assertEquals(1, packingResult.trucks().size());
        for (Truck truck : packingResult.trucks()) {
            assertFalse(truck.isEmpty());
        }
    }

    @Test
    public void testDensePackingWithOverloadedParcels() {
        List<Parcel> parcels = Arrays.asList(
                new Parcel(1, 1), new Parcel(1, 1), new Parcel(1, 1),
                new Parcel(1, 1), new Parcel(1, 1), new Parcel(1, 1),
                new Parcel(1, 1), new Parcel(1, 1), new Parcel(1, 1),
                new Parcel(1, 1), new Parcel(1, 1), new Parcel(1, 1),
                new Parcel(1, 1), new Parcel(1, 1), new Parcel(1, 1)
        );

        var packingResult = PackingAlgorithms.densePacking(parcels);

        assertEquals(1, packingResult.trucks().size());
        for (Truck truck : packingResult.trucks()) {
            assertFalse(truck.isEmpty());
        }
    }
}
