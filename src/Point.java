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
	
	@Override
	public boolean equals (Object point) {
		if ((point==null) || (point.getClass()!=Point.class)) {
			return false;
		}
		Point p1 = (Point)point;
		if ((this.getX() == p1.getX()) && (this.getY() == p1.getY())){
						return true;
		}
			return false;
	}
	
}

