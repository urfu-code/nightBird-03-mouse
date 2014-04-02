package woodInterfaces;

import wood01.Action;
import wood01.Direction;

public interface Mouse {
	/**
	 * Решение о последующем ходе игрока, на основе результата предыдущего.
	 * 
	 * @param action результат предыдущего хода
	 * @return направление следующего хода
	 * @throws Exception 
	 */
	Direction NextMove(Action action) throws Exception;
}
