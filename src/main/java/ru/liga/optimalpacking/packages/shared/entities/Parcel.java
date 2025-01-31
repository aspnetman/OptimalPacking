package ru.liga.optimalpacking.packages.shared.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "parcels", schema = "public")
public class Parcel {

    @Id
    @Column(name = "name")
    private String name;

    @Column(name = "form")
    private String form;

    @Column(name = "symbol")
    private char symbol;

    @Column(name = "width")
    private int width;

    @Column(name = "height")
    private int height;
}