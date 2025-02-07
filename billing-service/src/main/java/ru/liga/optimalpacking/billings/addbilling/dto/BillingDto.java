package ru.liga.optimalpacking.billings.addbilling.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class BillingDto {

    private UUID messageId;

    private String userId;

    private String type;

    private OffsetDateTime date;

    private int quantity;
}
