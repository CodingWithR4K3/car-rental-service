package com.kodilla.carrentalservice.repository;

import com.kodilla.carrentalservice.domain.Car;
import com.kodilla.carrentalservice.domain.Status;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface CarRepository extends CrudRepository<Car, Long> {

    @Override
    List<Car> findAll();

    List<Car> findAllByBrand(String brand);

    List<Car> findAllByChassisType(String chassisType);

    List<Car> findAllByProductionYear(int productionYear);

    List<Car> findAllByFuelType(String fuelType);

    List<Car> findAllByMileage(int mileage);

    List<Car> findAllByCostPerDay(BigDecimal cost);

    long countAllByStatus(Status status);

    Optional<Car> findByVin(String vin);

}
