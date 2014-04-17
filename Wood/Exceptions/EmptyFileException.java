package Exceptions;

import java.io.IOException;
/**
 * Поток с лесом пуст
 * @author Kirill
 *
 */
public class EmptyFileException extends IOException {
	private static final long serialVersionUID = 1L;
	public EmptyFileException (String message) {
		super(message);
	}
}
