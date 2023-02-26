package com.acme.test01.hadleyscholtz.service;

public class CurrentAccount extends Account {
	
	private Long overdraft;
	
	public CurrentAccount(Long id, String customerNumber, Long balance, Long overdraft) {
		this.id = id;
		this.customerNumber = customerNumber;
		this.currentBalance = balance;
		this.overdraft = overdraft;
	}

	public Long getOverdraft() {
		return overdraft;
	}

	public void setOverdraft(Long overdraft) {
		this.overdraft = overdraft;
	}
}