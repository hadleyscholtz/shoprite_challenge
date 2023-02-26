package com.acme.test01.hadleyscholtz.exception;

public class AccountAlreadyExistsException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1694050537690541926L;

	public AccountAlreadyExistsException() {
		super();
	}
	
	public AccountAlreadyExistsException(String message) {
		super(message);
	}
}
