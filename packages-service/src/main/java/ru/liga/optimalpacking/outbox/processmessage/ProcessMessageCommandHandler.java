package ru.liga.optimalpacking.outbox.processmessage;

import an.awesome.pipelinr.Command;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.MimeTypeUtils;
import ru.liga.optimalpacking.outbox.processmessage.dto.ProcessMessageResponse;
import ru.liga.optimalpacking.shared.OutboxMessageRepository;
import ru.liga.optimalpacking.shared.entities.OutboxMessage;

import java.nio.charset.StandardCharsets;

@Component
@RequiredArgsConstructor
public class ProcessMessageCommandHandler implements Command.Handler<ProcessMessageCommand, ProcessMessageResponse> {

    private final StreamBridge streamBridge;

    private final OutboxMessageRepository outboxMessageRepository;

    @Override
    @Transactional
    public ProcessMessageResponse handle(ProcessMessageCommand processMessageCommand) {

        outboxMessageRepository.deleteById(processMessageCommand.message().getId());

        streamBridge.send(
                processMessageCommand.message().getTopic(),
                buildKafkaMessage(processMessageCommand.message())
        );

        return new ProcessMessageResponse();
    }

    private Message<String> buildKafkaMessage(OutboxMessage message) {
        return MessageBuilder
                .withPayload(message.getMessage())
                .setHeader(KafkaHeaders.KEY, message.getId().toString().getBytes(StandardCharsets.UTF_8))
                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
                .build();
    }
}
