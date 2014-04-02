package wood01;

import woodInterfaces.Woodman;

public class TheWoodman implements Woodman {
	private int lifeCount;
	private Point position;
	private Point startPosition;
	private String name;

	public TheWoodman(String _name, Point _position) {
		startPosition = position = _position;
		name = _name;
		lifeCount = 3;
	}
	
	@Override
	public int GetLifeCount() {
		return lifeCount;
	}

	@Override
	public String GetName() {
		return name;
	}

	@Override
	public boolean Kill() {
		lifeCount--;
		if (lifeCount >= 0) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public void AddLife() {
		lifeCount++;
	}

	@Override
	public Point GetLocation() {
		return position;
	}

	@Override
	public void SetLocation(Point location) {
		position = location;
	}

	@Override
	public void MoveToStart() {
		position = startPosition;
	}

}
