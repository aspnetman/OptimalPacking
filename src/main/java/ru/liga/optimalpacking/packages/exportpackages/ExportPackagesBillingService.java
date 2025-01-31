package ru.liga.optimalpacking.packages.exportpackages;

import lombok.RequiredArgsConstructor;
import ru.liga.optimalpacking.config.BillingConfig;
import ru.liga.optimalpacking.packages.exportpackages.entities.Truck;
import ru.liga.optimalpacking.shared.BillingRepository;
import ru.liga.optimalpacking.shared.entities.Billing;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RequiredArgsConstructor
public class ExportPackagesBillingService {

    private final BillingConfig billingConfig;

    private final BillingRepository billingRepository;

    public void addBillingForExportedPackages(String userId, List<Truck> trucks) {

        String MESSAGE = "Разгрузка;Всего машин;%d посылок;%.2f рублей";
        var segments = trucks.stream().mapToInt(Truck::occupiedSegmentsCount).sum();
        var cost = billingConfig.getUnloadingCostPerSegment().multiply(BigDecimal.valueOf(segments));

        billingRepository.save(new Billing(
                userId,
                MESSAGE.formatted(
                        LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")),
                        trucks.size(),
                        trucks.stream().mapToInt(Truck::getParcelsCount).sum(),
                        cost),
                "разгрузка",
                LocalDate.now(),
                segments,
                cost));
    }
}
