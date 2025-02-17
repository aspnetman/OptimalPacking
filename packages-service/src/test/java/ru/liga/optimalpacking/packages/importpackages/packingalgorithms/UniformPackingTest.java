package ru.liga.optimalpacking.packages.importpackages.packingalgorithms;

import org.junit.jupiter.api.Test;
import ru.liga.optimalpacking.packages.importpackages.entities.PackingResult;
import ru.liga.optimalpacking.packages.shared.entities.Parcel;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class UniformPackingTest {

    @Test
    void testPack_EmptyParcelsList() {
        // Arrange
        UniformPacking uniformPacking = new UniformPacking();
        List<Parcel> parcels = List.of();
        int maxTrucks = 2;

        // Act
        PackingResult result = uniformPacking.pack(parcels, maxTrucks);

        // Assert
        assertThat(result.trucks()).isEmpty(); // Нет грузовиков
        assertThat(result.notPackedParcels()).isEmpty(); // Нет посылок
    }

    @Test
    void testPack_SingleParcelFitsExactly() {
        // Arrange
        UniformPacking uniformPacking = new UniformPacking();
        List<Parcel> parcels = List.of(
                new Parcel("Parcel1", "Rectangle", 'A', 6, 6) // Посылка занимает весь грузовик
        );
        int maxTrucks = 1;

        // Act
        PackingResult result = uniformPacking.pack(parcels, maxTrucks);

        // Assert
        assertThat(result.trucks()).hasSize(1); // Один грузовик
        assertThat(result.notPackedParcels()).isEmpty(); // Все посылки поместились
        assertThat(result.trucks().get(0).getParcelsCount()).isEqualTo(1); // В грузовике одна посылка
    }

    @Test
    void testPack_MoreTrucksThanParcels() {
        // Arrange
        UniformPacking uniformPacking = new UniformPacking();
        List<Parcel> parcels = List.of(
                new Parcel("Parcel1", "Rectangle", 'A', 2, 2),
                new Parcel("Parcel2", "Rectangle", 'B', 2, 2)
        );
        int maxTrucks = 3;

        // Act
        PackingResult result = uniformPacking.pack(parcels, maxTrucks);

        // Assert
        assertThat(result.trucks()).hasSize(2); // Ожидаем 2 грузовика (так как посылок меньше, чем maxTrucks)
        assertThat(result.notPackedParcels()).isEmpty(); // Все посылки должны быть упакованы
        assertThat(result.trucks().get(0).getParcelsCount()).isEqualTo(1); // В первом грузовике 1 посылка
        assertThat(result.trucks().get(1).getParcelsCount()).isEqualTo(1); // Во втором грузовике 1 посылка
    }

    @Test
    void testPack_NoTrucksAvailable() {
        // Arrange
        UniformPacking uniformPacking = new UniformPacking();
        List<Parcel> parcels = List.of(
                new Parcel("Parcel1", "Rectangle", 'A', 2, 2),
                new Parcel("Parcel2", "Rectangle", 'B', 2, 2)
        );
        int maxTrucks = 0;

        // Act
        PackingResult result = uniformPacking.pack(parcels, maxTrucks);

        // Assert
        assertThat(result.trucks()).isEmpty(); // Нет грузовиков
        assertThat(result.notPackedParcels()).hasSize(2); // Все посылки не упакованы
    }
}
