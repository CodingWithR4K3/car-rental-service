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

import java.math.BigDecimal;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;

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

    public List<Rental> getRentals() {
        return rentalRepository.findAll();
    }

    public Rental getRentalById(final Long id) throws RentalNotFoundException {
        return rentalRepository.findById(id).orElseThrow(RentalNotFoundException::new);
    }

    public Rental createRental(final RentalDto rentalDto) throws UserNotFoundException, CarNotFoundException {
        User user = userRepository.findById(rentalDto.getUserId()).orElseThrow(UserNotFoundException::new);
        Car car = carRepository.findById(rentalDto.getCarId()).orElseThrow(CarNotFoundException::new);
        car.setStatus(Status.RENTED);
        carRepository.save(car);

        Rental rental = new Rental(rentalDto.getRentedFrom(), rentalDto.getRentedTo(), user, car);
        return rentalRepository.save(rental);
    }

    public Rental updateRental(RentalDto rentalDto) throws UserNotFoundException, CarNotFoundException, RentalNotFoundException {
        User user = userRepository.findById(rentalDto.getUserId()).orElseThrow(UserNotFoundException::new);
        Car car = carRepository.findById(rentalDto.getCarId()).orElseThrow(CarNotFoundException::new);
        Rental rental = rentalRepository.findById(rentalDto.getId()).orElseThrow(RentalNotFoundException::new);

        rental.setUser(user);
        rental.setCar(car);
        rental.setRentedFrom(rentalDto.getRentedFrom());
        rental.setRentedTo(rentalDto.getRentedTo());
        updateDuration(rental);
        updateCost(rental);

        return rental;
    }

    public void closeRental(Long id) throws RentalNotFoundException {
        Rental rental = rentalRepository.findById(id).orElseThrow(RentalNotFoundException::new);

        rental.getUser().getRentals().remove(rental);
        rental.getCar().getRentals().remove(rental);
        rental.getCar().setStatus(Status.AVAILABLE);

        rentalRepository.deleteById(id);
    }

    public void updateDuration(Rental rental) {
        if (rental.getRentedTo().isAfter(rental.getRentedFrom())) {
            rental.setDuration(DAYS.between(rental.getRentedFrom(), rental.getRentedTo()));
        } else {
            rental.setDuration(0L);
        }
    }

    public void updateCost(Rental rental) {
        BigDecimal updatedCost = rental.getCar().getCostPerDay().multiply(new BigDecimal(rental.getDuration()));
        rental.setCost(updatedCost);
    }
}
