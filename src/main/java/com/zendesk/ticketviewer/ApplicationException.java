package com.zendesk.ticketviewer;

public class ApplicationException extends Exception {
	

	private static final long serialVersionUID = 1L;
	private int errorCode;
	
	public ApplicationException(int errorCode) {
		this.setErrorCode(errorCode);
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

}
