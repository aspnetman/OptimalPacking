package ru.liga.optimalpacking.packages.importpackages.packingalgorithms;

import org.junit.jupiter.api.Test;
import ru.liga.optimalpacking.packages.importpackages.entities.PackingResult;
import ru.liga.optimalpacking.packages.shared.entities.Parcel;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class DensePackingTest {

    @Test
    void testPack_AllParcelsFitInOneTruck() {
        // Arrange
        DensePacking densePacking = new DensePacking();
        List<Parcel> parcels = List.of(
                new Parcel("Parcel1", "Rectangle", 'A', 2, 2),
                new Parcel("Parcel2", "Rectangle", 'B', 2, 2),
                new Parcel("Parcel3", "Rectangle", 'C', 2, 2)
        );

        // Act
        PackingResult result = densePacking.pack(parcels);

        // Assert
        assertThat(result.trucks()).hasSize(1); // Все посылки должны поместиться в один грузовик
        assertThat(result.notPackedParcels()).isEmpty(); // Нет посылок, которые не поместились
        assertThat(result.trucks().get(0).getParcelsCount()).isEqualTo(3); // В грузовике должно быть 3 посылки
    }

    @Test
    void testPack_ParcelsFitInMultipleTrucks() {
        // Arrange
        DensePacking densePacking = new DensePacking();
        List<Parcel> parcels = List.of(
                new Parcel("Parcel1", "Rectangle", 'A', 4, 4), // Занимает 4x4
                new Parcel("Parcel2", "Rectangle", 'B', 4, 4),  // Занимает 4x4
                new Parcel("Parcel3", "Rectangle", 'C', 4, 4)   // Занимает 4x4
        );

        // Act
        PackingResult result = densePacking.pack(parcels);

        // Assert
        assertThat(result.trucks()).hasSize(3); // Ожидаем 3 грузовика
        assertThat(result.notPackedParcels()).isEmpty(); // Все посылки должны быть упакованы
        assertThat(result.trucks().get(0).getParcelsCount()).isEqualTo(1); // В первом грузовике 1 посылка
        assertThat(result.trucks().get(1).getParcelsCount()).isEqualTo(1); // Во втором грузовике 1 посылк
    }

    @Test
    void testPack_SomeParcelsDoNotFit() {
        // Arrange
        DensePacking densePacking = new DensePacking();
        List<Parcel> parcels = List.of(
                new Parcel("Parcel1", "Rectangle", 'A', 6, 6), // Занимает весь грузовик
                new Parcel("Parcel2", "Rectangle", 'B', 2, 2),  // Не поместится
                new Parcel("Parcel3", "Rectangle", 'C', 2, 2)   // Не поместится
        );

        // Act
        PackingResult result = densePacking.pack(parcels);

        // Assert
        assertThat(result.trucks()).hasSize(2);
    }

    @Test
    void testPack_EmptyParcelsList() {
        // Arrange
        DensePacking densePacking = new DensePacking();
        List<Parcel> parcels = List.of();

        // Act
        PackingResult result = densePacking.pack(parcels);

        // Assert
        assertThat(result.trucks()).isEmpty(); // Нет грузовиков
        assertThat(result.notPackedParcels()).isEmpty(); // Нет посылок
    }

    @Test
    void testPack_SingleParcelFitsExactly() {
        // Arrange
        DensePacking densePacking = new DensePacking();
        List<Parcel> parcels = List.of(
                new Parcel("Parcel1", "Rectangle", 'A', 6, 6) // Посылка занимает весь грузовик
        );

        // Act
        PackingResult result = densePacking.pack(parcels);

        // Assert
        assertThat(result.trucks()).hasSize(1); // Один грузовик
        assertThat(result.notPackedParcels()).isEmpty(); // Все посылки поместились
        assertThat(result.trucks().get(0).getParcelsCount()).isEqualTo(1); // В грузовике одна посылка
    }
}