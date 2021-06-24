package com.kodilla.carrentalservice.controller;

import com.kodilla.carrentalservice.domain.Rental;
import com.kodilla.carrentalservice.dto.RentalDto;
import com.kodilla.carrentalservice.exception.CarNotFoundException;
import com.kodilla.carrentalservice.exception.RentalNotFoundException;
import com.kodilla.carrentalservice.exception.UserNotFoundException;
import com.kodilla.carrentalservice.service.RentalService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/rentals")
public class RentalController {

    private final RentalService rentalService;

    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    @GetMapping
    public List<RentalDto> getAllRentals() {
        return rentalService.getRentals();
    }

    @GetMapping("/{id}")
    public RentalDto getRentalById(@PathVariable Long id) throws RentalNotFoundException {
        return rentalService.getRentalById(id);
    }

    @PostMapping
    public RentalDto createRental(@RequestBody RentalDto rentalDto) throws CarNotFoundException, UserNotFoundException {
        return rentalService.createRental(rentalDto);
    }

    @PutMapping
    public Rental updateRental(@RequestBody RentalDto rentalDto) throws CarNotFoundException, UserNotFoundException, RentalNotFoundException {
        return rentalService.updateRental(rentalDto);
    }


    @DeleteMapping("/{id}")
    public void closeRental(@PathVariable Long id) throws RentalNotFoundException {
        rentalService.closeRental(id);
    }
}
