package com.kodilla.carrentalservice.service;

import com.kodilla.carrentalservice.domain.Car;
import com.kodilla.carrentalservice.domain.Rental;
import com.kodilla.carrentalservice.domain.User;
import com.kodilla.carrentalservice.dto.RentalDto;
import com.kodilla.carrentalservice.exception.CarNotFoundException;
import com.kodilla.carrentalservice.exception.RentalNotFoundException;
import com.kodilla.carrentalservice.exception.UserNotFoundException;
import com.kodilla.carrentalservice.repository.CarRepository;
import com.kodilla.carrentalservice.repository.RentalRepository;
import com.kodilla.carrentalservice.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class RentalServiceTestSuite {

    @InjectMocks
    private RentalService rentalService;

    @Mock
    private RentalRepository rentalRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private CarRepository carRepository;

    @Test
    public void createRentalTest() throws CarNotFoundException, UserNotFoundException {
        //Given
        User user = initUser();
        Car car = initCar();
        Rental rental = initRental();
        RentalDto rentalDto = initRentalDto();

        when(userRepository.findById(any())).thenReturn(Optional.of(user));
        when(carRepository.findById(any())).thenReturn(Optional.of(car));
        when(rentalRepository.save(any())).thenReturn(rental);

        //When
        Rental createdRental = rentalService.createRental(rentalDto);

        //Then
        assertEquals(createdRental.getCar().getBrand(), car.getBrand());
        assertEquals(createdRental.getUser().getName(), user.getName());
        assertEquals(createdRental.getRentedFrom(), LocalDate.of(2020, 10, 10));
        assertEquals(createdRental.getRentedTo(), LocalDate.of(2020, 10, 15));
    }

    @Test
    public void modifyRentalTest() throws UserNotFoundException, RentalNotFoundException, CarNotFoundException {
        //Given
        User user = initUser();
        Car car = initCar();
        Rental rental = initRental();
        RentalDto rentalDto = initRentalDto();

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(carRepository.findById(1L)).thenReturn(Optional.of(car));
        when(rentalRepository.findById(1L)).thenReturn(Optional.of(rental));

        //When
        Rental modifiedRental = rentalService.updateRental(rentalDto);

        //Then
        assertEquals(rentalDto.getUserId(), modifiedRental.getUser().getId());
        assertEquals(rentalDto.getCarId(), modifiedRental.getCar().getId());
        assertEquals(rentalDto.getRentedFrom(), modifiedRental.getRentedFrom());
        assertEquals(rentalDto.getRentedTo(), modifiedRental.getRentedTo());
    }


    @Test
    public void getRentalByIdTest() throws RentalNotFoundException {
        //Given
        Rental rental = initRental();

        when(rentalRepository.findById(1L)).thenReturn(Optional.of(rental));

        //When
        Rental result = rentalService.getRentalById(1L);

        //Then
        assertEquals(result.getId(), result.getId());
    }

    @Test
    public void getRentalsTest() {
        //Given
        Rental rental = initRental();
        List<Rental> rentalList = Collections.singletonList(rental);

        when(rentalRepository.findAll()).thenReturn(rentalList);

        //When
        List<Rental> resultList = rentalService.getRentals();

        //Then
        assertNotNull(resultList);
        assertEquals(1, resultList.size());
    }

    @Test
    public void closeRentalTest() throws RentalNotFoundException {
        //Given
        Rental rental = initRental();
        when(rentalRepository.findById(1L)).thenReturn(Optional.of(rental));

        //When
        rentalService.closeRental(1L);

        //Then
        verify(rentalRepository, times(1)).deleteById(1L);
    }

    @Test
    public void updateDurationTest() {
        //Given
        Rental rental = initRental();
        rental.setRentedTo(LocalDate.of(2020, 10, 28));

        //When
        rentalService.updateDuration(rental);

        //Then
        assertEquals(18L, (long) rental.getDuration());
    }

    @Test
    public void updateCostTest() {
        //Given
        Rental rental = initRental();
        rental.setDuration(10L);

        //When
        rentalService.updateCost(rental);

        //Then
        assertEquals(new BigDecimal(180), rental.getCost());
    }

    private Car initCar() {
        return new Car(
                1L,
                "sampleVin",
                2015,
                "Audi",
                "A3",
                110000,
                "Saloon",
                "Diesel",
                3.0,
                new BigDecimal(18));
    }

    private User initUser() {
        return new User(
                1L,
                "Jack",
                "Smith",
                "email",
                "password",
                123456);
    }

    private Rental initRental() {
        User user = initUser();
        Car car = initCar();

        return new Rental(
                LocalDate.of(2020, 10, 10),
                LocalDate.of(2020, 10, 15),
                user,
                car);
    }

    private RentalDto initRentalDto() {
        return new RentalDto(
                1L,
                LocalDate.of(2020, 10, 10),
                LocalDate.of(2020, 10, 15),
                1L,
                1L);
    }

}


