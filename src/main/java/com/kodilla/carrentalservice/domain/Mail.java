package com.kodilla.carrentalservice.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public class Mail {
    @NonNull
    private final String mailTo;
    @NonNull
    private final String subject;
    @NonNull
    private final String message;
    private String toCc;
}

