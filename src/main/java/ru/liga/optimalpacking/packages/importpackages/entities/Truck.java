package ru.liga.optimalpacking.packages.importpackages.entities;

import lombok.Getter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import ru.liga.optimalpacking.packages.shared.entities.Parcel;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Getter
public class Truck {
    private static final int TRUCK_WIDTH = 10;
    private static final int TRUCK_HEIGHT = 10;

    private transient final char[][] grid;
    private final List<Parcel> parcels;
    private final String id;

    public Truck() {
        grid = new char[TRUCK_WIDTH][TRUCK_HEIGHT];
        parcels = new ArrayList<>();
        id = UUID.randomUUID().toString();
        initializeGrid();
    }

    @SneakyThrows
    private void initializeGrid() {
        for (int i = 0; i < TRUCK_WIDTH; i++) {
            for (int j = 0; j < TRUCK_HEIGHT; j++) {
                grid[i][j] = '.';
            }
        }
    }

    public boolean isEmpty() {
        for (int i = 0; i < TRUCK_WIDTH; i++) {
            for (int j = 0; j < TRUCK_HEIGHT; j++) {
                if (grid[i][j] != '.') {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean tryToFitParcel(Parcel parcel) {
        for (int i = 0; i <= TRUCK_WIDTH - parcel.width(); i++) {
            for (int j = 0; j <= TRUCK_HEIGHT - parcel.height(); j++) {
                if (canPlaceParcelAt(i, j, parcel)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void placeParcel(Parcel parcel) {
        for (int i = 0; i <= TRUCK_WIDTH - parcel.width(); i++) {
            for (int j = 0; j <= TRUCK_HEIGHT - parcel.height(); j++) {
                if (canPlaceParcelAt(i, j, parcel)) {
                    occupySpace(i, j, parcel);
                    parcels.add(parcel);
                    return;
                }
            }
        }
    }

    private boolean canPlaceParcelAt(int x, int y, Parcel parcel) {
        for (int i = x; i < x + parcel.width(); i++) {
            for (int j = y; j < y + parcel.height(); j++) {
                if (i < grid.length && j < grid[i].length) { // Проверка на выход за границы массива
                    if (grid[i][j] != '.' || (parcel.form()[i - x][j - y] == '#' && grid[i][j] != '.')) {
                        return false;
                    }
                } else {
                    return false; // Если вышли за границу массива, сразу возвращаем false
                }
            }
        }
        return true;
    }

    private void occupySpace(int x, int y, Parcel parcel) {
        for (int i = x; i < x + parcel.width(); i++) {
            for (int j = y; j < y + parcel.height(); j++) {
                if (parcel.form()[i - x][j - y] == '#') {
                    grid[i][j] = parcel.symbol();
                }
            }
        }
    }
}
