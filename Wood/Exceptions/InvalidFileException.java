package Exceptions;
/**
 * Поврежден файл с лесом. В каждой строке не одинаковое количество символов
 * @author Kirill
 *
 */
public class InvalidFileException extends Exception{

	private static final long serialVersionUID = 1L;
	public InvalidFileException (String message) {
		super(message);
	}
	
}
