package com.project.bankmanag.dto;

import lombok.Data;

@Data
public class ClientDTO {

    private Long clientId;
    private String firstName;
    private String lastName;
    private String cnp;
    private String PoB;
}
