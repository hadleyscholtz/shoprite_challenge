package com.acme.test01.hadleyscholtz.service;

import java.util.Map;

import com.acme.test01.hadleyscholtz.exception.AccountAlreadyExistsException;
import com.acme.test01.hadleyscholtz.exception.AccountNotFoundException;
import com.acme.test01.hadleyscholtz.exception.ActionNotSupportedException;
import com.acme.test01.hadleyscholtz.exception.DepositAmountLessThanMinimumException;
import com.acme.test01.hadleyscholtz.exception.WithdrawalAmountTooLargeException;

public class SavingsAccountService implements AccountService {
	
	private Long minimumBalance = 2000L;
	
	Map<Long, Account> database = getDatabase();
	
	public void openSavingsAccount(Long accountId, Long amountToDeposit) throws DepositAmountLessThanMinimumException {
		
		if(amountToDeposit < minimumBalance) {
			throw new DepositAmountLessThanMinimumException("A minimum of 2000 is required to open a Savings Account");
		}
		
		//Check if account already exists
		Account account = database.get(accountId);
		
		if(account != null) {
			throw new AccountAlreadyExistsException("An account already exists with ID = " + accountId);
		}
		
		//Spin up new account
		account = new SavingsAccount(accountId, String.valueOf(accountId), amountToDeposit);
		
		//Save to database
		database.put(accountId, account);
	}

	public void openCurrentAccount(Long accountId) {
		throw new ActionNotSupportedException("Current Account not supported on the Savings Product");
	}

	public void withdraw(Long accountId, int amountToWithdraw)
			throws AccountNotFoundException, WithdrawalAmountTooLargeException {
		
		Account account = database.get(accountId);
		
		if(account == null) {
			throw new AccountNotFoundException("Account not found for ID = " + accountId);
		}
		
		if(account instanceof SavingsAccount) {
			Long futureBalance = account.getCurrentBalance() - amountToWithdraw;
			
			if(futureBalance < minimumBalance) {
				throw new WithdrawalAmountTooLargeException("A minimum of " + minimumBalance + " is required for the account to remain opened. The withdrawal amount exceeds available balance.");
			}
			
			//Update account current balance
			account.setCurrentBalance(account.getCurrentBalance() - amountToWithdraw);
			
			//Update account
			database.put(accountId, account);
			
		} else {
			throw new ActionNotSupportedException("Operations on a " + account.getClass().getSimpleName() + " is not allowed on the Savings product.");
		}
	}
	
	public void deposit(Long accountId, int amountToDeposit) {
		Account account = database.get(accountId);
		
		//Account ID not present in database
		if(account == null) {
			throw new AccountNotFoundException("Account not found for ID = " + accountId);
		}
		
		if(account instanceof SavingsAccount) {
			//Update current balance
			account.setCurrentBalance(account.getCurrentBalance() + amountToDeposit);
			
			//Update database
			database.put(accountId, account);
		} else {
			throw new ActionNotSupportedException("Operations on a " + account.getClass().getSimpleName() + " is not allowed on the Savings product.");
		}
	}
}