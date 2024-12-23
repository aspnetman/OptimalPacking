package ru.liga.optimalpacking.packages.importpackages.entities;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import ru.liga.optimalpacking.packages.importpackages.dto.Parcel;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Getter
public class Truck {
    // Для теста пойдут и константы
    private static final int TRUCK_WIDTH = 6;
    private static final int TRUCK_HEIGHT = 6;

    private final char[][] grid = new char[TRUCK_WIDTH][TRUCK_HEIGHT];

    private final List<Parcel> parcels = new ArrayList<>();

    private final String Id = UUID.randomUUID().toString();

    public Truck() {
        initializeGrid();
    }

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
                if (grid[i][j] == 'X') {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean tryToFitParcel(Parcel parcel) {
        for (int i = 0; i <= TRUCK_WIDTH - parcel.getWidth(); i++) {
            for (int j = 0; j <= TRUCK_HEIGHT - parcel.getHeight(); j++) {
                if (canPlaceParcelAt(i, j, parcel)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void placeParcel(Parcel parcel) {
        for (int i = 0; i <= TRUCK_WIDTH - parcel.getWidth(); i++) {
            for (int j = 0; j <= TRUCK_HEIGHT - parcel.getHeight(); j++) {
                if (canPlaceParcelAt(i, j, parcel)) {
                    occupySpace(i, j, parcel);
                    parcels.add(parcel);
                    return;
                }
            }
        }
    }

    private boolean canPlaceParcelAt(int x, int y, Parcel parcel) {
        for (int i = x; i < x + parcel.getWidth(); i++) {
            for (int j = y; j < y + parcel.getHeight(); j++) {
                if (grid[i][j] != '.') {
                    return false;
                }
            }
        }
        return true;
    }

    private void occupySpace(int x, int y, Parcel parcel) {
        for (int i = x; i < x + parcel.getWidth(); i++) {
            for (int j = y; j < y + parcel.getHeight(); j++) {
                grid[i][j] = 'X';
            }
        }
    }

    public void printGrid() {
        log.info("Текущий вид кузова:");
        var gird = "";
        for (int j = 0; j < TRUCK_HEIGHT; j++) {
            for (int i = 0; i < TRUCK_WIDTH; i++) {
                 gird += grid[i][j];
            }
            log.debug(gird);
        }
        log.info(System.lineSeparator());;
    }
}
