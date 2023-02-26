package com.acme.test01.hadleyscholtz.exception;

public class DepositAmountLessThanMinimumException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2578888155685339542L;

	public DepositAmountLessThanMinimumException() {
		super();
	}
	
	public DepositAmountLessThanMinimumException(String message) {
		super(message);
	}
}
