package com.kodilla.carrentalservice.controller;

import com.kodilla.carrentalservice.dto.CarDto;
import com.kodilla.carrentalservice.exception.CarNotFoundException;
import com.kodilla.carrentalservice.service.CarService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/cars")
public class CarController {

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping
    public List<CarDto> getAllCars() {
        return carService.getCars();
    }

    @GetMapping(value = "/{id}")
    public CarDto getCarById(@PathVariable Long id) throws CarNotFoundException {
        return carService.getCarById(id);
    }

    @GetMapping(value = "by_vim{vin}")
    public CarDto getCarByVin(@PathVariable String vin) throws CarNotFoundException {
        return carService.getCarByVin(vin);
    }

    @GetMapping(value = "/by_brand{brand}")
    public List<CarDto> getCarByBrand(@PathVariable String brand) {
        return carService.getCarsByBrand(brand);
    }

    @GetMapping(value = "by_year{year}")
    public List<CarDto> getCarsByProductionYear(@PathVariable int year) {
        return carService.getCarsByProductionYear(year);
    }

    @GetMapping(value = "by_chassis{chassis}")
    public List<CarDto> getCarsByChassisType(@PathVariable String chassis) {
        return carService.getCarsByChassisType(chassis);
    }

    @GetMapping(value = "by_fuel{fuelType}")
    public List<CarDto> getCarsByFuelType(@PathVariable String fuelType) {
        return carService.getCarsByFuelType(fuelType);
    }

    @GetMapping(value = "by_mileage{mileage}")
    public List<CarDto> getCarsByMileage(@PathVariable int mileage) {
        return carService.getCarsByMileage(mileage);
    }

    @GetMapping(value = "by_cost{cost}")
    public List<CarDto> getCarsByCostPerDay(@PathVariable double cost) {
        return carService.getCarsByCostPerDay(cost);
    }

    @PostMapping
    public void createCar(@RequestBody CarDto carDto) {
        carService.saveCar(carDto);
    }

    @PutMapping
    public void updateCar(@RequestBody CarDto carDto) {
        carService.saveCar(carDto);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteCar(@PathVariable Long id) {
        carService.deleteCar(id);
    }
}
