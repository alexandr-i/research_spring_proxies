package com.study.ivankov.common.exception;

/**
 * @author Ivankov_A
 *
 */
public class ExceptionRestResponse {

	private String message;
	private Exception causedByException;

	public ExceptionRestResponse(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Exception getCausedByException() {
		return causedByException;
	}

	public void setCausedByException(Exception causedByException) {
		this.causedByException = causedByException;
	}

}
