package com.acme.test01.hadleyscholtz.repository;

import java.util.HashMap;
import java.util.Map;

import com.acme.test01.hadleyscholtz.service.Account;
import com.acme.test01.hadleyscholtz.service.CurrentAccount;
import com.acme.test01.hadleyscholtz.service.SavingsAccount;

public class SystemDB {
	
	public static Map<Long, Account> database;

	public SystemDB() {
		if(database == null) {
			initializeDatabase();
		}
	}
	
	public static Map<Long, Account> getDatabaseInstance() {
		if(database != null) {
			return database;
		} else {
			initializeDatabase();
			return database;
		}
	}
	
	private static void initializeDatabase() {
		database = new HashMap<Long, Account>();
		
		//Populate some accounts here
		//Customer 1 -> Id = 1; CustomerNumber = 1; Balance = 2000
		Account customer1 = new SavingsAccount(1L, "1", 2000L);
		database.put(1L, customer1);
		
		//Customer 2 -> Id = 2; CustomerNumber = 2; Balance = 5000
		Account customer2 = new SavingsAccount(2L, "2", 2000L);
		database.put(2L, customer2);
				
		//Customer 3 -> Id = 3; CustomerNumber = 3; Balance = +1000; Overdraft = 10 000
		Account customer3 = new CurrentAccount(3L, "3", 1000L, 10000L);
		database.put(3L, customer3);
				
		//Customer 4 -> Id = 4; CustomerNumber = 4; Balance = -5000; Overdraft = 20 000
		Account customer4 = new CurrentAccount(4L, "4", -5000L, 20000L);
		database.put(4L, customer4);
	}
}