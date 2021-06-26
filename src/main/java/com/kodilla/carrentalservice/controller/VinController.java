package com.kodilla.carrentalservice.controller;

import com.kodilla.carrentalservice.dto.api.vin.VinApiDto;
import com.kodilla.carrentalservice.service.api.vin.VinApiService;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/vin")
public class VinController {

    private final VinApiService vinApiService;

    public VinController(VinApiService vinApiService) {
        this.vinApiService = vinApiService;
    }

    @GetMapping("/{vin}")
    public VinApiDto decodeVin(@PathVariable String vin) {
        return vinApiService.decodeVin(vin);

    }
}
