package com.acme.test01.hadleyscholtz.service;

public class SavingsAccount extends Account {
	
	public SavingsAccount(Long id, String customerNumber, Long balance) {
		this.id = id;
		this.customerNumber = customerNumber;
		this.currentBalance = balance;
	}
}