package co.com.foundation.intersoft.exceptions;

public class ActiveNotAvailableException extends SystemException {

	private static final long serialVersionUID = 1L;

	public ActiveNotAvailableException(String message, Throwable throwable) {
		super(message, throwable);
	}

	public ActiveNotAvailableException(String message) {
		super(message);
	}

}
