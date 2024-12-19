package ru.liga.optimalpacking.packages.importpackages.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class Parcel {
    private int width;
    private int height;

    public Parcel(int width, int height) {
        this.width = width;
        this.height = height;
    }
}
