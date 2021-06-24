package com.kodilla.carrentalservice.service;

import com.kodilla.carrentalservice.domain.Car;
import com.kodilla.carrentalservice.domain.Rental;
import com.kodilla.carrentalservice.domain.Status;
import com.kodilla.carrentalservice.domain.User;
import com.kodilla.carrentalservice.dto.RentalDto;
import com.kodilla.carrentalservice.exception.CarNotFoundException;
import com.kodilla.carrentalservice.exception.RentalNotFoundException;
import com.kodilla.carrentalservice.exception.UserNotFoundException;
import com.kodilla.carrentalservice.mapper.RentalMapper;
import com.kodilla.carrentalservice.repository.CarRepository;
import com.kodilla.carrentalservice.repository.RentalRepository;
import com.kodilla.carrentalservice.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class RentalService {

    private final RentalMapper rentalMapper;
    private final RentalRepository rentalRepository;
    private final UserRepository userRepository;
    private final CarRepository carRepository;


    public RentalService(RentalMapper rentalMapper, RentalRepository rentalRepository, UserRepository userRepository, CarRepository carRepository) {
        this.rentalMapper = rentalMapper;
        this.rentalRepository = rentalRepository;
        this.userRepository = userRepository;
        this.carRepository = carRepository;
    }

    public List<RentalDto> getRentals() {
        return rentalMapper.mapToRentalDtoList(rentalRepository.findAll());
    }

    public RentalDto getRentalById(final Long id) throws RentalNotFoundException {
        return rentalMapper.mapToRentalDto(rentalRepository.findById(id).orElseThrow(RentalNotFoundException::new));
    }

    public RentalDto createRental(final RentalDto rentalDto) throws UserNotFoundException, CarNotFoundException {
        User user = userRepository.findById(rentalDto.getUserId()).orElseThrow(UserNotFoundException::new);
        Car car = carRepository.findById(rentalDto.getCarId()).orElseThrow(CarNotFoundException::new);
        car.setStatus(Status.RENTED);
        carRepository.save(car);

        Rental rental = new Rental(rentalDto.getRentedFrom(), rentalDto.getRentedTo(), user, car);
        return rentalMapper.mapToRentalDto(rentalRepository.save(rental));
    }

    public void deleteRental(final Long id) throws RentalNotFoundException {
        Rental rental = rentalRepository.findById(id).orElseThrow(RentalNotFoundException::new);
    }
}
