package ru.liga.optimalpacking.packages.shared;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.liga.optimalpacking.packages.shared.entities.Billing;
import ru.liga.optimalpacking.packages.shared.entities.BillingPK;

import java.time.LocalDate;
import java.util.List;

public interface BillingRepository extends JpaRepository<Billing, BillingPK> {
    List<Billing> findByUserIdAndDateBetweenOrderByDateDesc(String userId, LocalDate fromDate, LocalDate toDate);
}