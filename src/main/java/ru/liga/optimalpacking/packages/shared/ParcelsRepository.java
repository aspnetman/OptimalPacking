package ru.liga.optimalpacking.packages.shared;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.liga.optimalpacking.packages.shared.entities.Parcel;

@Repository
public interface ParcelsRepository extends JpaRepository<Parcel, String> {

    Page<Parcel> findAll(Pageable pageable);
}