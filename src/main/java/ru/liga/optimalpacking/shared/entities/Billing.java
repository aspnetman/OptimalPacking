package ru.liga.optimalpacking.shared.entities;

import java.math.BigDecimal;
import java.time.LocalDate;

public record Billing(String userId, String description, String type, LocalDate date, int quantity, BigDecimal cost) {
}
