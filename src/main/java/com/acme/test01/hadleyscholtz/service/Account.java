package com.acme.test01.hadleyscholtz.service;

public abstract class Account {
	
	protected Long id;
	protected String customerNumber;
	protected Long currentBalance;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getCustomerNumber() {
		return customerNumber;
	}
	
	public void setCustomerNumber(String customerNumber) {
		this.customerNumber = customerNumber;
	}
	
	public Long getCurrentBalance() {
		return currentBalance;
	}
	
	public void setCurrentBalance(Long currentBalance) {
		this.currentBalance = currentBalance;
	}
}