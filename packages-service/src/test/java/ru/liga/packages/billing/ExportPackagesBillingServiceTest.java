package ru.liga.packages.billing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.liga.optimalpacking.config.BillingConfig;
import ru.liga.optimalpacking.packages.exportpackages.ExportPackagesBillingService;
import ru.liga.optimalpacking.packages.exportpackages.entities.Truck;
import ru.liga.optimalpacking.packages.shared.entities.Parcel;
import ru.liga.optimalpacking.packages.shared.BillingRepository;
import ru.liga.optimalpacking.packages.shared.entities.Billing;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ExportPackagesBillingServiceTest {

    private final BigDecimal loadingCostPerSegment = BigDecimal.valueOf(1000);
    private final BigDecimal unloadingCostPerSegment = BigDecimal.valueOf(2000);

    @Mock
    private BillingConfig billingConfig;

    @Mock
    private BillingRepository exportPackagesBillingRepository;

    @InjectMocks
    private ExportPackagesBillingService serviceUnderTest;

    private List<Truck> trucks;

    @BeforeEach
    void setUp() {

        when(billingConfig.getLoadingCostPerSegment()).thenReturn(loadingCostPerSegment);
        when(billingConfig.getUnloadingCostPerSegment()).thenReturn(unloadingCostPerSegment);
        trucks = new ArrayList<>();

        trucks.add(new Truck(UUID.randomUUID(), List.of(new Parcel("", null, 'X', 1, 1)), 3));
        trucks.add(new Truck(UUID.randomUUID(), List.of(new Parcel("", null, 'X', 1, 1), new Parcel("", null, 'X', 1, 1)), 2));
    }

    @Test
    void testAddBillingForExportedPackages() {
        serviceUnderTest.addBillingForExportedPackages("user1", trucks);

        Mockito.verify(exportPackagesBillingRepository).save(Mockito.any(Billing.class));
    }

    @Test
    void testCalculateTotalSegments() {
        serviceUnderTest.addBillingForExportedPackages("user1", trucks);

        Mockito.verify(exportPackagesBillingRepository).save(Mockito.argThat(billing -> billing.getSegments() == 5));
    }

    @Test
    void testCalculateTotalCost() {
        serviceUnderTest.addBillingForExportedPackages("user1", trucks);

        Mockito.verify(exportPackagesBillingRepository).save(Mockito.argThat(billing -> billing.getCost().compareTo(BigDecimal.valueOf(5000)) == 0));
    }
}