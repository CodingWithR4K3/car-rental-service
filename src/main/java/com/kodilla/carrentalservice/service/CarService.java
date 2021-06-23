package com.kodilla.carrentalservice.service;

import com.kodilla.carrentalservice.domain.Car;
import com.kodilla.carrentalservice.dto.CarDto;
import com.kodilla.carrentalservice.exception.CarNotFoundException;
import com.kodilla.carrentalservice.mapper.CarMapper;
import com.kodilla.carrentalservice.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {

    private final CarRepository carRepository;
    private final CarMapper carMapper;

    @Autowired
    public CarService(CarRepository carRepository, CarMapper carMapper) {
        this.carRepository = carRepository;
        this.carMapper = carMapper;
    }

    public List<CarDto> getCars() {
        return carMapper.mapToCarDtoList(carRepository.findAll());
    }

    public List<CarDto> getCarsByBrand(String brand) {
        return carMapper.mapToCarDtoList(carRepository.findAllByBrand(brand));
    }

    public List<CarDto> getCarsByProductionYear(int year) {
        return carMapper.mapToCarDtoList(carRepository.findAllByProductionYear(year));
    }

    public List<CarDto> getCarsByChassisType(String chassisType) {
        return carMapper.mapToCarDtoList(carRepository.findAllByChassisType(chassisType));
    }

    public List<CarDto> getCarsByFuelType(String fuelType) {
        return carMapper.mapToCarDtoList(carRepository.findAllByFuelType(fuelType));
    }

    public List<CarDto> getCarsByMileage(int mileage) {
        return carMapper.mapToCarDtoList(carRepository.findAllByMileage(mileage));
    }

    public List<CarDto> getCarsByCostPerDay(double cost) {
        return carMapper.mapToCarDtoList(carRepository.findAllByCostPerDay(cost));
    }

    public CarDto getCarById(Long id) throws CarNotFoundException {
        return carMapper.mapToCarDto(carRepository.findById(id).orElseThrow(CarNotFoundException::new));
    }

    public CarDto getCarByVin(String vin) throws CarNotFoundException {
        return carMapper.mapToCarDto(carRepository.findByVin(vin).orElseThrow(CarNotFoundException::new));
    }


    public Car saveCar(final CarDto carDto) {
        return carRepository.save(carMapper.mapToCar(carDto));
    }

    public void deleteCar(Long id) {
        carRepository.deleteById(id);
    }

}
