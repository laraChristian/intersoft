package co.com.foundation.intersoft.exceptions;

public class SystemException extends Exception {

	private static final long serialVersionUID = 7999488762116743571L;

	public SystemException(final String message) {
		super(message);
	}

	public SystemException(final String message, final Throwable throwable) {
		super(message, throwable);
	}
}
