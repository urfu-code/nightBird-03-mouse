package woodInterfaces;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import wood01.PrintableTheWood;
import wood01.TheWood;

/**
 * Создатель леса
 */
public interface WoodLoader {
	/**
	 * Создает экземпляр леса по данным из потока.
	 * @param stream поток с информацией о лесе.
	 * @return Лес
	 * @throws IOException 
	 * @throws Exception 
	 */
	TheWood Load(InputStream stream) throws IOException, Exception;
	PrintableTheWood Load(InputStream inStream, OutputStream outStream) throws Exception;
	
}
