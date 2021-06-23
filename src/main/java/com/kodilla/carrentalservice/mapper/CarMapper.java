package com.kodilla.carrentalservice.mapper;

import com.kodilla.carrentalservice.domain.Car;
import com.kodilla.carrentalservice.dto.CarDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CarMapper {

    Car mapToCar(final CarDto carDto);

    CarDto mapToCarDto(final Car car);

    List<CarDto> mapToCarDtoList(final List<Car> carList);
}
