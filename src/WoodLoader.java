import java.io.IOException;
import java.io.InputStream;

/**
 * Создатель леса
 */
public interface WoodLoader {
	/**
	 * Создает экземпляр леса по данным из потока.
	 * @param stream поток с информацией о лесе.
	 * @return Лес
	 * @throws IOException 
	 */
	MyWood Load(InputStream stream) throws IOException;
}
