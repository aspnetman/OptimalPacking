package ru.liga.optimalpacking.outbox.processmessage;

import an.awesome.pipelinr.Command;
import ru.liga.optimalpacking.outbox.processmessage.dto.ProcessMessageResponse;
import ru.liga.optimalpacking.shared.entities.OutboxMessage;

public record ProcessMessageCommand(OutboxMessage message) implements Command<ProcessMessageResponse> {
}