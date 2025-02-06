package ru.liga.optimalpacking.packages.exportpackages;

import com.google.gson.GsonBuilder;
import lombok.RequiredArgsConstructor;
import ru.liga.optimalpacking.packages.exportpackages.entities.Truck;
import ru.liga.optimalpacking.packages.shared.entities.Billing;
import ru.liga.optimalpacking.shared.OutboxMessageRepository;
import ru.liga.optimalpacking.shared.entities.OutboxMessage;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
public class ExportPackagesBillingService {

    private final OutboxMessageRepository outboxMessageRepository;

    public void addBillingForExportedPackages(String userId, List<Truck> trucks) {

        String billingTopic = "export-packages-billing-topic-out-0";

        var segments = trucks.stream().mapToInt(Truck::occupiedSegmentsCount).sum();

        var billing = new Billing(
                userId,
                "разгрузка",
                LocalDate.now().toString(),
                segments);

        var gson = new GsonBuilder().setPrettyPrinting().create();
        var outboxMessage = new OutboxMessage();
        outboxMessage.setTopic(billingTopic);
        outboxMessage.setMessage(gson.toJson(billing));

        outboxMessageRepository.save(outboxMessage);
    }
}
