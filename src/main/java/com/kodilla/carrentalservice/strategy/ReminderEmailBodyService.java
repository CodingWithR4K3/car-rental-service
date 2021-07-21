package com.kodilla.carrentalservice.strategy;

import com.kodilla.carrentalservice.domain.Rental;
import com.kodilla.carrentalservice.dto.RentalWithCarDto;
import com.kodilla.carrentalservice.mapper.RentalMapper;
import com.kodilla.carrentalservice.repository.RentalRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReminderEmailBodyService implements EmailBodyService {

    private final RentalRepository rentalRepository;
    private final RentalMapper rentalMapper;

    public ReminderEmailBodyService(RentalRepository rentalRepository, RentalMapper rentalMapper) {
        this.rentalRepository = rentalRepository;
        this.rentalMapper = rentalMapper;
    }

    @Override
    public String emailBodyCreate() {
        long rentalRepositorySize = rentalRepository.count();
        LocalDate today = LocalDate.now();

        List<Rental> closingDelayedRentalList = rentalRepository.findAllByRentedToBefore(today);
        List<Rental> closingSoonRentalList = rentalRepository.findAllByRentedToBetween(today, today.plusDays(3));

        List<RentalWithCarDto> closingDelayedRentalDtoList = rentalMapper.mapToRentalWithCarDtoList(closingDelayedRentalList);
        List<RentalWithCarDto> closingSoonRentalDtoList = rentalMapper.mapToRentalWithCarDtoList(closingSoonRentalList);

        return ("\n Dear Administrator." +
                "\n\t Below You can find daily reminder for currents rentals: " +
                "\n\n\t List of already delayed rentals: \n" +
                streamRentalListToString(closingDelayedRentalDtoList) +
                "\n\t List of soon closing rentals: \n" +
                streamRentalListToString(closingSoonRentalDtoList) +
                "\n\t Current number of all rentals: " + rentalRepositorySize + "\n" +
                "\n Have a nice day!" +
                "\n //Car Rental service//");
    }

    private String streamRentalListToString(List<RentalWithCarDto> rentalComplexDtoList) {
        return rentalComplexDtoList.stream()
                .map(RentalWithCarDto::toString)
                .collect(Collectors.joining(". \n\n\t", "\n <<", ">> \n"));
    }
}
