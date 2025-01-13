package ru.liga.packages.packingalgorithms;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import ru.liga.optimalpacking.packages.shared.entities.Parcel;
import ru.liga.optimalpacking.packages.importpackages.entities.PackingResult;
import ru.liga.optimalpacking.packages.importpackages.packingAlgorithms.DensePacking;

import static org.assertj.core.api.Assertions.assertThat;

public class DensePackingTest {

    @Test
    void testPackingWithSmallParcels() {
        List<Parcel> parcels = new ArrayList<>();
        parcels.add(new Parcel("Parcel1", new char[][]{{'#'}, {'#'}}, '#', 1, 2));
        parcels.add(new Parcel("Parcel2", new char[][]{{'#', '#'}, {' ', '#'}}, '#', 2, 2));
        parcels.add(new Parcel("Parcel3", new char[][]{{'#', '#', '#'}, {'#', '#', '#'}}, '#', 3, 2));

        DensePacking packingAlgorithm = new DensePacking();
        PackingResult result = packingAlgorithm.pack(parcels);

        assertThat(result.trucks()).hasSize(1);
        assertThat(result.notPackedParcels()).isEmpty();
    }


        @Test
        void testPackingWithLargeParcels() {
            List<Parcel> parcels = new ArrayList<>();
            parcels.add(new Parcel("Parcel1", new char[][]{{'#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#'}},
                    '#', 10, 1));
            parcels.add(new Parcel("Parcel2", new char[][]{{'#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#'}},
                    '#', 10, 1));
            parcels.add(new Parcel("Parcel3", new char[][]{{'#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#'}},
                    '#', 10, 1));

            DensePacking packingAlgorithm = new DensePacking();
            PackingResult result = packingAlgorithm.pack(parcels);

            assertThat(result.trucks()).hasSize(3);
            assertThat(result.notPackedParcels()).isEmpty();
    }

            @Test
            void testPackingWithMixedSizeParcels() {
                List<Parcel> parcels = new ArrayList<>();
                parcels.add(new Parcel("Parcel1", new char[][]{{'#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#'}},
                        '#', 10, 1));
                parcels.add(new Parcel("Parcel2", new char[][]{{'#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#'}},
                        '#', 10, 1));
                parcels.add(new Parcel("Parcel3", new char[][]{{'#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#'}},
                        '#', 10, 1));

                DensePacking packingAlgorithm = new DensePacking();
                PackingResult result = packingAlgorithm.pack(parcels);

                assertThat(result.trucks()).hasSize(3);
                assertThat(result.notPackedParcels()).isEmpty();
    }
}
