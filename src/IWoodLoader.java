import java.io.FileInputStream;
import java.io.IOException;


/**
 * Создатель леса
 */
public interface IWoodLoader {
	/**
	 * Создает экземпляр леса по данным из потока.
	 * @param stream поток с информацией о лесе.
	 * @return Лес
	 * @throws IOException 
	 */
	Wood Load(FileInputStream stream) throws IOException;
}
