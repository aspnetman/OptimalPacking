package ru.liga.optimalpacking.packages.shared;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.liga.optimalpacking.packages.shared.entities.Parcel;

public interface ParcelsRepository extends JpaRepository<Parcel, String> {
}