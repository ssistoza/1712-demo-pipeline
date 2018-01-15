package com.revature.exception;

public class NotLoggedInException extends Exception {
	private static final long serialVersionUID = 1L;

	public NotLoggedInException() {
		super();
	
	}

	public NotLoggedInException(String message, Throwable cause) {
		super(message, cause);
		
	}

	public NotLoggedInException(String message) {
		super(message);
	
	}
}
