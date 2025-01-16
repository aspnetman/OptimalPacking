package ru.liga.optimalpacking.packages.editparcel.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class Parcel {
    private final int width;
    private final int height;
    private final String name;
}
