package com.kodilla.carrentalservice.facade;

import com.kodilla.carrentalservice.domain.Car;
import com.kodilla.carrentalservice.dto.CarDto;
import com.kodilla.carrentalservice.exception.CarNotFoundException;
import com.kodilla.carrentalservice.mapper.CarMapper;
import com.kodilla.carrentalservice.repository.CarRepository;
import com.kodilla.carrentalservice.service.CarService;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

@Component
public class CarFacade {

    private final CarService carService;
    private final CarMapper carMapper;
    private final EntityManager entityManager;
    private final CarRepository carRepository;


    public CarFacade(CarService carService, CarMapper carMapper, EntityManager entityManager, CarRepository carRepository) {
        this.carService = carService;
        this.carMapper = carMapper;
        this.entityManager = entityManager;
        this.carRepository = carRepository;
    }

    public List<CarDto> getCars() {
        return carMapper.mapToCarDtoList(carService.getCars());
    }

    public List<CarDto> getCarsByBrand(final String brand) {
        return carMapper.mapToCarDtoList(carService.getCarsByBrand(brand));
    }

    public List<CarDto> getCarsByProductionYear(final int year) {
        return carMapper.mapToCarDtoList(carService.getCarsByProductionYear(year));
    }

    public List<CarDto> getCarsByChassisType(final String chassisType) {
        return carMapper.mapToCarDtoList(carService.getCarsByChassisType(chassisType));
    }

    public List<CarDto> getCarsByFuelType(final String fuelType) {
        return carMapper.mapToCarDtoList(carService.getCarsByFuelType(fuelType));
    }

    public List<CarDto> getCarsByMileage(final int mileage) {
        return carMapper.mapToCarDtoList(carService.getCarsByMileage(mileage));
    }

    public List<CarDto> getCarsByCostPerDay(final BigDecimal cost) {
        return carMapper.mapToCarDtoList(carService.getCarsByCostPerDay(cost));
    }

    public CarDto getCarById(Long id) throws CarNotFoundException {
        return carMapper.mapToCarDto(carService.getCarById(id));
    }

    public CarDto getCarByVin(final String vin) throws CarNotFoundException {
        return carMapper.mapToCarDto(carService.getCarByVin(vin));
    }

    public Iterable<Car> findAll(boolean isDeleted) {
        Session session = entityManager.unwrap(Session.class);
        Filter filter = session.enableFilter("deletedCarFilter");
        filter.setParameter("isDeleted", isDeleted);
        Iterable<Car> cars = carRepository.findAll();
        session.disableFilter("deletedCarFilter");
        return cars;

    }

    public CarDto saveCar(CarDto carDto) {
        return carMapper.mapToCarDto(carService.saveCar(carMapper.mapToCar(carDto)));
    }

    public void deleteCar(final Long id) {
        carService.deleteCar(id);
    }

}
