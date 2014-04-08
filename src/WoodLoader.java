import java.io.InputStream;

/**
 * Создатель леса
 */
public interface WoodLoader {
	/**
	 * Создает экземпляр леса по данным из потока.
	 * @param stream поток с информацией о лесе.
	 * @return Лес
	 * @throws Exception 
	 */
	Wood Load(InputStream stream) throws Exception;
}
