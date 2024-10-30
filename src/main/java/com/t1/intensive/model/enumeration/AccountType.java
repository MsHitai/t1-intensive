package com.t1.intensive.model.enumeration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AccountType {

    DEBIT("DEBIT"),
    CREDIT("CREDIT");

    private final String value;
}
