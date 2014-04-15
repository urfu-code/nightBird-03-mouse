package mouse;

import wood.Action;
import wood.Direction;

public interface Mouse {
	/**
	 * Решение о последующем ходе игрока, на основе результата предыдущего.
	 * 
	 * @param action результат предыдущего хода
	 * @return направление следующего хода
	 */
	Direction NextMove(Action action);
}
