package Exceptions;
/**
 * Недопустимое имя
 * Игрок с таким именем уже существует
 * @author Kirill
 *
 */
public class UnexceptableNameException extends Exception {

	private static final long serialVersionUID = 1L;
	private String m_name;
	public UnexceptableNameException(String name) {
		super("Недопустимое имя: " + name);
		m_name = name;
	}
	public String getName() {
		return m_name;
	}
}
