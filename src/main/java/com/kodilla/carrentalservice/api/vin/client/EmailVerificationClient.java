package com.kodilla.carrentalservice.api.vin.client;

import com.kodilla.carrentalservice.api.vin.config.EmailVerificationConfig;
import com.kodilla.carrentalservice.dto.api.email.EmailVerificationDto;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component
public class EmailVerificationClient {

    private final RestTemplate restTemplate;
    private final EmailVerificationConfig emailVerificationConfig;

    public EmailVerificationClient(RestTemplate restTemplate, EmailVerificationConfig emailVerificationConfig) {
        this.restTemplate = restTemplate;
        this.emailVerificationConfig = emailVerificationConfig;
    }

    public EmailVerificationDto verifyEmail(String email) {
        URI url = getEmailVerificationUri(email);
        return restTemplate.getForObject(url, EmailVerificationDto.class);
    }

    private URI getEmailVerificationUri(String email) {
        return UriComponentsBuilder.fromHttpUrl(emailVerificationConfig.getEmailVerificationApiEndpoint())
                .queryParam("apiKey", emailVerificationConfig.getEmailVerificationApiKey())
                .queryParam("emailAddress", email)
                .build().encode().toUri();
    }
}
