package com.kodilla.carrentalservice.service.email;

import com.kodilla.carrentalservice.api.vin.client.EmailVerificationClient;
import com.kodilla.carrentalservice.dto.api.email.EmailVerificationDto;
import org.springframework.stereotype.Service;

@Service
public class EmailVerificationService {

    private final EmailVerificationClient emailVerificationClient;

    public EmailVerificationService(EmailVerificationClient emailVerificationClient) {
        this.emailVerificationClient = emailVerificationClient;
    }

    public EmailVerificationDto verifyEmail(final String email) {
        return emailVerificationClient.verifyEmail(email);
    }
}
