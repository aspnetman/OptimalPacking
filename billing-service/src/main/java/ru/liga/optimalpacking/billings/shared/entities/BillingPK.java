package ru.liga.optimalpacking.billings.shared.entities;

import jakarta.persistence.Column;
import jakarta.persistence.IdClass;

import java.io.Serializable;
import java.time.OffsetDateTime;

@IdClass(BillingPK.class)
public class BillingPK implements Serializable {
    @Column(name = "user_id")
    private String userId;

    @Column(name = "date")
    private OffsetDateTime date;

    @Column(name = "type")
    private String type;
}
