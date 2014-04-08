
public interface Wood {
	/**
	 * Создает нового лесного жителя.
	 * 
	 * @param name имя лесного жителя
	 * @param start место появления
	 * @throws Exception 
	 */
	void createWoodman(String name, Point start, Point finish) throws Exception;
	
	/**
	 * Перемещает лесного жителя.
	 * 
	 * @param name имя лесного жителя
	 * @param direction направление перемещения
	 * @return результат перемещения
	 * @throws Exception 
	 * @throws NullPointerException 
	 */
	Action move(String name, Direction direction) throws NullPointerException, Exception;
}
