package co.com.foundation.intersoft.exceptions;

public class PersistenceException extends SystemException {

	private static final long serialVersionUID = 1L;

	public PersistenceException(String message, Throwable throwable) {
		super(message, throwable);
	}

	public PersistenceException(String message) {
		super(message);
	}

}
