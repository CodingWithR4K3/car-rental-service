package com.kodilla.carrentalservice.dto;

import com.kodilla.carrentalservice.domain.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CarDto {
    private String vin;
    private int productionYear;
    private String brand;
    private String model;
    private int mileage;
    private String chassisType;
    private String fuelType;
    private double engineCapacity;
    private double costPerDay;
    private Status status;
}
