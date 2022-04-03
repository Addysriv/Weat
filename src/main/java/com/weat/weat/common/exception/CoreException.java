package com.weat.weat.common.exception;


public class CoreException extends RuntimeException {

	private static final long serialVersionUID = -5994053631370106273L;

	private String message;
	private int errorCode;

	public CoreException() {
		super();
	}

	public CoreException(String message) {
		super(message);
	}

	public CoreException(int errorCode, String message) {
		super(message);
		this.errorCode = errorCode;
		this.message = message;
	}

	public CoreException(int errorCode, String message, Throwable throwable) {
		super(message, throwable);
		this.errorCode = errorCode;
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public int getErrorCode() {
		return errorCode;
	}
}