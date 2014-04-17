package Interfaces;

import java.io.InputStream;

import Exceptions.EmptyFileException;
import Exceptions.InvalidFileException;

/**
 * Создатель леса
 */
public interface WoodLoaderInterface {
	/**
	 * Создает экземпляр леса по данным из потока.
	 * @param stream поток с информацией о лесе.
	 * @return Лес
	 * @exception EmptyFileException - если открываемый файл пустой
	 * @exception InvalidFileException - если в файле не таблица
	 */
	
	public WoodInterface Load(InputStream stream) throws EmptyFileException, InvalidFileException;
}
