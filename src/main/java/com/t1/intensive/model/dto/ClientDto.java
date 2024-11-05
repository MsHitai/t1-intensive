package com.t1.intensive.model.dto;

import lombok.Data;

@Data
public class ClientDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String middleName;
    private Boolean blockedFor;
    private String blockedWhom;
}
