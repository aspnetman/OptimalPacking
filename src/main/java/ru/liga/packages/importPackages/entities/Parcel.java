package ru.liga.packages.importPackages.entities;

import lombok.Data;

@Data
public class Parcel {
    int width;
    int height;

    public Parcel(int width, int height) {
        this.width = width;
        this.height = height;
    }
}
