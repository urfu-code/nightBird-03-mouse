package wood03;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import wood01.Action;
import wood01.Direction;
import wood01.Point;
import woodInterfaces.Mouse;

public class TheMouse implements Mouse {
	
	private Map<Point,Action>mouseWorld;
	private Stack<Point>mouseLifesTrapsMap;
	private Stack<Direction>mouseTrail;
	private Point mouseLocation;
	private int mouseLifes;
	private Direction lastDirection;
	private int lifePointCounter;
	private boolean needLifes;
	private boolean isCountedPath;
	int howManyLifesINeeded;
	
	public TheMouse() {
		mouseWorld = new HashMap<Point,Action>();
		mouseLocation = new Point(0,0);
		mouseLifes = 3;
		lastDirection = Direction.None;
		mouseLifesTrapsMap = new Stack<Point>();
		mouseTrail = new Stack<Direction>();
		lifePointCounter = 0;
		needLifes = false;
		isCountedPath = false;
	}
	
	@Override
	public Direction NextMove(Action action) throws Exception {
		Point shamLocation = mouseLocation;
		Direction direction = lastDirection;
		shamLocation = getShamLocation(shamLocation,lastDirection);
		if (!mouseWorld.containsKey(shamLocation))	{
			mouseWorld.put(shamLocation, action);
		}
		if ((action != Action.Fail)&&(!needLifes)) {
			mouseTrail.push(lastDirection);
		}
		direction = thinkAboutNextMove(action, shamLocation, direction);
		if (needLifes) {
			direction = findLifes(action, shamLocation, direction);
		}
		if (!isNotablePoint(shamLocation,direction)) {
			lastDirection = direction;
			return lastDirection;
		}
		else {
			direction = thinkAboutExisting(direction);
			lastDirection = direction;
			return lastDirection;
		}
	}
	
	private Point getShamLocation(Point shamLocation, Direction direction) {
		Point tempLocation;
		switch (direction) {
		case Up:
			tempLocation = shamLocation.MoveUp();
			break;
		case Left:
			tempLocation = shamLocation.MoveLeft();
			break;
		case Down:
			tempLocation = shamLocation.MoveDown();
			break;
		case Right:
			tempLocation = shamLocation.MoveRight();
			break;
		default:
			tempLocation = shamLocation;
			break;
		}
		return tempLocation;
	}

	private boolean isNotablePoint(Point location,Direction direction) {
		return mouseWorld.containsKey(getShamLocation(location,direction));
	}

	private Direction findLifes(Action action, Point location,Direction direction) throws Exception {
		if (isCountedPath) {
			if (mouseWorld.get(mouseLocation) != Action.Life) {
				if (mouseWorld.get(mouseLocation) == Action.Dead) {
					mouseLifesTrapsMap.pop();
				}
				return invertDirection(mouseTrail.pop());
			}
			else {
				if (lifePointCounter > 2 + howManyLifesINeeded) {
					needLifes = false;
					lifePointCounter = 0;
					isCountedPath = false;
					mouseLifes--;
					return thinkAboutNextMove(action, location, direction);
				}
				else {
					lifePointCounter++;
					return Direction.None;
				}
			}
		}
		else {
			Stack<Point>tempStack = new Stack<Point>();
			int tempCounter = mouseLifes;
			howManyLifesINeeded = 0;
			boolean weHaveLifes = false;
			while (!mouseLifesTrapsMap.isEmpty()) {
				tempStack.push(mouseLifesTrapsMap.pop());
				if (mouseWorld.get(tempStack.peek()) == Action.Life) {
					weHaveLifes = true;
					break;
				}
				else {
					tempCounter--;
					howManyLifesINeeded++;
				}
				
			}
			if ((tempCounter < 0)||(!weHaveLifes)) {
				needLifes = false;
				return direction;
			}
			else {
				isCountedPath = true;
				while (!tempStack.isEmpty()) {
					mouseLifesTrapsMap.push(tempStack.pop());
				}
				return invertDirection(mouseTrail.pop());
			}
		}
	}

	private Direction invertDirection(Direction direction) {
		switch (direction) {
		case Up:
			return Direction.Down;
		case Down:
			return Direction.Up;
		case Left:
			return Direction.Right;
		case Right:
			return Direction.Left;
		default:
			return Direction.None;
		}
	}

	private Direction thinkAboutNextMove(Action action, Point location, Direction direction) throws Exception {
		Direction nextDirection;
		switch (action) {
		case Ok:
			nextDirection = moveLeftHand(direction);
			mouseLocation = location;
			break;
		case Fail:
			if (mouseWorld.get(mouseLocation) == Action.Life) {
				mouseLifes++;
			}
			else if (mouseWorld.get(mouseLocation) == Action.Dead) {
				mouseLifes--;
				if (mouseLifes < 3) {
					needLifes = true;
				}
			}
			nextDirection = moveRightHand(direction);
			break;
		case Life:
			mouseLocation = location;
			mouseLifes++;
			if (lastDirection != Direction.None) {
			 mouseLifesTrapsMap.push(location);
			}
			nextDirection = moveLeftHand(direction);
			break;
		case Dead:
			mouseLocation = location;
			mouseLifes--;
			if (mouseLifes < 3) {
				needLifes = true;
			}
			else {
				mouseLifesTrapsMap.push(mouseLocation);
			}
			nextDirection = moveLeftHand(direction);
			break;
		default:
			throw new Exception("Нежданный экшн!");
		}
		return nextDirection;
	}

	private Direction moveRightHand(Direction direction) {
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
			return Direction.Down;
		}
	}

	private Direction moveLeftHand(Direction direction) {
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
			return Direction.Down;
		}
	}

	private Direction thinkAboutExisting(Direction direction) throws Exception {
		switch (mouseWorld.get(getShamLocation(mouseLocation,direction))) {
		case Ok:
			return direction;
		case Fail:
			if (!mouseWorld.containsKey(getShamLocation(mouseLocation, moveRightHand(direction)))) {
				return moveRightHand(direction);
			}
			else {
				if (mouseWorld.get(getShamLocation(mouseLocation, moveRightHand(direction))) == Action.Fail) {
					direction = moveRightHand(direction);
					return moveRightHand(direction);
				}
				return moveRightHand(direction);
			}
		case Life:
			return direction;
		case Dead:
			return direction;
		default:
			return direction;
		}
	}
}

