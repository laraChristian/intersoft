package co.com.foundation.intersoft.exceptions;

public class DischargeDateException extends SystemException {

	private static final long serialVersionUID = 1L;

	public DischargeDateException(String message, Throwable throwable) {
		super(message, throwable);
	}

	public DischargeDateException(String message) {
		super(message);
	}

}
