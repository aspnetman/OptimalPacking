package ru.liga.optimalpacking.packages.editparcel;

import org.mapstruct.Mapper;
import ru.liga.optimalpacking.packages.shared.entities.Parcel;

@Mapper
public interface EditParcelMapper {
    Parcel toEntity(ru.liga.optimalpacking.packages.editparcel.dto.Parcel parcel);
}
