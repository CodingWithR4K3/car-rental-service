package com.kodilla.carrentalservice.controller;

import com.google.gson.Gson;
import com.kodilla.carrentalservice.domain.Status;
import com.kodilla.carrentalservice.dto.CarDto;
import com.kodilla.carrentalservice.facade.CarFacade;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CarController.class)
public class CarControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CarFacade carFacade;


    @Test
    public void shouldFetchCarById() throws Exception {
        //Given
        CarDto carDto = createSampleCar();
        when(carFacade.getCarById(1L)).thenReturn(carDto);

        //When & Then
        mockMvc.perform(get("/v1/cars/1")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .param("id", "1"))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.brand", is("Ford")));
    }

    @Test
    public void shouldFetchAllCars() throws Exception {
        //Given
        List<CarDto> carDtoList = createSampleCarList();
        when(carFacade.getCars()).thenReturn(carDtoList);

        //When & Then
        mockMvc.perform(get("/v1/cars")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));
    }

    @Test
    public void shouldFetchAllCarsByBrand() throws Exception {
        //Given
        List<CarDto> carDtoList = createSampleCarList();
        when(carFacade.getCarsByBrand("Ford")).thenReturn(carDtoList);

        //When & Then
        mockMvc.perform(get("/v1/cars/by_brand/Ford")
                .contentType(MediaType.APPLICATION_JSON)
                .param("brand", "Ford"))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].brand", is("Ford")));
    }

    @Test
    public void shouldFetchAllCarsByFuelType() throws Exception {
        //Given
        List<CarDto> carDtoList = createSampleCarList();
        when(carFacade.getCarsByFuelType("Gasoline")).thenReturn(carDtoList);

        //When & Then
        mockMvc.perform(get("/v1/cars/by_fuel/Gasoline")
                .contentType(MediaType.APPLICATION_JSON)
                .param("fuelType", "Gasoline"))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].fuelType", is("Gasoline")));
    }

    @Test
    public void shouldFetchCarsByChassisType() throws Exception {
        //Given
        List<CarDto> carDtoList = createSampleCarList();
        when(carFacade.getCarsByChassisType("Hatchback")).thenReturn(carDtoList);

        //When & Then
        mockMvc.perform(get("/v1/cars/by_chassis/Hatchback")
                .contentType(MediaType.APPLICATION_JSON)
                .param("chassisType", "Hatchback"))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].chassisType", is("Hatchback")));
    }

    @Test
    public void shouldFetchCarsByMileage() throws Exception {
        //Given
        List<CarDto> carDtoList = createSampleCarList();
        when(carFacade.getCarsByMileage(1000)).thenReturn(carDtoList);

        //When & Then
        mockMvc.perform(get("/v1/cars/by_mileage/1000")
                .contentType(MediaType.APPLICATION_JSON)
                .param("mileage", String.valueOf(1000)))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].vin", is("sampleVin")))
                .andExpect(jsonPath("$[0].mileage", is(1000)));
    }

    @Test
    public void shouldFetchCarsByCostPerDay() throws Exception {
        //Given
        List<CarDto> carDtoList = createSampleCarList();
        when(carFacade.getCarsByCostPerDay(new BigDecimal("499.99"))).thenReturn(carDtoList);

        //When & Then
        mockMvc.perform(get("/v1/cars/by_cost/499.99")
                .contentType(MediaType.APPLICATION_JSON)
                .param("costPerDay", String.valueOf(499.99)))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].costPerDay", is(499.99)));

    }

    @Test
    public void shouldCreateCar() throws Exception {
        //Given
        CarDto carDto = createSampleCar();
        when(carFacade.saveCar(ArgumentMatchers.any(CarDto.class))).thenReturn(carDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(carDto);

        //When & Then
        mockMvc.perform(post("/v1/cars")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.vin", is("sampleVin")))
                .andExpect(jsonPath("$.brand", is("Ford")));
    }

    @Test
    public void shouldUpdateCar() throws Exception {
        //Given
        CarDto carDto = createSampleCar();
        when(carFacade.saveCar(ArgumentMatchers.any(CarDto.class))).thenReturn(carDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(carDto);

        //When & Then
        mockMvc.perform(put("/v1/cars")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.chassisType", is("Hatchback")))
                .andExpect(jsonPath("$.fuelType", is("Gasoline")));
    }

    @Test
    public void shouldDeleteCar() throws Exception {
        //Given
        //When & Then
        mockMvc.perform(delete("/v1/cars/1")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .param("id", "1"))
                .andExpect(status().is(200));
    }

    private CarDto createSampleCar() {
        return CarDto.builder()
                .id(1L)
                .brand("Ford")
                .model("Mustang")
                .chassisType("Hatchback")
                .engineCapacity(3.7)
                .fuelType("Gasoline")
                .productionYear(2020)
                .mileage(1000)
                .costPerDay(new BigDecimal("499.99"))
                .vin("sampleVin")
                .status(Status.AVAILABLE)
                .build();
    }

    private List<CarDto> createSampleCarList() {
        CarDto carDto = createSampleCar();
        return Collections.singletonList(carDto);
    }
}