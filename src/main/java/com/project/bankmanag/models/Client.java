package com.project.bankmanag.models;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

@Entity
public class Client {
	@Column(name="client_id")
	private @Id @GeneratedValue(strategy = GenerationType.AUTO) Long clientId;
	private @Column(name="first_name") @NotBlank(message="firstName is mandatory") String firstName;
	private @Column(name="last_name") @NotBlank(message="lastName is mandatory") String lastName;
	private @Column(unique=true) @NotBlank(message="CNP is mandatory") String cnp;
	private @Column(name="date_of_birth") @NotBlank(message="Date of birth is mandatory") Date DoB;
	private @Column(name="location_of_birth") @NotBlank(message="Location of birth is mandatory") String PoB;
	
	@OneToMany(cascade = CascadeType.ALL)
	private Set<Account> accounts = new HashSet<>();
	
	public Client() {}
	
	public Client(Long clientId, String firstName, String lastName, String cnp, Date doB, String poB) {
		super();
		this.clientId = clientId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.cnp = cnp;
		DoB = doB;
		PoB = poB;
	}
	public Long getClientId() {
		return clientId;
	}
	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getCnp() {
		return cnp;
	}
	public void setCnp(String cnp) {
		this.cnp = cnp;
	}
	public Date getDoB() {
		return DoB;
	}
	public void setDoB(Date doB) {
		DoB = doB;
	}
	public String getPoB() {
		return PoB;
	}
	public void setPoB(String poB) {
		PoB = poB;
	}
	
	public void populate(String firstName, String lastName, String cnp, String pob, Date dob){
		this.firstName = firstName;
		this.lastName = lastName;
		this.cnp = cnp;
		this.PoB = pob;
		this.DoB = dob;
	}

}
