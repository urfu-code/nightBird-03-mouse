package Bot;

import java.io.IOException;

import WoodEngine.Action;
import WoodEngine.Direction;

public interface IMouse {
	/**
	 * Решение о последующем ходе игрока, на основе результата предыдущего.
	 * 
	 * @param action результат предыдущего хода
	 * @return направление следующего хода
	 * @throws IOException 
	 * @throws Exception 
	 */
	Direction NextMove(Action action) throws IOException, Exception;
}
