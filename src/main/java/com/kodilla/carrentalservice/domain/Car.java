package com.kodilla.carrentalservice.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cars")
@SQLDelete(sql = "UPDATE cars SET deleted = true WHERE id=?")
@FilterDef(name = "deletedCarFilter", parameters = @ParamDef(name = "isDeleted", type = "boolean"))
@Filter(name = "deletedCarFilter", condition = "deleted = :isDeleted")
public class Car {

    @Id
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
    private BigDecimal costPerDay;

    @Enumerated
    @Column(name = "STATUS")
    private Status status;

    @OneToMany(targetEntity = Rental.class,
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            mappedBy = "car")
    private List<Rental> rentals = new ArrayList<>();

    private boolean deleted;

    public Car(Long id, String vin, int productionYear, String brand, String model, int mileage, String chassisType, String fuelType, double engineCapacity, BigDecimal costPerDay) {
        this.id = id;
        this.vin = vin;
        this.productionYear = productionYear;
        this.brand = brand;
        this.model = model;
        this.mileage = mileage;
        this.chassisType = chassisType;
        this.fuelType = fuelType;
        this.engineCapacity = engineCapacity;
        this.costPerDay = costPerDay;
        this.status = Status.AVAILABLE;
    }
}
