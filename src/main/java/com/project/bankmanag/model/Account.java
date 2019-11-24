package com.project.bankmanag.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {

    @Column(name = "account_id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "Please provide an amount")
    private BigDecimal amount;

    @NotNull(message = "Please provide an IBAN")
    @Column(unique = true)
    private String iban;

    @NotNull(message = "Please provide a pincode")
    @Column(name = "pin_code")
    private int pinCode;

    @NotNull(message = "Please provide a currencyName")
    @Enumerated(EnumType.STRING)
    @Column(name = "currency_name")
    private CurrencyType currencyName;

    @NotNull(message = "Please provide an account name")
    @Column(name = "account_name")
    private String accountName;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Transaction> transactions = new ArrayList<>();


    public Account(BigDecimal amount, String iban, int pinCode, CurrencyType currencyName, String accountName) {
        this.amount = amount;
        this.iban = iban;
        this.pinCode = pinCode;
        this.currencyName = currencyName;
        this.accountName = accountName;
    }


}
