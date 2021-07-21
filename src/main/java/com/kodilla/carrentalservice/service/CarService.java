package com.kodilla.carrentalservice.service;

import com.kodilla.carrentalservice.domain.Car;
import com.kodilla.carrentalservice.exception.CarNotFoundException;
import com.kodilla.carrentalservice.mapper.CarMapper;
import com.kodilla.carrentalservice.repository.CarRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

@Transactional
@Service
public class CarService {

    private final EntityManager entityManager;
    private final CarRepository carRepository;
    private final CarMapper carMapper;


    public CarService(CarRepository carRepository, CarMapper carMapper, EntityManager entityManager) {
        this.carRepository = carRepository;
        this.carMapper = carMapper;
        this.entityManager = entityManager;
    }

    public List<Car> getCars() {
        return carRepository.findAll();
    }

    public List<Car> getCarsByBrand(final String brand) {
        return carRepository.findAllByBrand(brand);
    }

    public List<Car> getCarsByProductionYear(final int year) {
        return carRepository.findAllByProductionYear(year);
    }

    public List<Car> getCarsByChassisType(final String chassisType) {
        return carRepository.findAllByChassisType(chassisType);
    }

    public List<Car> getCarsByFuelType(final String fuelType) {
        return carRepository.findAllByFuelType(fuelType);
    }

    public List<Car> getCarsByMileage(final int mileage) {
        return carRepository.findAllByMileage(mileage);
    }

    public List<Car> getCarsByCostPerDay(final BigDecimal cost) {
        return carRepository.findAllByCostPerDay(cost);
    }

    public Car getCarById(final Long id) throws CarNotFoundException {
        return carRepository.findById(id).orElseThrow(CarNotFoundException::new);
    }

    public Car getCarByVin(final String vin) throws CarNotFoundException {
        return carRepository.findByVin(vin).orElseThrow(CarNotFoundException::new);
    }


    public Car saveCar(final Car car) {
        return carRepository.save(car);
    }

    public void deleteCar(final Long id) {
        carRepository.deleteById(id);
    }

}
