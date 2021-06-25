package com.kodilla.carrentalservice.mapper;

import com.kodilla.carrentalservice.domain.Rental;
import com.kodilla.carrentalservice.dto.RentalDto;
import com.kodilla.carrentalservice.dto.RentalWithCarDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface RentalMapper {

    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "carId", source = "car.id")
    RentalDto mapToRentalDto(final Rental rental);

    List<RentalDto> mapToRentalDtoList(final List<Rental> rentalList);

    @Mapping(target = "rentalCost", source = "cost")
    @Mapping(target = "carId", source = "car.id")
    @Mapping(target = "carBrand", source = "car.brand")
    @Mapping(target = "carModel", source = "car.model")
    @Mapping(target = "userName", source = "user.name")
    @Mapping(target = "userLastName", source = "user.lastName")
    @Mapping(target = "userEmail", source = "user.email")
    @Mapping(target = "userPhoneNumber", source = "user.phoneNumber")
    RentalWithCarDto mapToRentalWithCarDto(final Rental rental);

    List<RentalWithCarDto> mapToRentalWithCarDtoList(final List<Rental> rentalList);
}
