package com.kodilla.carrentalservice.service.emailService;

import com.kodilla.carrentalservice.api.vin.client.EmailVerificationClient;
import com.kodilla.carrentalservice.dto.api.email.EmailVerificationDto;
import com.kodilla.carrentalservice.service.email.EmailVerificationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class EmailVerificationServiceTestSuite {

    @InjectMocks
    private EmailVerificationService emailVerificationService;

    @Mock
    private EmailVerificationClient emailVerificationClient;

    @Test
    public void verifyEmailTest() {
        //Given
        EmailVerificationDto emailVerificationDto = new EmailVerificationDto("true", "true", "true");
        when(emailVerificationClient.verifyEmail("tamagotchi3377@gmail.com")).thenReturn(emailVerificationDto);

        //When
        EmailVerificationDto result = emailVerificationService.verifyEmail("tamagotchi3377@gmail.com");

        //Then
        assertEquals("true", result.getDnsCheck());
        assertEquals("true", result.getSmtpCheck());
        assertEquals("true", result.getFormatCheck());
    }
}
