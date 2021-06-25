package com.kodilla.carrentalservice.controller;

import com.kodilla.carrentalservice.domain.Car;
import com.kodilla.carrentalservice.dto.CarDto;
import com.kodilla.carrentalservice.exception.CarNotFoundException;
import com.kodilla.carrentalservice.facade.CarFacade;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/cars")
public class CarController {

    private final CarFacade carFacade;

    public CarController(CarFacade carFacade) {
        this.carFacade = carFacade;
    }

    @GetMapping
    public Iterable<Car> getAllCars(@RequestParam(value = "isDeleted", required = false, defaultValue = "false") boolean isDeleted) {
        return carFacade.findAll(isDeleted);
    }

    @GetMapping("/{id}")
    public CarDto getCarById(@PathVariable Long id) throws CarNotFoundException {
        return carFacade.getCarById(id);
    }

    @GetMapping("/by_vin/{vin}")
    public CarDto getCarByVin(@PathVariable String vin) throws CarNotFoundException {
        return carFacade.getCarByVin(vin);
    }

    @GetMapping("/by_brand/{brand}")
    public List<CarDto> getCarByBrand(@PathVariable String brand) {
        return carFacade.getCarsByBrand(brand);
    }

    @GetMapping("/by_year/{year}")
    public List<CarDto> getCarsByProductionYear(@PathVariable int year) {
        return carFacade.getCarsByProductionYear(year);
    }

    @GetMapping("/by_chassis/{chassis}")
    public List<CarDto> getCarsByChassisType(@PathVariable String chassis) {
        return carFacade.getCarsByChassisType(chassis);
    }

    @GetMapping("/by_fuel/{fuelType}")
    public List<CarDto> getCarsByFuelType(@PathVariable String fuelType) {
        return carFacade.getCarsByFuelType(fuelType);
    }

    @GetMapping("/by_mileage/{mileage}")
    public List<CarDto> getCarsByMileage(@PathVariable int mileage) {
        return carFacade.getCarsByMileage(mileage);
    }

    @GetMapping("/by_cost/{cost}")
    public List<CarDto> getCarsByCostPerDay(@PathVariable BigDecimal cost) {
        return carFacade.getCarsByCostPerDay(cost);
    }

    @PostMapping
    public CarDto createCar(@RequestBody CarDto carDto) {
        return carFacade.saveCar(carDto);
    }

    @PutMapping
    public CarDto updateCar(@RequestBody CarDto carDto) {
        return carFacade.saveCar(carDto);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteCar(@PathVariable Long id) {
        carFacade.deleteCar(id);
    }
}
