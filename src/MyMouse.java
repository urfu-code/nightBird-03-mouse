import java.util.HashMap;
import java.util.Random;
import java.util.RandomAccess;


public class MyMouse implements Mouse {
	
	private int position_x;
	private int position_y;
	private Direction lastDirection;
	private HashMap<Point, Character> wood = new HashMap<Point, Character>();
	
	public MyMouse () {
		position_x = 0;
		position_y = 0;		
		lastDirection = null;
	}
	
	@Override
	public Direction NextMove(Action action) {
		if (lastDirection == null) {
			lastDirection = Direction.None;
			return lastDirection;
		}
		createWood(action);
		if (lastDirection == Direction.None) {
			lastDirection = Direction.Right;
			shiftPosition(lastDirection);
			return lastDirection;
		}
		return pathFinding();
	}

	private Direction pathFinding() {
		Point p_down = new Point (position_x, position_y + 1);
		Point p_up = new Point (position_x, position_y - 1);
		Point p_left = new Point (position_x - 1, position_y);
		Point p_right = new Point (position_x + 1, position_y);
		
		// 3 ����� �����
		if (wood.containsKey(p_left) && wood.containsKey(p_up) && wood.containsKey(p_right)) {
			if (wood.get(p_left) == '1' && wood.get(p_up) == '1' && wood.get(p_right) == '1') {
				lastDirection = Direction.Down;
				shiftPosition(lastDirection);
				return lastDirection;
			}
		}		
		if (wood.containsKey(p_up) && wood.containsKey(p_left) && wood.containsKey(p_down)) {
			if (wood.get(p_up) == '1' && wood.get(p_left) == '1' && wood.get(p_down) == '1') {
				lastDirection = Direction.Right;
				shiftPosition(lastDirection);
				return lastDirection;
			}
		}		
		if (wood.containsKey(p_left) && wood.containsKey(p_down) && wood.containsKey(p_right)) {
			if (wood.get(p_left) == '1' && wood.get(p_down) == '1' && wood.get(p_right) == '1') {
				lastDirection = Direction.Up;
				shiftPosition(lastDirection);
				return lastDirection;
			}
		}		
		if (wood.containsKey(p_down) && wood.containsKey(p_right) && wood.containsKey(p_up)) {
			if (wood.get(p_down) == '1' && wood.get(p_right) == '1' && wood.get(p_up) == '1') {
				lastDirection = Direction.Left;
				shiftPosition(lastDirection);
				return lastDirection;
			}
		}
		
		// 2 ����� ����� (�������� ����)
		if (wood.containsKey(p_right) && wood.containsKey(p_up)) {
			if (wood.get(p_right) == '1' && wood.get(p_up) == '1') {
				if (lastDirection == Direction.Right) {
					lastDirection = Direction.Down;
					shiftPosition(lastDirection);
					return lastDirection;
				} else {
					lastDirection = Direction.Left;
					shiftPosition(lastDirection);
					return lastDirection;
				}
			}
		}
		if (wood.containsKey(p_up) && wood.containsKey(p_left)) {
			if (wood.get(p_up) == '1' && wood.get(p_left) == '1') {
				if (lastDirection == Direction.Up) {
					lastDirection = Direction.Right;
					shiftPosition(lastDirection);
					return lastDirection;
				} else {
					lastDirection = Direction.Down;
					shiftPosition(lastDirection);
					return lastDirection;
				}
			}
		}
		if (wood.containsKey(p_left) && wood.containsKey(p_down)) {
			if (wood.get(p_left) == '1' && wood.get(p_down) == '1') {
				if (lastDirection == Direction.Left) {
					lastDirection = Direction.Up;
					shiftPosition(lastDirection);
					return lastDirection;
				} else {
					lastDirection = Direction.Right;
					shiftPosition(lastDirection);
					return lastDirection;
				}
			}
		}
		if (wood.containsKey(p_down) && wood.containsKey(p_right)) {
			if (wood.get(p_down) == '1' && wood.get(p_right) == '1') {
				if (lastDirection == Direction.Down) {
					lastDirection = Direction.Left;
					shiftPosition(lastDirection);
					return lastDirection;
				} else {
					lastDirection = Direction.Up;
					shiftPosition(lastDirection);
					return lastDirection;
				}
			}
		}
		
		//1 ����� �����
		if (wood.containsKey(p_up) && lastDirection != Direction.Right) {
			if (wood.get(p_up) == '1') {
				lastDirection = Direction.Left;
				shiftPosition(lastDirection);
				return lastDirection;
			}
		}
		if (wood.containsKey(p_left) && lastDirection != Direction.Up) {
			if (wood.get(p_left) == '1') {
				lastDirection = Direction.Down;
				shiftPosition(lastDirection);
				return lastDirection;
			}
		}
		if (wood.containsKey(p_down) && lastDirection != Direction.Left) {
			if (wood.get(p_down) == '1') {
				lastDirection = Direction.Right;
				shiftPosition(lastDirection);
				return lastDirection;
			}
		}
		if (wood.containsKey(p_right) && lastDirection != Direction.Down) {
			if (wood.get(p_right) == '1') {
				lastDirection = Direction.Up;
				shiftPosition(lastDirection);
				return lastDirection;
			}
		}
		
		//2 ����� ����� (�������������� ���� �����)
		if (wood.containsKey(p_right) && wood.containsKey(p_left)) {
			if (wood.get(p_right) == '1' && wood.get(p_left) == '1') {
				if (lastDirection == Direction.Down) {
					shiftPosition(lastDirection);
					return lastDirection;
				} else {
					shiftPosition(lastDirection);
					return lastDirection;
				}
			}
		}
		if (wood.containsKey(p_up) && wood.containsKey(p_down)) {
			if (wood.get(p_up) == '1' && wood.get(p_down) == '1') {
				if (lastDirection == Direction.Right) {
					shiftPosition(lastDirection);
					return lastDirection;
				} else {
					shiftPosition(lastDirection);
					return lastDirection;
				}
			}
		}
		
		if (lastDirection == Direction.Right) {
			lastDirection = Direction.Down;
			shiftPosition(lastDirection);
			return lastDirection;
		}
		if (lastDirection == Direction.Down) {
			lastDirection = Direction.Left;
			shiftPosition(lastDirection);
			return lastDirection;
		}
		if (lastDirection == Direction.Left) {
			lastDirection = Direction.Up;
			shiftPosition(lastDirection);
			return lastDirection;
		}
		if (lastDirection == Direction.Up) {
			lastDirection = Direction.Right;
			shiftPosition(lastDirection);
			return lastDirection;
		}
		return null;
	}
	
	private void shiftPosition (Direction direction) {
		switch (direction) {
		case Down: position_y ++;
		break;
		case Left: position_x --;
		break;
		case Right: position_x ++;
		break;
		case Up: position_y --;
		break;
		}
	}

	private void createWood(Action action) {
		switch (action) {
		case Ok: wood.put(new Point (position_x, position_y), '0');
		break;		
		case Fail: {
			wood.put(new Point (position_x, position_y), '1');
			switch (lastDirection) {
			case Down: shiftPosition(Direction.Up);
			break;
			case Left: shiftPosition(Direction.Right);
			break;
			case Right: shiftPosition(Direction.Left);
			break;
			case Up: shiftPosition(Direction.Down);
			break;
			}
		}
		break;
		case Dead: wood.put(new Point (position_x, position_y), 'K');
		break;
		case Life: wood.put(new Point (position_x, position_y), 'L');
		break;
		default: break;
		}
	}

}
