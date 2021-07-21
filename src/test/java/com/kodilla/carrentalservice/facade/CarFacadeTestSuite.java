package com.kodilla.carrentalservice.facade;


import com.kodilla.carrentalservice.domain.Car;
import com.kodilla.carrentalservice.domain.Status;
import com.kodilla.carrentalservice.dto.CarDto;
import com.kodilla.carrentalservice.exception.CarNotFoundException;
import com.kodilla.carrentalservice.mapper.CarMapper;
import com.kodilla.carrentalservice.service.CarService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class CarFacadeTestSuite {

    @InjectMocks
    private CarFacade carFacade;

    @Mock
    private CarService carService;

    @Mock
    private CarMapper carMapper;

    @Test
    public void saveCarTest() {
        //Given
        Car car = initCar();
        CarDto carDto = initCarDto();

        when(carMapper.mapToCar(any())).thenReturn(car);
        when(carMapper.mapToCarDto(any())).thenReturn(carDto);

        //When
        CarDto savedCar = carFacade.saveCar(carDto);

        //Then
        assertEquals(carDto.getId(), savedCar.getId());
        assertEquals(carDto.getBrand(), savedCar.getBrand());
        assertEquals(carDto.getCostPerDay(), savedCar.getCostPerDay());
    }

    @Test
    public void getCarByIdTest() throws CarNotFoundException {
        //Given
        Car car = initCar();
        CarDto carDto = initCarDto();

        when(carService.getCarById(1L)).thenReturn(car);
        when(carMapper.mapToCarDto(car)).thenReturn(carDto);

        //When
        CarDto result = carFacade.getCarById(1L);

        //Then
        assertEquals(carDto.getId(), result.getId());
        assertEquals(carDto.getBrand(), result.getBrand());
    }

    @Test
    public void getCarByVinTest() throws CarNotFoundException {
        //Given
        Car car = initCar();
        CarDto carDto = initCarDto();

        when(carService.getCarByVin("sampleVin")).thenReturn(car);
        when(carMapper.mapToCarDto(car)).thenReturn(carDto);

        //When
        CarDto result = carFacade.getCarByVin("sampleVin");

        //Then
        assertEquals(carDto.getId(), result.getId());
        assertEquals(carDto.getVin(), result.getVin());
    }

    @Test
    public void getCarsTest() {
        //Given
        List<Car> carList = initCarList();
        List<CarDto> carDtoList = initCarDtoList();

        when(carService.getCars()).thenReturn(carList);
        when(carMapper.mapToCarDtoList(carList)).thenReturn(carDtoList);

        //When
        List<CarDto> resultList = carFacade.getCars();

        //Then
        assertNotNull(resultList);
        assertEquals(1, resultList.size());

        resultList.forEach(c -> {
            assertEquals(c.getId(), carDtoList.get(0).getId());
            assertEquals(c.getStatus(), carDtoList.get(0).getStatus());
        });
    }

    @Test
    public void getCarsByBrandTest() {
        //Given
        List<Car> carList = initCarList();
        List<CarDto> carDtoList = initCarDtoList();

        when(carService.getCarsByBrand("Audi")).thenReturn(carList);
        when(carMapper.mapToCarDtoList(carList)).thenReturn(carDtoList);

        //When
        List<CarDto> resultList = carFacade.getCarsByBrand("Audi");

        //Then
        assertNotNull(resultList);
        assertEquals(1, resultList.size());

        resultList.forEach(c -> {
            assertEquals(c.getId(), carDtoList.get(0).getId());
            assertEquals(c.getBrand(), carDtoList.get(0).getBrand());
        });
    }

    @Test
    public void getCarsByFuelTypeTest() {
        //Given
        List<Car> carList = initCarList();
        List<CarDto> carDtoList = initCarDtoList();

        when(carService.getCarsByFuelType("Diesel")).thenReturn(carList);
        when(carMapper.mapToCarDtoList(carList)).thenReturn(carDtoList);

        //When
        List<CarDto> resultList = carFacade.getCarsByFuelType("Diesel");

        //Then
        assertNotNull(resultList);
        assertEquals(1, resultList.size());

        resultList.forEach(c -> {
            assertEquals(c.getId(), carDtoList.get(0).getId());
            assertEquals(c.getFuelType(), carDtoList.get(0).getFuelType());
        });
    }

    @Test
    public void getCarsByBodyClassTest() {
        //Given
        List<Car> carList = initCarList();
        List<CarDto> carDtoList = initCarDtoList();

        when(carService.getCarsByChassisType("Saloon")).thenReturn(carList);
        when(carMapper.mapToCarDtoList(carList)).thenReturn(carDtoList);

        //When
        List<CarDto> resultList = carFacade.getCarsByChassisType("Saloon");

        //Then
        assertNotNull(resultList);
        assertEquals(1, resultList.size());

        resultList.forEach(c -> {
            assertEquals(c.getId(), carDtoList.get(0).getId());
            assertEquals(c.getChassisType(), carDtoList.get(0).getChassisType());
        });
    }

    @Test
    public void getCarsByMileageLessThenTest() {
        //Given
        List<Car> carList = initCarList();
        List<CarDto> carDtoList = initCarDtoList();

        when(carService.getCarsByMileage(260000)).thenReturn(carList);
        when(carMapper.mapToCarDtoList(carList)).thenReturn(carDtoList);

        //When
        List<CarDto> resultList = carFacade.getCarsByMileage(260000);

        //Then
        assertNotNull(resultList);
        assertEquals(1, resultList.size());

        resultList.forEach(c -> {
            assertEquals(c.getId(), carDtoList.get(0).getId());
            assertEquals(c.getMileage(), carDtoList.get(0).getMileage());
        });
    }

    @Test
    public void getCarsByCostPerDayLessThenTest() {
        //Given
        List<Car> carList = initCarList();
        List<CarDto> carDtoList = initCarDtoList();

        when(carService.getCarsByCostPerDay(new BigDecimal(32))).thenReturn(carList);
        when(carMapper.mapToCarDtoList(carList)).thenReturn(carDtoList);

        //When
        List<CarDto> resultList = carFacade.getCarsByCostPerDay(new BigDecimal(32));

        //Then
        assertNotNull(resultList);
        assertEquals(1, resultList.size());

        resultList.forEach(c -> {
            assertEquals(c.getId(), carDtoList.get(0).getId());
            assertEquals(c.getCostPerDay(), carDtoList.get(0).getCostPerDay());
        });
    }

    @Test
    public void deleteCarTest() {
        //Given
        //When
        carFacade.deleteCar(2L);

        //Then
        verify(carService, times(1)).deleteCar(2L);
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
                new BigDecimal(25));
    }

    private CarDto initCarDto() {
        return new CarDto(
                1L,
                "sampleVin",
                2015,
                "Audi",
                "A3",
                110000,
                "Saloon",
                "Diesel",
                3.0,
                new BigDecimal(25),
                Status.AVAILABLE);
    }

    private List<Car> initCarList() {
        Car car = initCar();
        return Collections.singletonList(car);
    }

    private List<CarDto> initCarDtoList() {
        CarDto carDto = initCarDto();
        return Collections.singletonList(carDto);
    }
}
