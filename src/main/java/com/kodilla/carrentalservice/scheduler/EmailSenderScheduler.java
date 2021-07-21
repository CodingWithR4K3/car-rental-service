package com.kodilla.carrentalservice.scheduler;

import com.kodilla.carrentalservice.config.AdminConfiguration;
import com.kodilla.carrentalservice.domain.Mail;
import com.kodilla.carrentalservice.service.email.SenderService;
import com.kodilla.carrentalservice.strategy.EmailBodyService;
import org.springframework.scheduling.annotation.Scheduled;

public class EmailSenderScheduler {
    private static final String SUBJECT = "Car rental: Your daily email!";

    private final SenderService senderService;
    private final AdminConfiguration adminConfiguration;
    private final EmailBodyService emailBodyService;

    public EmailSenderScheduler(SenderService senderService, AdminConfiguration adminConfiguration,
                                EmailBodyService emailBodyService) {
        this.senderService = senderService;
        this.adminConfiguration = adminConfiguration;
        this.emailBodyService = emailBodyService;
    }

    //@Scheduled(cron = "*/30 * * * * *")
    @Scheduled(cron = "0 0 6 * * *")
    public void sendDailyEmail() {
        senderService.sendMail(new Mail(
                adminConfiguration.getAdminMail(),
                SUBJECT,
                emailBodyService.emailBodyCreate()));
    }
}
