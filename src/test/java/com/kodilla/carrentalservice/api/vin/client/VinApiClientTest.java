package com.kodilla.carrentalservice.api.vin.client;

import com.kodilla.carrentalservice.api.vin.config.VinApiConfig;
import com.kodilla.carrentalservice.dto.api.vin.VinApiDto;
import com.kodilla.carrentalservice.dto.api.vin.VinBodyDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VinApiClientTest {

    @InjectMocks
    private VinApiClient vinApiClient;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private VinApiConfig vinApiConfig;

    @Test
    public void shouldDecodeVin() throws URISyntaxException {

        when(vinApiConfig.getVinEndpoint()).thenReturn("https://vpic.nhtsa.dot.gov/api/vehicles/decodevinvalues");

        //Given
        VinBodyDto vinBodyDto = VinBodyDto.builder()
                .manufacturer("BMW")
                .model("325xi")
                .productYear("2006")
                .vehicleType("PASSENGER CAR")
                .build();

        List<VinBodyDto> vinBodyDtoList = Collections.singletonList(vinBodyDto);
        VinApiDto vinApiDto = new VinApiDto(vinBodyDtoList);

        URI uri = new URI("https://vpic.nhtsa.dot.gov/api/vehicles/decodevinvalues/WBAVD13596KX00407?format=json");
        when(restTemplate.getForObject(uri, VinApiDto.class)).thenReturn(vinApiDto);

        //When
        VinApiDto result = vinApiClient.decodeVin("WBAVD13596KX00407");

        //Then
        assertEquals("BMW", result.getVinBodyDtoList().get(0).getManufacturer());
        assertEquals("325xi", result.getVinBodyDtoList().get(0).getModel());

    }

}