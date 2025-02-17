package ru.liga.optimalpacking.billings.addbilling;

import an.awesome.pipelinr.Command;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.liga.optimalpacking.billings.addbilling.dto.AddBillingResponse;
import ru.liga.optimalpacking.billings.addbilling.dto.BillingDto;
import ru.liga.optimalpacking.billings.addbilling.entities.InboxMessage;
import ru.liga.optimalpacking.billings.shared.BillingRepository;
import ru.liga.optimalpacking.config.BillingConfig;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.LocalDateTime;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class AddBillingCommandHandler implements Command.Handler<AddBillingCommand, AddBillingResponse> {

    private final BillingRepository billingRepository;

    private final AddBillingMapper addBillingMapper;

    private final InboxRepository inboxRepository;

    private final Clock clock;

    private final BillingConfig billingConfig;

    @Transactional
    @CacheEvict(value = "billings", key = "#addBillingCommand.billingDto().userId + '-range'")
    @Override
    public AddBillingResponse handle(AddBillingCommand addBillingCommand) {

        if (inboxRepository.findById(addBillingCommand.billingDto().getMessageId()).isPresent()) {
            return new AddBillingResponse();
        }

        addBilling(addBillingCommand.billingDto());
        addInboxMessage(addBillingCommand.billingDto().getMessageId());

        return new AddBillingResponse();
    }

    private void addBilling(BillingDto billingDto) {
        billingRepository.save(addBillingMapper.toEntity(billingDto, calculateCost(billingDto)));
    }

    private void addInboxMessage(UUID messageId) {
        var message = new InboxMessage();
        message.setId(messageId);
        message.setCreatedAt(LocalDateTime.now(clock));
        inboxRepository.save(message);
    }

    private BigDecimal calculateCost(BillingDto billingDto) {
        var costPerSegment = billingDto.getType().equals("погрузка")
                ? billingConfig.getLoadingCostPerSegment()
                : billingConfig.getUnloadingCostPerSegment();
        return costPerSegment.multiply(BigDecimal.valueOf(billingDto.getQuantity()));
    }
}
