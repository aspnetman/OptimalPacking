package ru.liga.optimalpacking.billings.shared.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@IdClass(BillingPK.class)
@Table(name = "billings", schema = "public")
public class Billing {

    @Id
    @Column(name = "user_id")
    private String userId;

    @Column(name = "description")
    private String description;

    @Id
    @Column(name = "type")
    private String type;

    @Id
    @Column(name = "date")
    private OffsetDateTime date;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "cost")
    private BigDecimal cost;
}
