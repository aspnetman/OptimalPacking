package ru.liga.optimalpacking.infrastructure.schedule;

import an.awesome.pipelinr.Pipeline;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.liga.optimalpacking.outbox.processmessage.ProcessMessageCommand;
import ru.liga.optimalpacking.shared.OutboxMessageRepository;

@Component
public class ProcessOutboxMessagesJob {

    private final Pipeline pipeline;

    private final OutboxMessageRepository outboxMessageRepository;

    private final int batchSize;

    public ProcessOutboxMessagesJob(
            Pipeline pipeline,
            OutboxMessageRepository outboxMessageRepository,
            @Value("${schedule.process-outbox-messages.batch-size}")
            int batchSize) {
        this.pipeline = pipeline;
        this.outboxMessageRepository = outboxMessageRepository;
        this.batchSize = batchSize;
    }

    @Scheduled(fixedRateString = "${schedule.process-outbox-messages.interval}")
    @SchedulerLock(name = "process-outbox-messages")
    public void processOutboxMessages() {

        var messages = outboxMessageRepository.findByOrderById(PageRequest.of(0, batchSize));

        for (var message : messages) {
            pipeline.send(new ProcessMessageCommand(message));
        }
    }
}