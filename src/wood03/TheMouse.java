package wood03;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

import wood01.Action;
import wood01.Direction;
import wood01.Point;
import woodInterfaces.Mouse;

public class TheMouse implements Mouse {
	
	private Map<Point,Action>mouseWorld;
	private Point mouseLocation;
	private int mouseLifes;
	private Direction lastDirection;
	private int lifePointCounter;
	private boolean needLifes;
	private boolean isCountedPath;
	private int howManyLifesINeeded;
	private Stack<Direction> pathToLife;
	
	public TheMouse() {
		mouseWorld = new HashMap<Point,Action>();
		mouseLocation = new Point(0,0);
		mouseLifes = 3;
		lastDirection = null;
		lifePointCounter = 0;
		needLifes = false;
		isCountedPath = false;
		pathToLife = new Stack<Direction>();
	}
	
	@Override
	public Direction NextMove(Action action) throws Exception {
		if (lastDirection == null) {
			lastDirection = Direction.None;
			return lastDirection;
		}
		Point shamLocation = mouseLocation;
		Direction direction = lastDirection;
		shamLocation = getShamLocation(shamLocation,lastDirection);
		if (!mouseWorld.containsKey(shamLocation))	{
			mouseWorld.put(shamLocation, action);
		}
		direction = thinkAboutNextMove(action, shamLocation, direction);
		if (needLifes) {
			direction = findLifes(action, mouseLocation, direction);
		}
		if ((action == Action.Life)&&(!needLifes)) {
			if (mouseLifes < 4) {
				needLifes = true;
				lifePointCounter = 0;
				howManyLifesINeeded = 4 - mouseLifes;
				pathToLife.push(invertDirection(lastDirection));
				lastDirection = Direction.None;
				return lastDirection;
			}
		}

		if (!isNotablePoint(mouseLocation,direction)) {
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
		return mouseWorld.containsKey(getShamLocation(mouseLocation,direction));
	}

	private Direction findLifes(Action action, Point location,Direction direction) throws Exception {
		if (isCountedPath) {
			if (mouseWorld.get(mouseLocation) != Action.Life) {
				return pathToLife.pop();
			}
			else {
				if (lifePointCounter > 2 + howManyLifesINeeded) {
					needLifes = false;
					lifePointCounter = 0;
					isCountedPath = false;
					return invertDirection(pathToLife.pop());
				}
				else {
					lifePointCounter++;
					return Direction.None;
				}
			}
		}
		else {
			Queue<Point> searchLife = new LinkedList<Point>();
			int tempCounter = mouseLifes + 1;
			howManyLifesINeeded = 0;
			searchLife.offer(mouseLocation);
			boolean weHaveLife = false;
			Point shamLocation = mouseLocation;
			Point tempLocation;
			HashSet<Point> usedPoints = new HashSet<Point>();
			while (!searchLife.isEmpty()) {
				shamLocation = searchLife.poll();
				if (!usedPoints.add(shamLocation)) {
					continue;
				}
				if (mouseWorld.get(shamLocation) == Action.Life) {
					weHaveLife = true;
					break;
				}
				else if (mouseWorld.get(shamLocation) == Action.Dead) {
					tempCounter--;
					howManyLifesINeeded++;
				}
				//up
				tempLocation = getShamLocation(shamLocation, Direction.Up);
				if (mouseWorld.containsKey(tempLocation)) {
					if (mouseWorld.get(tempLocation) != Action.Fail) {
						searchLife.offer(tempLocation);
					}
				}
				//left
				tempLocation = getShamLocation(shamLocation, Direction.Left);
				if (mouseWorld.containsKey(tempLocation)) {
					if (mouseWorld.get(tempLocation) != Action.Fail) {
						searchLife.offer(tempLocation);
					}
				}
				//down
				tempLocation = getShamLocation(shamLocation, Direction.Down);
				if (mouseWorld.containsKey(tempLocation)) {
					if (mouseWorld.get(tempLocation) != Action.Fail) {
						searchLife.offer(tempLocation);
					}
				}
				//right
				tempLocation = getShamLocation(shamLocation, Direction.Right);
				if (mouseWorld.containsKey(tempLocation)) {
					if (mouseWorld.get(tempLocation) != Action.Fail) {
						searchLife.offer(tempLocation);
					}
				}
				
			}
			if ((tempCounter < 0)||(!weHaveLife)) {
				needLifes = false;
				return direction;
			}
			else {
				isCountedPath = true;
				writePath(shamLocation);
				return pathToLife.pop();
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

	private void writePath(Point location) {
		Point tempPoint;
		tempPoint = location.getParrent();
		//up
		if (location.getY() < tempPoint.getY()) {
			pathToLife.push(Direction.Up);
		}
		//left
		else if (location.getX() > tempPoint.getX()) {
			pathToLife.push(Direction.Right);
		}
		//down
		else if (location.getY() > tempPoint.getY()) {
			pathToLife.push(Direction.Down);
		}
		//right
		else {
			pathToLife.push(Direction.Left);
		}
		while (!location.equals(mouseLocation)) {
			tempPoint = location.getParrent();
			//up
			if (location.getY() < tempPoint.getY()) {
				pathToLife.push(Direction.Up);
			}
			//left
			else if (location.getX() > tempPoint.getX()) {
				pathToLife.push(Direction.Right);
			}
			//down
			else if (location.getY() > tempPoint.getY()) {
				pathToLife.push(Direction.Down);
			}
			//right
			else {
				pathToLife.push(Direction.Left);
			}
			location = tempPoint;
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
			nextDirection = moveLeftHand(direction);
			break;
		case Dead:
			mouseLocation = location;
			mouseLifes--;
			if (mouseLifes < 3) {
				needLifes = true;
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

