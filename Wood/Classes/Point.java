package Classes;


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
	
	public Point sub(Point p) {
		return new Point(x-p.x, y - p.y);
	}
	
	public Point add(Point p) {
		return new Point(x+p.x, y+p.y);
	}
	public Point revers() {
		return new Point(-x,-y);
	}
	@Override
	public String toString() {
		return "("+x+";"+y+")";
	}
	@Override
	public boolean equals(Object obj) {
		if (obj.getClass()!=Point.class)
			return false;
		Point pnt = (Point)obj;
		return  ( (pnt.x==x) && (pnt.y==y) );
	}
	
	@Override
	public int hashCode() {
		return x*100+y;
	}
}
