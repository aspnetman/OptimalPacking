package ru.liga.optimalpacking.packages.getparcel.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import ru.liga.optimalpacking.packages.shared.entities.Parcel;

@Getter
@Setter
@Data
public class GetParcelResponse {
    private final Parcel parcel;
}