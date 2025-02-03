package ru.liga.optimalpacking.billings.addbilling.dto;


import java.math.BigDecimal;
import java.time.LocalDate;

public class BillingDto {

    private String userId;

    private String description;

    private String type;

    private LocalDate date;

    private int quantity;

    private BigDecimal cost;
}
