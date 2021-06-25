package com.kodilla.carrentalservice.facade;

import com.kodilla.carrentalservice.domain.Rental;
import com.kodilla.carrentalservice.dto.RentalDto;
import com.kodilla.carrentalservice.dto.RentalWithCarDto;
import com.kodilla.carrentalservice.exception.CarNotFoundException;
import com.kodilla.carrentalservice.exception.RentalNotFoundException;
import com.kodilla.carrentalservice.exception.UserNotFoundException;
import com.kodilla.carrentalservice.mapper.RentalMapper;
import com.kodilla.carrentalservice.repository.RentalRepository;
import com.kodilla.carrentalservice.service.RentalService;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class RentalFacade {

    private final RentalService rentalService;
    private final RentalMapper rentalMapper;
    private final RentalRepository rentalRepository;

    public RentalFacade(RentalService rentalService, RentalMapper rentalMapper, RentalRepository rentalRepository) {
        this.rentalService = rentalService;
        this.rentalMapper = rentalMapper;
        this.rentalRepository = rentalRepository;
    }

    public List<RentalWithCarDto> getRentals() {
        return rentalMapper.mapToRentalWithCarDtoList(rentalService.getRentals());
    }

    public RentalWithCarDto getRentalById(final Long id) throws RentalNotFoundException {
        return rentalMapper.mapToRentalWithCarDto(rentalService.getRentalById(id));
    }

    public RentalWithCarDto createRental(final RentalDto rentalDto) throws UserNotFoundException, CarNotFoundException, RentalNotFoundException {
        return rentalMapper.mapToRentalWithCarDto(rentalService.createRental(rentalDto));
    }

    public RentalWithCarDto updateRental(RentalDto rentalDto) throws UserNotFoundException, CarNotFoundException, RentalNotFoundException {
        return rentalMapper.mapToRentalWithCarDto(rentalService.updateRental(rentalDto));
    }

    public void closeRental(Long id) throws RentalNotFoundException {
        Rental rental = rentalRepository.findById(id).orElseThrow(RentalNotFoundException::new);

        rental.setRentedTo(LocalDate.now());
        rentalService.updateCost(rental);
        rentalService.updateDuration(rental);
        rentalService.closeRental(id);
    }
}

