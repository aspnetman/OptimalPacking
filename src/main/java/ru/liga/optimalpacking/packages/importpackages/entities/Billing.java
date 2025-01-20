package ru.liga.optimalpacking.packages.importpackages.entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@RequiredArgsConstructor
public class Billing {
    private final String userId;
    private final String description;
    private final String type;
    private final LocalDate date;
    private final int quantity;
    private final BigDecimal cost;
}
