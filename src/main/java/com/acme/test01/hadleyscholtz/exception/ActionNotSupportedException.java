package com.acme.test01.hadleyscholtz.exception;

public class ActionNotSupportedException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8550284625518034640L;

	public ActionNotSupportedException() {
		super();
	}
	
	public ActionNotSupportedException(String message) {
		super(message);
	}
}
