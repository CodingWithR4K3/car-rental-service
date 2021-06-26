package com.kodilla.carrentalservice.api.vin.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class VinApiConfig {

    @Value("${vin.api.endpoint}")
    private String vinEndpoint;
}
