package com.kodilla.carrentalservice.service.emailService;

import com.kodilla.carrentalservice.domain.Mail;
import com.kodilla.carrentalservice.service.email.SenderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
public class EmailSenderServiceTestSuite {

    @InjectMocks
    private SenderService senderService;

    @Mock
    private JavaMailSender javaMailSender;

    @Test
    public void shouldSendEmail() {
        //Given
        Mail mail = new Mail("email@test.com", "ccemail@test.com", "subject", "message");
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(mail.getMailTo());
        mailMessage.setCc((mail.getToCc()));
        mailMessage.setSubject(mail.getSubject());
        mailMessage.setText(mail.getMessage());

        //When
        senderService.sendMail(mail);

        // Then
        verify(javaMailSender, times(1)).send(mailMessage);
    }
}
