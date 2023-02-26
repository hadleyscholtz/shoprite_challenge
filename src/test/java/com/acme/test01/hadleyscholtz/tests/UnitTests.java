package com.acme.test01.hadleyscholtz.tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.acme.test01.hadleyscholtz.exception.AccountAlreadyExistsException;
import com.acme.test01.hadleyscholtz.exception.ActionNotSupportedException;
import com.acme.test01.hadleyscholtz.exception.DepositAmountLessThanMinimumException;
import com.acme.test01.hadleyscholtz.exception.WithdrawalAmountTooLargeException;
import com.acme.test01.hadleyscholtz.repository.SystemDB;
import com.acme.test01.hadleyscholtz.service.CurrentAccountService;
import com.acme.test01.hadleyscholtz.service.SavingsAccountService;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UnitTests {
	
	/*
	 * Pre Existing Account IDs: 1, 2, 3, 4
	 */
	
	private SavingsAccountService savingsAccountService;
	private CurrentAccountService currentAccountService;
	
	@Before
	public void initializeDB() {
		//Initialize database
		new SystemDB();
		
		//Initialize services
		savingsAccountService = new SavingsAccountService();
		currentAccountService = new CurrentAccountService();
	}
	
	@Test
	public void test1_openNewSavingsAccount() {
		try {
			savingsAccountService.openSavingsAccount(10L, 5000L);
		} catch (DepositAmountLessThanMinimumException daltme) {
			System.out.println("Deposit failed: " + daltme.getMessage());
		}
	}
	
	@Test
	public void test2_openNewCurrentAccount() {
		currentAccountService.openCurrentAccount(11L);
	}
	
	@Test
	public void test3_openExistingCurrentAccount() {
		Assert.assertThrows(AccountAlreadyExistsException.class, () -> currentAccountService.openCurrentAccount(1L));
	}
	
	@Test
	public void test4_depositToAnExistingSavingsAccount() {
		savingsAccountService.deposit(10L, 500);
	}
	
	@Test
	public void test5_withdrawMoreThanBalanceSavingsAccount() {
		Assert.assertThrows(WithdrawalAmountTooLargeException.class, () -> savingsAccountService.withdraw(10L, 4000));
	}
	
	@Test
	public void test6_withdrawFromCurrentAccount() {
		try {
			currentAccountService.withdraw(4L, 12000);
		} catch (WithdrawalAmountTooLargeException watle) {
			System.out.println("Withdrawal failed:" + watle.getMessage());
		}
	}
	
	@Test
	public void test7_withdrawMoreThanCurrentBalanceCurrentAccount() {
		Assert.assertThrows(WithdrawalAmountTooLargeException.class, () -> currentAccountService.withdraw(4L, 5000));
	}
	
	@Test
	public void test8_withdrawFromAccountUsingIncorrectService() {
		Assert.assertThrows(ActionNotSupportedException.class, () -> savingsAccountService.withdraw(4L, 5000));
	}
	
	@Test
	public void test9_depositToAccountUsingIncorrectService() {
		Assert.assertThrows(ActionNotSupportedException.class, () -> currentAccountService.deposit(1L, 5000));
	}
	
	@Test
	public void test10_openNewSavingsAccountWithLessThanMinimumBalance() {
		Assert.assertThrows(DepositAmountLessThanMinimumException.class, () -> savingsAccountService.openSavingsAccount(12L, 1000L));
	}
}
