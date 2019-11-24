package com.project.bankmanag.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Type {

    @Column(name = "transaction_type")
    @ApiModelProperty(required = false, hidden = true)
    private @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @NotNull(message = "Please provide a name")
    @Column(unique = true)
    private String name;


}
