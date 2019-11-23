package com.project.bankmanag.models;

public enum CurrencyType {

    EURO(0.859382032), POUNDS(1.16362684);

    private double exchangeRate;

    private CurrencyType(double exchangeRate) {
	this.exchangeRate = exchangeRate;
    }

    public double getExchangeRate() {
	return this.exchangeRate;
    }
}
