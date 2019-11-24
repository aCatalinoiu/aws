package com.project.bankmanag.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class AccountDTO {

    private Long id;
    private BigDecimal amount;
    private String iban;
    private int pinCode;
    private String currencyName;
    private String accountName;
    private List<Long> transactions;

}
