package ru.liga.packages.packingalgorithms;

import org.junit.jupiter.api.Test;
import ru.liga.optimalpacking.packages.importpackages.entities.PackingResult;
import ru.liga.optimalpacking.packages.importpackages.packingalgorithms.DensePacking;
import ru.liga.optimalpacking.packages.shared.entities.Parcel;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class DensePackingTest {

    @Test
    void testPackingWithSmallParcels() {
        List<Parcel> parcels = new ArrayList<>();
        // Создаем три средние посылки
        parcels.add(new Parcel("medium1", new char[][]{
                {'#', '#'},
                {'#', '#'}
        }, 'm', 2, 2));

        parcels.add(new Parcel("medium2", new char[][]{
                {'#', '#'},
                {'#', '#'}
        }, 'm', 2, 2));

        parcels.add(new Parcel("medium3", new char[][]{
                {'#', '#'},
                {'#', '#'}
        }, 'm', 2, 2));

        DensePacking packingAlgorithm = new DensePacking();
        PackingResult result = packingAlgorithm.pack(parcels);

        assertThat(result.trucks()).hasSize(1);
        assertThat(result.notPackedParcels()).isEmpty();
    }


        @Test
        void testPackingWithLargeParcels() {
            List<Parcel> parcels = new ArrayList<>();
            // Создаем три средние посылки
            parcels.add(new Parcel("Parcel1", new char[][]{{'#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#'}},
                    '#', 10, 1));

            parcels.add(new Parcel("Parcel1", new char[][]{{'#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#'}},
                    '#', 10, 1));

            parcels.add(new Parcel("Parcel1", new char[][]{{'#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#'}},
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
