package com.project.bankmanag.models;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
	

@Entity
public class Transaction {
	@Column(name="transaction_id")
	private @Id @GeneratedValue(strategy = GenerationType.AUTO) Long id;
	private @Column(name="transaction_amount") BigDecimal amount;
	private @Column(name="transaction_date") Timestamp date;
	@OneToMany(cascade = CascadeType.ALL)
	@Column(name="transaction_type")
	private Set<Type> type = new HashSet<>();
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public Timestamp getDate() {
		return date;
	}
	public void setDate(Timestamp date) {
		this.date = date;
	}
	public Set<Type> getType() {
		return type;
	}
	public void setType(Set<Type> type) {
		this.type = type;
	}
	
	
}
