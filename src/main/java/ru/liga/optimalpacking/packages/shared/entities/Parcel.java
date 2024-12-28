package ru.liga.optimalpacking.packages.shared.entities;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Parcel {
    private final int width;
    private final int height;
    private final String name;
}
