package ru.liga.packages.packingalgorithms;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import ru.liga.optimalpacking.packages.importpackages.PackingAlgorithms;
import ru.liga.optimalpacking.packages.importpackages.dto.Parcel;
import ru.liga.optimalpacking.packages.importpackages.entities.Truck;

import static org.assertj.core.api.Assertions.assertThat;

public class DensePackingTest {

    @Test
    public void testDensePackingWithSmallNumberOfParcels() {
        List<Parcel> parcels = Arrays.asList(
                new Parcel(1, 1),
                new Parcel(1, 1)
        );

        var packingResult = PackingAlgorithms.densePacking(parcels);

        assertThat(packingResult.trucks())
                .hasSize(1);
        assertThat(packingResult.trucks())
                .hasSize(1);
        assertThat(packingResult.trucks().get(0).isEmpty())
                .isFalse();
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

        assertThat(packingResult.trucks())
                .hasSize(1);

        for (Truck truck : packingResult.trucks()) {
            assertThat(truck.isEmpty())
                    .isFalse();
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

        assertThat(packingResult.trucks())
                .hasSize(1);
        for (Truck truck : packingResult.trucks()) {
            assertThat(truck.isEmpty())
                    .isFalse();
        }
    }
}
