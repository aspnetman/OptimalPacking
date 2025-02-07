package ru.liga.optimalpacking.billings.addbilling;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.liga.optimalpacking.billings.addbilling.entities.InboxMessage;

import java.util.UUID;

@Repository
public interface InboxRepository extends JpaRepository<InboxMessage, UUID> {
}