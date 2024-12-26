package ru.liga.packages.packingalgorithms;


import org.junit.jupiter.api.Test;
import ru.liga.optimalpacking.packages.importpackages.PackingAlgorithms;
import ru.liga.optimalpacking.packages.importpackages.dto.Parcel;
import ru.liga.optimalpacking.packages.importpackages.entities.Truck;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class UniformPackingTest {

    @Test
    public void testUniformPackingWithSmallNumberOfParcels() {
        List<Parcel> parcels = Arrays.asList(
                new Parcel(1, 1),
                new Parcel(1, 1)
        );

        var packingResult = PackingAlgorithms.uniformPacking(parcels, 2);

        assertThat(packingResult.trucks())
                .hasSize(2);
        assertThat(packingResult.trucks().get(0).isEmpty())
                .isFalse();
        assertThat(packingResult.trucks().get(1).isEmpty())
                .isFalse();
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

        assertThat(packingResult.trucks())
                .hasSize(12);
        for (Truck truck : packingResult.trucks()) {
            assertThat(truck.isEmpty())
                    .isFalse();
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

        assertThat(packingResult.trucks())
                .hasSize(10);
        for (Truck truck : packingResult.trucks()) {
            assertThat(truck.isEmpty())
                    .isFalse();
        }
    }
}
