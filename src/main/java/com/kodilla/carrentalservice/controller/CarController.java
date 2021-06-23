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

    @GetMapping(value = "/ByBrand{brand}")
    public List<CarDto> getCarByBrand(@PathVariable String brand) {
        return carService.getCarsByBrand(brand);
    }

    @PostMapping
    public void createCar(@RequestBody CarDto carDto) {
        carService.saveCar(carDto);
    }

    @PutMapping
    public void modifyCar(@RequestBody CarDto carDto) {
        carService.saveCar(carDto);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteCar(@PathVariable Long id) {
        carService.deleteCar(id);
    }
}
