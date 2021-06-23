package com.kodilla.carrentalservice.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "CARS")
public class Car {

    @Id
    @NotNull
    @GeneratedValue
    @Column(name = "ID", unique = true)
    private Long id;

    @NotNull
    @Column(name = "VIN")
    private String vin;

    @NotNull
    @Column(name = "PRODUCTION_YEAR")
    private int productionYear;

    @NotNull
    @Column(name = "BRAND")
    private String brand;

    @NotNull
    @Column(name = "MODEL")
    private String model;

    @NotNull
    @Column(name = "MILEAGE")
    private int mileage;

    @NotNull
    @Column(name = "CHASIS_TYPE")
    private String chassisType;

    @NotNull
    @Column(name = "FUEL_TYPE")
    private String fuelType;

    @NotNull
    @Column(name = "ENGINE_CAPACITY")
    private double engineCapacity;

    @NotNull
    @Column(name = "COST_PER_DAY")
    private double costPerDay;

    @NotNull
    @Column(name = "STATUS")
    private Status status;
}
