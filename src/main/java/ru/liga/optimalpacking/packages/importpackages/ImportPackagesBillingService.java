package ru.liga.optimalpacking.packages.importpackages;

import lombok.RequiredArgsConstructor;
import ru.liga.optimalpacking.config.BillingConfig;
import ru.liga.optimalpacking.packages.importpackages.entities.Truck;
import ru.liga.optimalpacking.shared.BillingRepository;
import ru.liga.optimalpacking.shared.entities.Billing;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RequiredArgsConstructor
public class ImportPackagesBillingService {

    private final BillingConfig billingConfig;

    private final BillingRepository billingRepository;

    public void addBillingForImportedPackages(String userId, List<Truck> trucks) {

        String MESSAGE = "Погрузка;Всего машин;%d посылок;%.2f рублей";
        var segments = trucks.stream().mapToInt(Truck::getOccupiedSegmentsCount).sum();
        var cost = billingConfig.getLoadingCostPerSegment().multiply(BigDecimal.valueOf(segments));

        billingRepository.save(new Billing(
                userId,
                MESSAGE.formatted(
                        LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")),
                        trucks.size(),
                        trucks.stream().mapToInt(Truck::getParcelsCount).sum(),
                        cost),
                "погрузка",
                LocalDate.now(),
                segments,
                cost));
    }
}
