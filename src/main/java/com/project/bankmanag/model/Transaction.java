package com.project.bankmanag.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Transaction {

    @Column(name = "transaction_id")
    @ApiModelProperty(required = false, hidden = true)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "Please provide an amount")
    @Column(name = "transaction_amount")
    private double amount;

    @NotNull(message = "Please provide a date")
    @Column(name = "transaction_date")
    private Date date;

    // TODO: add clientId
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "transaction_type")
    // TODO: validare
    private Type type;

    public void populate(double amount, Date date, Type type) {
        this.amount = amount;
        this.date = date;
        this.type = type;
    }
}
