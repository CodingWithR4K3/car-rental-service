package com.kodilla.carrentalservice.mapper;

import com.kodilla.carrentalservice.domain.Rental;
import com.kodilla.carrentalservice.dto.RentalDto;
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
}
