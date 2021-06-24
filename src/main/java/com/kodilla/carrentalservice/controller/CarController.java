package com.kodilla.carrentalservice.controller;

import com.kodilla.carrentalservice.domain.Car;
import com.kodilla.carrentalservice.dto.CarDto;
import com.kodilla.carrentalservice.exception.CarNotFoundException;
import com.kodilla.carrentalservice.service.CarService;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
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
    public Iterable<Car> getAllCars(@RequestParam(value = "isDeleted", required = false, defaultValue = "false") boolean isDeleted) {
        return carService.findAll(isDeleted);
    }

    @GetMapping("/{id}")
    public CarDto getCarById(@PathVariable Long id) throws CarNotFoundException {
        return carService.getCarById(id);
    }

    @GetMapping("/by_vin/{vin}")
    public CarDto getCarByVin(@PathVariable String vin) throws CarNotFoundException {
        return carService.getCarByVin(vin);
    }

    @GetMapping("/by_brand/{brand}")
    public List<CarDto> getCarByBrand(@PathVariable String brand) {
        return carService.getCarsByBrand(brand);
    }

    @GetMapping("/by_year/{year}")
    public List<CarDto> getCarsByProductionYear(@PathVariable int year) {
        return carService.getCarsByProductionYear(year);
    }

    @GetMapping("/by_chassis/{chassis}")
    public List<CarDto> getCarsByChassisType(@PathVariable String chassis) {
        return carService.getCarsByChassisType(chassis);
    }

    @GetMapping("/by_fuel/{fuelType}")
    public List<CarDto> getCarsByFuelType(@PathVariable String fuelType) {
        return carService.getCarsByFuelType(fuelType);
    }

    @GetMapping("/by_mileage/{mileage}")
    public List<CarDto> getCarsByMileage(@PathVariable int mileage) {
        return carService.getCarsByMileage(mileage);
    }

    @GetMapping("/by_cost/{cost}")
    public List<CarDto> getCarsByCostPerDay(@PathVariable BigDecimal cost) {
        return carService.getCarsByCostPerDay(cost);
    }

    @PostMapping
    public CarDto createCar(@RequestBody CarDto carDto) {
        return carService.saveCar(carDto);
    }

    @PutMapping
    public CarDto updateCar(@RequestBody CarDto carDto) {
        return carService.saveCar(carDto);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteCar(@PathVariable Long id) {
        carService.deleteCar(id);
    }
}
