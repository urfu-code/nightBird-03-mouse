import java.util.HashMap;
import java.util.Map;


public class MyMouse implements Mouse {

	private Map<Point,Action>memory_wood;
	private Point mouse_location;
	private int mouse_lifes;
	private Direction last_direction;

	public MyMouse() {
		memory_wood = new HashMap<Point,Action>();
		mouse_location = new Point(0,0);
		mouse_lifes = 3;
		last_direction = null;
	}
	
	
	@Override
	public Direction NextMove(Action action) throws Exception{
		Point location = mouse_location;
		Direction mouse_direction = last_direction;
		if (mouse_direction == null) {
			mouse_direction = Direction.Right;
			last_direction = mouse_direction;
			return mouse_direction;
		}
		// Сейчас не  используется, но потом переделаю алгоритм.
		if (!memory_wood.containsKey(mouse_location)) {
			memory_wood.put(location, action);
		}
		switch (action) {
		case Ok:
			mouse_location = location;
			if (last_direction == Direction.Right)
				mouse_direction = Direction.Up;
			if (last_direction == Direction.Down)
				mouse_direction = Direction.Right;
			if (last_direction == Direction.Left)
				mouse_direction = Direction.Down;
			if (last_direction == Direction.Up)
				mouse_direction = Direction.Left;
			break;
			
		case Fail:
			if (last_direction == Direction.Right)
				mouse_direction = Direction.Down;
			if (last_direction == Direction.Down)
				mouse_direction = Direction.Left;
			if (last_direction == Direction.Left)
				mouse_direction = Direction.Up;
			if (last_direction == Direction.Up)
				mouse_direction = Direction.Right;
			break;
			
		case Life:	
			mouse_location = location;
			mouse_lifes++;
			if (mouse_lifes < 3) {
				mouse_direction = Direction.None;
			}	else {
				if (last_direction == Direction.Right)
					mouse_direction = Direction.Up;
				if (last_direction == Direction.Down)
					mouse_direction = Direction.Right;
				if (last_direction == Direction.Left)
					mouse_direction = Direction.Down;
				if (last_direction == Direction.Up)
					mouse_direction = Direction.Left;
			}
			break;
			
		case Dead:
			mouse_location = location;
			mouse_lifes--;
			if (last_direction == Direction.Right)
				mouse_direction = Direction.Up;
			if (last_direction == Direction.Down)
				mouse_direction = Direction.Right;
			if (last_direction == Direction.Left)
				mouse_direction = Direction.Down;
			if (last_direction == Direction.Up)
				mouse_direction = Direction.Left;
			break;
		default:
			throw new Exception ("ErrorInAction");
		}
		
		last_direction = mouse_direction;
		return mouse_direction;
	}

}
