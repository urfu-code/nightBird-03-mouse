 	package wood01;
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

	public Point MoveRight() {
		return new Point(x + 1, y);
	}
	
	public boolean equals(Object obj) {
		if (!(obj instanceof Point)) {
			return false;
		}
		return (this.getX() == ((Point) obj).getX())&&(this.getY() == ((Point) obj).getY());		
	}
	
	@Override
	public int hashCode() {
		return  (x+" "+y).hashCode();
	}
}
