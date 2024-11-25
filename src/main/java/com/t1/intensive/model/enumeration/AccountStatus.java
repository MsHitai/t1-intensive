package com.t1.intensive.model.enumeration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AccountStatus {

    ARRESTED("ARRESTED"),
    BLOCKED("BLOCKED"),
    CLOSED("CLOSED"),
    OPEN("OPEN");

    private final String value;
}
