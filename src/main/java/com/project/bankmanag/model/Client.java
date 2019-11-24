package com.project.bankmanag.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Client {

    @Column(name = "client_id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long clientId;

    @NotNull(message = "Please provide a firstName")
    private @Column(name = "first_name")
    String firstName;

    @NotNull(message = "Please provide a lastName")
    private @Column(name = "last_name")
    String lastName;

    @NotNull(message = "Please provide a CNP")
    private @Column(unique = true)
    String cnp;

    @NotNull(message = "Please provide a date of birth")
    private @Column(name = "date_of_birth")
    Date DoB;

    @NotNull(message = "Please provide a place of birth")
    private @Column(name = "location_of_birth")
    String PoB;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Account> accounts = new ArrayList<>();


    public void populate(String firstName, String lastName, String cnp, String pob, Date dob) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.cnp = cnp;
        this.PoB = pob;
        this.DoB = dob;
    }

}
