package com.kodilla.carrentalservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "User with that credentials don't exists")
public class LoginNotFoundException extends Exception {
}
