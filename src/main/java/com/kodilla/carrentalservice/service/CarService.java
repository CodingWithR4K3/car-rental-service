package com.kodilla.carrentalservice.service;

import com.kodilla.carrentalservice.domain.Car;
import com.kodilla.carrentalservice.dto.CarDto;
import com.kodilla.carrentalservice.exception.CarNotFoundException;
import com.kodilla.carrentalservice.mapper.CarMapper;
import com.kodilla.carrentalservice.repository.CarRepository;
import org.hibernate.Filter;
import org.hibernate.Session;
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

    public List<CarDto> getCars() {
        return carMapper.mapToCarDtoList(carRepository.findAll());
    }

    public List<CarDto> getCarsByBrand(final String brand) {
        return carMapper.mapToCarDtoList(carRepository.findAllByBrand(brand));
    }

    public List<CarDto> getCarsByProductionYear(final int year) {
        return carMapper.mapToCarDtoList(carRepository.findAllByProductionYear(year));
    }

    public List<CarDto> getCarsByChassisType(final String chassisType) {
        return carMapper.mapToCarDtoList(carRepository.findAllByChassisType(chassisType));
    }

    public List<CarDto> getCarsByFuelType(final String fuelType) {
        return carMapper.mapToCarDtoList(carRepository.findAllByFuelType(fuelType));
    }

    public List<CarDto> getCarsByMileage(final int mileage) {
        return carMapper.mapToCarDtoList(carRepository.findAllByMileage(mileage));
    }

    public List<CarDto> getCarsByCostPerDay(final BigDecimal cost) {
        return carMapper.mapToCarDtoList(carRepository.findAllByCostPerDay(cost));
    }

    public CarDto getCarById(final Long id) throws CarNotFoundException {
        return carMapper.mapToCarDto(carRepository.findById(id).orElseThrow(CarNotFoundException::new));
    }

    public CarDto getCarByVin(final String vin) throws CarNotFoundException {
        return carMapper.mapToCarDto(carRepository.findByVin(vin).orElseThrow(CarNotFoundException::new));
    }


    public CarDto saveCar(final CarDto carDto) {
        return carMapper.mapToCarDto(carRepository.save(carMapper.mapToCar(carDto)));
    }

    public void deleteCar(final Long id) {
        carRepository.deleteById(id);
    }

    public Iterable<Car> findAll(boolean isDeleted) {
        Session session = entityManager.unwrap(Session.class);
        Filter filter = session.enableFilter("deletedCarFilter");
        filter.setParameter("isDeleted", isDeleted);
        Iterable<Car> cars = carRepository.findAll();
        session.disableFilter("deletedCarFilter");
        return cars;

    }

}
