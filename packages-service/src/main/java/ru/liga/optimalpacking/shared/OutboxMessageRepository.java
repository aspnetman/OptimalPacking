package ru.liga.optimalpacking.shared;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.liga.optimalpacking.shared.entities.OutboxMessage;

import java.util.List;

public interface OutboxMessageRepository extends JpaRepository<OutboxMessage, Long> {
    List<OutboxMessage> findAllByProcessed(boolean processed);
}
