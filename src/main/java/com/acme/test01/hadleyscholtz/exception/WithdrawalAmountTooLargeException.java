package com.acme.test01.hadleyscholtz.exception;

public class WithdrawalAmountTooLargeException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 553348594864419378L;
	
	public WithdrawalAmountTooLargeException() {
		super();
	}
	
	public WithdrawalAmountTooLargeException(String message) {
		super(message);
	}
}