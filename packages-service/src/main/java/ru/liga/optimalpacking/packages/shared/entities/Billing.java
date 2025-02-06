package ru.liga.optimalpacking.packages.shared.entities;

import lombok.AllArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
public class Billing {

    private String userId;

    private String type;

    private String date;

    private int quantity;
}