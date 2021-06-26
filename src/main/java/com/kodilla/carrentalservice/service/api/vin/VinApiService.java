package com.kodilla.carrentalservice.service.api.vin;

import com.kodilla.carrentalservice.api.vin.client.VinApiClient;
import com.kodilla.carrentalservice.dto.api.vin.VinApiDto;
import org.springframework.stereotype.Service;

@Service
public class VinApiService {

    private final VinApiClient vinApiClient;

    public VinApiService(VinApiClient vinApiClient) {
        this.vinApiClient = vinApiClient;
    }

    public VinApiDto decodeVin(String vin) {
        return vinApiClient.decodeVin(vin);
    }
}
