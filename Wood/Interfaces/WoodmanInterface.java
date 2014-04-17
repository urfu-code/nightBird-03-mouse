package Interfaces;

import Classes.Point;


/**
 * Лесной житель
 */
public interface WoodmanInterface {
	/**
	 * Количество жизней персонажа.
	 */
	int GetLifeCount();
	
	/**
	 * Имя персонажа.
	 */
	String GetName();

	/**
	 * Уменьшить количество жизней.
	 * 
	 * @return true - если остался жив.
	 */
	boolean Kill();

	/**
	 * Добавить жизнь персонажу.
	 */
	void AddLife();
	
	/**
	 * Местоположение персонажа на игровом поле.
	 */
	Point GetLocation();
	
	/**
	 * Установить новое местоположение персонажа на игровом поле.
	 */
	void SetLocation(Point location);
	
	/**
	 * Переместить игрока в стартовую точку
	 */
	public void MoveToStart();
}