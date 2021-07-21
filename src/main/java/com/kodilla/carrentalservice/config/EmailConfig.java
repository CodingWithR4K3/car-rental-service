package com.kodilla.carrentalservice.config;

import com.kodilla.carrentalservice.scheduler.EmailSenderScheduler;
import com.kodilla.carrentalservice.service.email.SenderService;
import com.kodilla.carrentalservice.strategy.EmailBodyService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmailConfig {

    @Bean
    public EmailSenderScheduler statisticsEmailScheduler(SenderService senderService,
                                                         AdminConfiguration adminConfiguration,
                                                         @Qualifier("statisticsEmailBodyService") EmailBodyService emailBodyService) {
        return new EmailSenderScheduler(senderService, adminConfiguration, emailBodyService);
    }

    @Bean
    public EmailSenderScheduler reminderEmailScheduler(SenderService senderService,
                                                       AdminConfiguration adminConfiguration,
                                                       @Qualifier("reminderEmailBodyService") EmailBodyService emailBodyService) {
        return new EmailSenderScheduler(senderService, adminConfiguration, emailBodyService);
    }
}
