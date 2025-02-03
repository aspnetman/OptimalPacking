package ru.liga.optimalpacking.billings.shared;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.liga.optimalpacking.billings.shared.entities.Billing;
import ru.liga.optimalpacking.billings.shared.entities.BillingPK;

import java.time.LocalDate;
import java.util.List;

public interface BillingRepository extends JpaRepository<Billing, BillingPK> {
    List<Billing> findByUserIdAndDateBetweenOrderByDateDesc(String userId, LocalDate fromDate, LocalDate toDate);
}