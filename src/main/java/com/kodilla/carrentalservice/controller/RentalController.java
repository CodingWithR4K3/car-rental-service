package com.kodilla.carrentalservice.controller;

import com.kodilla.carrentalservice.dto.RentalDto;
import com.kodilla.carrentalservice.dto.RentalWithCarDto;
import com.kodilla.carrentalservice.exception.CarNotFoundException;
import com.kodilla.carrentalservice.exception.RentalNotFoundException;
import com.kodilla.carrentalservice.exception.UserNotFoundException;
import com.kodilla.carrentalservice.facade.RentalFacade;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/rentals")
public class RentalController {

    private final RentalFacade rentalFacade;

    public RentalController(RentalFacade rentalFacade) {
        this.rentalFacade = rentalFacade;
    }

    @GetMapping
    public List<RentalWithCarDto> getAllRentals() {
        return rentalFacade.getRentals();
    }

    @GetMapping("/{id}")
    public RentalWithCarDto getRentalById(@PathVariable Long id) throws RentalNotFoundException {
        return rentalFacade.getRentalById(id);
    }

    @PostMapping
    public RentalWithCarDto createRental(@RequestBody RentalDto rentalDto) throws CarNotFoundException, UserNotFoundException, RentalNotFoundException {
        return rentalFacade.createRental(rentalDto);
    }

    @PutMapping
    public RentalWithCarDto updateRental(@RequestBody RentalDto rentalDto) throws CarNotFoundException, UserNotFoundException, RentalNotFoundException {
        return rentalFacade.updateRental(rentalDto);
    }


    @DeleteMapping("/{id}")
    public void closeRental(@PathVariable Long id) throws RentalNotFoundException {
        rentalFacade.closeRental(id);
    }
}
