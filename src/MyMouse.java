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
		// Сейчас не  используется
		if (!memory_wood.containsKey(mouse_location)) {
			memory_wood.put(location, action);
		}
		
		switch (action) {
		case Ok:
			mouse_location = location;
			mouse_direction = turn_left(last_direction);
			last_direction = mouse_direction;
			break;
			
		case Fail:
			mouse_direction = turn_right(last_direction);
			last_direction = mouse_direction;
			break;
			
		case Life:	
			mouse_location = location;
			mouse_lifes++;
			if (mouse_lifes < 5) {
				mouse_direction = Direction.None;
			}	else {
				mouse_direction = turn_left(last_direction);
				last_direction = mouse_direction;
			}
			break;
			
		case Dead:
			mouse_location = location;
			mouse_lifes--;
			mouse_direction = turn_left(last_direction);
			break;
		default:
			throw new Exception ("ErrorInAction");
		}
		
		return mouse_direction;
	}
	
	
	private Direction turn_right(Direction direction) {
		switch (direction) {
		case Up:
			return Direction.Right;
		case Left:
			return Direction.Up;
		case Down:
			return Direction.Left;
		case Right:
			return Direction.Down;
		default:
			return Direction.Right;
		}
	}

	private Direction turn_left(Direction direction) {
		switch (direction) {
		case Up:
			return Direction.Left;
		case Left:
			return Direction.Down;
		case Down:
			return Direction.Right;
		case Right:
			return Direction.Up;
		default:
			return Direction.Left;
		}
	}
}
