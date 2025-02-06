package ru.liga.optimalpacking.infrastructure.schedule;

import an.awesome.pipelinr.Pipeline;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.scheduling.annotation.Scheduled;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.liga.optimalpacking.outbox.processmessage.ProcessMessageCommand;
import ru.liga.optimalpacking.shared.OutboxMessageRepository;

@Component
@RequiredArgsConstructor
public class ProcessOutboxMessagesJob {

    private final Pipeline pipeline;

    private final OutboxMessageRepository outboxMessageRepository;

    @Scheduled(fixedRateString = "${schedule.process-outbox-messages.interval}")
    @SchedulerLock(name = "process-outbox-messages")
    public void processOutboxMessages() {

        var messages = outboxMessageRepository.findAll();

        for (var message : messages) {
            pipeline.send(new ProcessMessageCommand(message));
        }
    }
}