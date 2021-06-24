package com.kodilla.carrentalservice.controller;

import com.kodilla.carrentalservice.dto.RentalWithCarDto;
import com.kodilla.carrentalservice.service.RentalService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(RentalController.class)
class RentalControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RentalService rentalService;


    @Test
    public void shouldFetchAllRentals() throws Exception {
        //Given
        RentalWithCarDto rentalWithCarDto = createSampleRental();
        List<RentalWithCarDto> rentalWithCarDtoList = Collections.singletonList(rentalWithCarDto);
        doReturn(rentalWithCarDtoList).when(rentalService).getRentals();
        //When & Then
        mockMvc.perform(get("/v1/rentals")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].carBrand", is("Ford")));
    }


    @Test
    public void shouldCloseRental() throws Exception {
        //Given
        //When & Then
        mockMvc.perform(delete("/v1/rentals/1")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .param("id", "1"))
                .andExpect(status().is(200));
    }

    private RentalWithCarDto createSampleRental() {
        return RentalWithCarDto.builder()
                .id(1L)
                .rentedFrom(LocalDate.of(2020, 6, 6))
                .rentedTo(LocalDate.of(2020, 6, 6))
                .rentalCost(new BigDecimal(500))
                .carId(1L)
                .carBrand("Ford")
                .carModel("Mustang")
                .userName("Konrad")
                .userLastName("Gorgol")
                .userEmail("sampleEmail")
                .userPhoneNumber(1234)
                .build();
    }
}