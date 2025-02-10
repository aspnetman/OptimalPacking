package ru.liga.optimalpacking.shared;

import lombok.RequiredArgsConstructor;
import ru.liga.optimalpacking.packages.shared.entities.Billing;
import ru.liga.optimalpacking.shared.entities.OutboxMessage;
import ru.liga.optimalpacking.shared.util.JsonUtil;

import java.time.OffsetDateTime;
import java.util.UUID;

@RequiredArgsConstructor
public class BillingService {

    private final OutboxMessageRepository outboxMessageRepository;

    public void addBilling(String userId, int quantity, String type) {

        String billingTopic = "billing-topic-out-0";

        var billing = new Billing(
                UUID.randomUUID(),
                userId,
                type,
                OffsetDateTime.now(),
                quantity);

        var outboxMessage = new OutboxMessage();

        outboxMessage.setTopic(billingTopic);
        outboxMessage.setMessage(JsonUtil.toJson(billing));

        outboxMessageRepository.save(outboxMessage);
    }
}
