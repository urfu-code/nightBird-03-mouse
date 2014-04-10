package WoodEngine;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

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
	IWood LoadWood(InputStream stream) throws IOException;
	IWood LoadPrntbleWood(InputStream stream, OutputStream ostream) throws IOException;
}
