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
		return new Point(this.x, this.y - 1);
	}

	public Point MoveDown() {
		return new Point(this.x, this.y + 1);
	}

	public Point MoveLeft() {
		return new Point(this.x - 1, this.y);
	}

	public Point MoveRight() {
		return new Point(this.x + 1, this.y);
	}
	
	@Override
	public boolean equals(Object point) {

		if (point == null) {
			return false;
		}

		if (point.getClass() != Point.class) {
			return false;
		}
		
		Point p = (Point) point;
		if (p.getX() != getX() || p.getY() != getY()) {
			return false;
		}
		
		return true;
	}
	
	@Override
	public String toString() {
		return "(" + Integer.toString(x) + ", " + Integer.toString(y) + ")";
	}
	
	@Override
	public int hashCode() {
		 final int prime = 31;
		 int result = 1;
		 result = prime * result + this.x;
		 result = prime * result + this.y;
		 return result;
	}

}
