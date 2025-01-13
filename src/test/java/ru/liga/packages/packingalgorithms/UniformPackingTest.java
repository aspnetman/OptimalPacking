package ru.liga.packages.packingalgorithms;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.liga.optimalpacking.packages.shared.entities.Parcel;
import ru.liga.optimalpacking.packages.importpackages.entities.PackingResult;
import ru.liga.optimalpacking.packages.importpackages.packingAlgorithms.UniformPacking;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class UniformPackingTest {

    private UniformPacking uniformPacking;

    @BeforeEach
    void setUp() {
        uniformPacking = new UniformPacking();
    }

    @Test
    void testPackWithOneTruckAndTwoSmallParcels() {
        // Создаем две маленькие посылки
        Parcel smallParcel1 = new Parcel("small1", new char[][]{{'#'}}, 's', 1, 1);
        Parcel smallParcel2 = new Parcel("small2", new char[][]{{'#'}}, 's', 1, 1);

        // Создаем список посылок
        List<Parcel> parcels = new ArrayList<>();
        parcels.add(smallParcel1);
        parcels.add(smallParcel2);

        // Выполняем упаковку с одним грузовиком
        PackingResult result = uniformPacking.pack(parcels, 1);

        // Проверяем результат
        assertThat(result.trucks()).hasSize(1); // Один грузовик
        assertThat(result.trucks().get(0)).isNotNull(); // Грузовик существует
        assertThat(result.trucks().get(0).isEmpty()).isFalse(); // Грузовик не пустой
        assertThat(result.notPackedParcels()).isEmpty(); // Все посылки упакованы
    }

    @Test
    void testPackWithTwoTrucksAndThreeMediumParcels() {
        // Создаем три средние посылки
        Parcel mediumParcel1 = new Parcel("medium1", new char[][]{
                {'#', '#'},
                {'#', '#'}
        }, 'm', 2, 2);
        Parcel mediumParcel2 = new Parcel("medium2", new char[][]{
                {'#', '#'},
                {'#', '#'}
        }, 'm', 2, 2);
        Parcel mediumParcel3 = new Parcel("medium3", new char[][]{
                {'#', '#'},
                {'#', '#'}
        }, 'm', 2, 2);

        // Создаем список посылок
        List<Parcel> parcels = new ArrayList<>();
        parcels.add(mediumParcel1);
        parcels.add(mediumParcel2);
        parcels.add(mediumParcel3);

        // Выполняем упаковку с двумя грузовиками
        PackingResult result = uniformPacking.pack(parcels, 2);

        // Проверяем результат
        assertThat(result.trucks()).hasSize(2); // Два грузовика
        assertThat(result.trucks().get(0)).isNotNull(); // Первый грузовик существует
        assertThat(result.trucks().get(0).isEmpty()).isFalse(); // Первый грузовик не пустой
        assertThat(result.trucks().get(1)).isNotNull(); // Второй грузовик существует
        assertThat(result.trucks().get(1).isEmpty()).isFalse(); // Второй грузовик не пустой
        assertThat(result.notPackedParcels()).hasSize(0); // Одна посылка не упакована
    }

    @Test
    void testPackWithNoTrucks() {
        // Создаем одну маленькую посылку
        Parcel smallParcel = new Parcel("small", new char[][]{{'#'}}, 's', 1, 1);

        // Создаем список посылок
        List<Parcel> parcels = new ArrayList<>();
        parcels.add(smallParcel);

        // Выполняем упаковку с нулевым количеством грузовиков
        PackingResult result = uniformPacking.pack(parcels, 0);

        // Проверяем результат
        assertThat(result.trucks()).isEmpty(); // Нет грузовиков
        assertThat(result.notPackedParcels()).containsExactly(smallParcel); // Все посылки остались неупакованными
    }

    @Test
    void testPackWithInsufficientTrucks() {
        // Создаем пять маленьких посылок
        Parcel smallParcel1 = new Parcel("small1", new char[][]{{'#'}}, 's', 1, 1);
        Parcel smallParcel2 = new Parcel("small2", new char[][]{{'#'}}, 's', 1, 1);
        Parcel smallParcel3 = new Parcel("small3", new char[][]{{'#'}}, 's', 1, 1);
        Parcel smallParcel4 = new Parcel("small4", new char[][]{{'#'}}, 's', 1, 1);
        Parcel smallParcel5 = new Parcel("small5", new char[][]{{'#'}}, 's', 1, 1);

        // Создаем список посылок
        List<Parcel> parcels = new ArrayList<>();
        parcels.add(smallParcel1);
        parcels.add(smallParcel2);
        parcels.add(smallParcel3);
        parcels.add(smallParcel4);
        parcels.add(smallParcel5);

        // Выполняем упаковку с четырьмя грузовиками
        PackingResult result = uniformPacking.pack(parcels, 4);

        // Проверяем результат
        assertThat(result.trucks()).hasSize(4); // Четыре грузовика
        assertThat(result.notPackedParcels()).hasSize(0); // Одна посылка осталась неупакованной
    }
}
