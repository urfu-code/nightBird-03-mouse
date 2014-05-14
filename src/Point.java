public class Point {
	private final int x;
	private final int y;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Point MoveUp() {
		return new Point(x, y - 1);
	}

	public Point MoveDown() {
		return new Point(x, y + 1);
	}

	public Point MoveLeft() {
		return new Point(x - 1, y);
	}

	public Point MoveRigth() {
		return new Point(x + 1, y);
	}
	
	public boolean equals (Object ob) {
		if (ob instanceof Point) {
			Point p = (Point) ob; 
			return this.x == p.x && this.y == p.y;
		} else return false;
	}
	
	public int hashCode() {   
		return 50*x + 125*y + 33*(x+y);
	}
	
	public Point Move(Direction direction) {
		switch(direction){
		case Down: return MoveDown();
		case Left: return MoveLeft();
		case None: return this;
		case Right: return MoveRigth();
		default: return MoveUp();
		}
	}
}
