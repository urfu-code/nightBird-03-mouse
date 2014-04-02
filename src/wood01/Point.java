 	package wood01;
public class Point {
	private final int x;
	private final int y;
	private Point parrent;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Point(int x, int y,Point _parrent) {
		this.x = x;
		this.y = y;
		this.parrent = _parrent;
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Point getParrent() {
		return parrent;
	}
	
	public Point MoveUp() {
		return new Point(x, y - 1, this);
	}

	public Point MoveDown() {
		return new Point(x, y + 1, this);
	}

	public Point MoveLeft() {
		return new Point(x - 1, y, this);
	}

	public Point MoveRight() {
		return new Point(x + 1, y, this);
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
