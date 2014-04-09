public interface IMouse {
	/**
	 * Решение о последующем ходе игрока, на основе результата предыдущего.
	 * 
	 * @param action результат предыдущего хода
	 * @return направление следующего хода
	 */
	Direction NextMove(Action action);
}
