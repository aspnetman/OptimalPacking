package ru.liga.optimalpacking.packages.editparcel.dto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Parcel {
    private final int width;
    private final int height;
    private final String name;
}
