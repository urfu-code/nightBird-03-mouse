package Interfaces;

import Enums.Action;
import Enums.Direction;

public interface Mouse {
	/**
	 * Решение о последующем ходе игрока, на основе результата предыдущего.
	 * 
	 * @param action результат предыдущего хода
	 * @return направление следующего хода
	 */
	Direction NextMove(Action action);
}
