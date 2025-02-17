package ru.liga.optimalpacking.billings.addbilling.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "inbox", schema = "public")
public class InboxMessage {

    @Id
    @Column(columnDefinition = "UUID")
    private UUID id;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
}
