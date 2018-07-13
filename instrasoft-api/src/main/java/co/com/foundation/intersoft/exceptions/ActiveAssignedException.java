package co.com.foundation.intersoft.exceptions;

public class ActiveAssignedException extends SystemException {

	private static final long serialVersionUID = 1L;

	public ActiveAssignedException(String message, Throwable throwable) {
		super(message, throwable);
	}

	public ActiveAssignedException(String message) {
		super(message);
	}

}
