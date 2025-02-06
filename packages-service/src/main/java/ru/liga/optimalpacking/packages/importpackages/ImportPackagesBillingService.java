package ru.liga.optimalpacking.packages.importpackages;

import com.google.gson.GsonBuilder;
import lombok.RequiredArgsConstructor;
import ru.liga.optimalpacking.packages.importpackages.entities.Truck;
import ru.liga.optimalpacking.shared.OutboxMessageRepository;
import ru.liga.optimalpacking.packages.shared.entities.Billing;
import ru.liga.optimalpacking.shared.entities.OutboxMessage;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
public class ImportPackagesBillingService {

    private final OutboxMessageRepository outboxMessageRepository;

    public void addBillingForImportedPackages(String userId, List<Truck> trucks) {

        String billingTopic = "import-packages-billing-topic-out-0";

        var billing = new Billing(
                userId,
                "погрузка",
                LocalDate.now().toString(),
                trucks.stream().mapToInt(Truck::getOccupiedSegmentsCount).sum());

        var gson = new GsonBuilder().setPrettyPrinting().create();
        var outboxMessage = new OutboxMessage();
        var message = gson.toJson(billing);

        outboxMessage.setTopic(billingTopic);
        outboxMessage.setMessage(message);

        outboxMessageRepository.save(outboxMessage);
    }
}
