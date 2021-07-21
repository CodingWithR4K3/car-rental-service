package com.kodilla.carrentalservice.service.email;

import com.kodilla.carrentalservice.config.AdminConfiguration;
import com.kodilla.carrentalservice.domain.Mail;
import com.kodilla.carrentalservice.domain.Rental;
import com.kodilla.carrentalservice.domain.User;
import org.springframework.stereotype.Service;

@Service
public class EmailToUserService {

    private static final String WELCOME_SUBJECT = "New account created!!";
    private static final String CREATE_RENTAL = "New rental registered!";
    private static final String MODIFIED_RENTAL = "Rental has been modified!";
    private static final String EXTEND_RENTAL = "Rental has been extended!";
    private static final String CLOSE_RENTAL = "Rental has been closed!";
    private final SenderService senderService;
    private final AdminConfiguration adminConfiguration;

    public EmailToUserService(SenderService senderService, AdminConfiguration adminConfiguration) {
        this.senderService = senderService;
        this.adminConfiguration = adminConfiguration;
    }

    public void sendEmailAboutCreatingUser(User user) {
        senderService.sendMail(new Mail(
                user.getEmail(),
                WELCOME_SUBJECT,
                greetingsMessageCreate(user)));
    }

    public void sendEmailAboutRental(Rental rental, String subjectType) {
        senderService.sendMail(new Mail(
                rental.getUser().getEmail(),
                adminConfiguration.getAdminMail(),
                verifyEmailSubject(subjectType),
                rentalMessageCreate(rental, subjectType)));
    }

    private String verifyEmailSubject(String subjectType) {
        String subject = null;

        switch (subjectType) {
            case "created":
                subject = CREATE_RENTAL;
                break;
            case "extended":
                subject = EXTEND_RENTAL;
                break;
            case "closed":
                subject = CLOSE_RENTAL;
                break;
            case "modified":
                subject = MODIFIED_RENTAL;
                break;
        }
        return subject;
    }

    private String rentalMessageCreate(Rental rental, String messageType) {
        return ("\n Hello " + rental.getUser().getName() + " !" +
                "\n Your rental has been " + messageType + ". Rental details: \n" +
                "\n\t Id: " + rental.getId() +
                "\n\t Rental starts: " + rental.getRentedFrom() +
                "\n\t Rental ends: " + rental.getRentedTo() +
                "\n\t Duration: " + rental.getDuration() + " days" +
                "\n\t Cost : " + rental.getCost() + " $" +
                "\n\t Car rented: " + rental.getCar().getBrand() + " - " + rental.getCar().getModel() + "\n" +
                "\n Have a nice day!" +
                "\n //Car Rental service//");
    }

    private String greetingsMessageCreate(User user) {
        return ("\n Hello " + user.getName() + " !" +
                "\n Your account has been created. Please verify if Your data are correct: " + "\n" +
                "\n\t Id: " + user.getId() +
                "\n\t Name: " + user.getName() +
                "\n\t Lastname: " + user.getLastName() +
                "\n\t Phone number: " + user.getPhoneNumber() +
                "\n\t Email(login): " + user.getEmail() +
                "\n\t Password: " + user.getPassword() + "\n" +
                "\n We encourage You to check out cars on our website. If You have any questions don't hesitate to" +
                " contact us. \n" +
                "\n Have a nice day!" +
                "\n //Car Rental service//");
    }
}
