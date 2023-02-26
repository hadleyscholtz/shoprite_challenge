package com.acme.test01.hadleyscholtz.service;

import java.util.Map;

import com.acme.test01.hadleyscholtz.exception.AccountAlreadyExistsException;
import com.acme.test01.hadleyscholtz.exception.AccountNotFoundException;
import com.acme.test01.hadleyscholtz.exception.ActionNotSupportedException;
import com.acme.test01.hadleyscholtz.exception.WithdrawalAmountTooLargeException;

public class CurrentAccountService implements AccountService {
	
	Map<Long, Account> database = getDatabase();
	
	public void openSavingsAccount(Long accountId, Long amountToDeposit) {
		throw new ActionNotSupportedException("Savings Account not supported on the Current Product");
	}

	public void openCurrentAccount(Long accountId) {
		//Check if account already exists
		Account account = database.get(accountId);
		
		if(account != null) {
			throw new AccountAlreadyExistsException("An account already exists with ID = " + accountId);
		}
		
		//Spin up new account
		account = new CurrentAccount(accountId, String.valueOf(accountId), 0L, 0L);
		
		//Save to database
		database.put(accountId, account);
	}

	public void withdraw(Long accountId, int amountToWithdraw)
			throws AccountNotFoundException, WithdrawalAmountTooLargeException {
		Account account = database.get(accountId);
		
		if(account == null) {
			throw new AccountNotFoundException("Account not found for ID = " + accountId);
		}
		
		if(account instanceof CurrentAccount) {
			Long totalBalance = account.getCurrentBalance() + ((CurrentAccount)account).getOverdraft();
			
			if(amountToWithdraw > totalBalance) {
				throw new WithdrawalAmountTooLargeException("Withdrawal amount exceeds available balance");
			}
			
			//Update account current balance
			account.setCurrentBalance(account.getCurrentBalance() - amountToWithdraw);
			
			//Update account
			database.put(accountId, account);
			
		} else {
			throw new ActionNotSupportedException("Operations on a " + account.getClass().getSimpleName() + " is not allowed on the Current product.");
		}
	}
	
	public void deposit(Long accountId, int amountToDeposit) {
		Account account = database.get(accountId);
		
		//Account ID not present in database
		if(account == null) {
			throw new AccountNotFoundException("Account not found for ID = " + accountId);
		}
		
		if(account instanceof CurrentAccount) {
			//Update current balance
			account.setCurrentBalance(account.getCurrentBalance() + amountToDeposit);
			
			//Update database
			database.put(accountId, account);
			
		} else {
			throw new ActionNotSupportedException("Operations on a " + account.getClass().getSimpleName() + " is not allowed on the Current product.");
		}
	}
}