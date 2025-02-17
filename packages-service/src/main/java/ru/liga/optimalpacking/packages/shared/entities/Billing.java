package ru.liga.optimalpacking.packages.shared.entities;

import lombok.AllArgsConstructor;

import java.time.OffsetDateTime;
import java.util.UUID;

@AllArgsConstructor
public class Billing {

    public final UUID messageId;

    private String userId;

    private String type;

    private OffsetDateTime date;

    private int quantity;
}