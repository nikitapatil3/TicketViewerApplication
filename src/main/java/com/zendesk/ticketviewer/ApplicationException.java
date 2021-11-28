package com.zendesk.ticketviewer;

import java.util.Objects;

public class ApplicationException extends Exception {

	private static final long serialVersionUID = 1L;
	private int errorCode;

	public ApplicationException() {}

	public ApplicationException(int errorCode) {
		this.setErrorCode(errorCode);
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		ApplicationException that = (ApplicationException) o;
		return errorCode == that.errorCode;
	}

	@Override
	public int hashCode() {
		return Objects.hash(errorCode);
	}

}
