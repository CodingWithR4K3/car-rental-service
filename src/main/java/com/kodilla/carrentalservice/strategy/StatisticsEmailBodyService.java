package com.kodilla.carrentalservice.strategy;

import com.kodilla.carrentalservice.domain.Status;
import com.kodilla.carrentalservice.repository.CarRepository;
import com.kodilla.carrentalservice.repository.RentalRepository;
import com.kodilla.carrentalservice.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class StatisticsEmailBodyService implements EmailBodyService {

    private final CarRepository carRepository;
    private final UserRepository userRepository;
    private final RentalRepository rentalRepository;

    public StatisticsEmailBodyService(CarRepository carRepository, UserRepository userRepository,
                                      RentalRepository rentalRepository) {
        this.carRepository = carRepository;
        this.userRepository = userRepository;
        this.rentalRepository = rentalRepository;
    }

    @Override
    public String emailBodyCreate() {
        long userRepositorySize = userRepository.count();
        long carRentedSize = carRepository.countAllByStatus(Status.RENTED);
        long carAvailableSize = carRepository.countAllByStatus(Status.AVAILABLE);
        long rentalRepositorySize = rentalRepository.count();

        return ("\n Dear Administrator." +
                "\n\t Below there are daily statistics considering your page: \n" +
                "\n\t Current number of registered users: " + userRepositorySize +
                "\n\t Current number of rented cars: " + carRentedSize +
                "\n\t Current number of available cars: " + carAvailableSize +
                "\n\t Current number of all rentals: " + rentalRepositorySize + "\n" +
                "\n Have a nice day!" +
                "\n //Car Rental service//");
    }
}
