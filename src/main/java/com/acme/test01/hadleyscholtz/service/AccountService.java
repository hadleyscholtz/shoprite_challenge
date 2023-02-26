package com.acme.test01.hadleyscholtz.service;

import java.util.Map;

import com.acme.test01.hadleyscholtz.exception.AccountNotFoundException;
import com.acme.test01.hadleyscholtz.exception.ActionNotSupportedException;
import com.acme.test01.hadleyscholtz.exception.DepositAmountLessThanMinimumException;
import com.acme.test01.hadleyscholtz.exception.WithdrawalAmountTooLargeException;
import com.acme.test01.hadleyscholtz.repository.SystemDB;

public interface AccountService {
	
	public void openSavingsAccount(Long accountId, Long amountToDeposit) throws ActionNotSupportedException, DepositAmountLessThanMinimumException;
	
	public void openCurrentAccount(Long accountId) throws ActionNotSupportedException;
	
	public void withdraw(Long accountId, int amountToWithdraw) throws AccountNotFoundException, WithdrawalAmountTooLargeException;
	
	public void deposit(Long accountId, int amountToDeposit) throws AccountNotFoundException;
	
	default Map<Long, Account> getDatabase() {
		return SystemDB.getDatabaseInstance();
	}
}
