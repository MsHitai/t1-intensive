package com.t1.intensive.model.enumeration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TransactionStatus {

    ACCEPTED("ACCEPTED"),
    OPEN("OPEN"),
    REJECTED("REJECTED"),
    BLOCKED("BLOCKED"),
    CANCELLED("CANCELLED"),
    REQUESTED("REQUESTED");

    private final String value;
}
