import java.util.HashMap;
import java.util.Map;

public class MyMouse implements Mouse {

	private int mouse_life_count;
	private Point mouse_location;
	Map <String,Character> mousesWorld=new HashMap<String,Character>();
	private Direction last_direction;

	int x=0;
	int y=0;
	private String mouse_name; 

	public MyMouse(String name) {
		mouse_name=name;
		mouse_life_count=3;
		mouse_location = new Point(0,0);
		last_direction = null;
		x=0;
		y=0;
	}

	public boolean Kill() {
		mouse_life_count = mouse_life_count-1;
		if (mouse_life_count > -1)
			return true;
		else
			return false;
	}

	public void AddMouseLife() {
		mouse_life_count = mouse_life_count+1;
	}
	
	
	@Override
	public Direction NextMove(Action action) throws Exception {

		Point location = mouse_location;
		Direction mouse_direction = last_direction;
		if (mouse_direction == null) {
			mouse_direction = Direction.Right;
			last_direction = mouse_direction;
			return mouse_direction;
		}

		switch (action) {
		case Ok:
			mouse_location = location;
			switch (last_direction) {
			case Up: {
				mouse_direction = Direction.Left;
			}
			break;
			case Down: {
				mouse_direction = Direction.Right;
			}
			break;
			case Left: {
				mouse_direction = Direction.Down;
			}
			break;
			case Right: {
				mouse_direction = Direction.Up;
			}
			break;
			}
			break;
		case Fail:
			switch (last_direction) {
			case Up: {
				mouse_direction = Direction.Right;
			}
			break;
			case Down: {
				mouse_direction = Direction.Left;
			}
			break;
			case Left: {
				mouse_direction = Direction.Up;
			}
			break;
			case Right: {
				mouse_direction = Direction.Down;
			}
			break;	
			}
			break;
		case Life:	
			mouse_location = location;
			AddMouseLife();
			switch (last_direction) {
			case Up: {
				mouse_direction = Direction.Left;
			}
			break;
			case Down: {
				mouse_direction = Direction.Right;
			}
			break;
			case Left: {
				mouse_direction = Direction.Down;
			}
			break;
			case Right: {
				mouse_direction = Direction.Up;
			}
			break;	

			}
			break;

		case Dead:
			mouse_location = location;
			Kill();
			switch (last_direction) {
			case Up: {
				mouse_direction = Direction.Left;
			}
			break;
			case Down: {
				mouse_direction = Direction.Right;
			}
			break;
			case Left: {
				mouse_direction = Direction.Down;
			}
			break;
			case Right: {
				mouse_direction = Direction.Up;
			}
			break;	

			}

		}

		last_direction = mouse_direction;
		return mouse_direction;
	}

	public String GetName() {
		return mouse_name;
	}

	}
