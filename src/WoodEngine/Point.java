package WoodEngine;

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
	
	public Point MoveTo(Direction direction) {
		switch(direction){
		case Up: return this.MoveUp();
		case Down: return this.MoveDown();
		case Left: return this.MoveLeft();
		case Right: return this.MoveRigth();
		default : return this;
		}
	}
	
	@Override
    public boolean equals(Object obj){
        if (this == obj)
            return true;
        if (!(obj instanceof Point))
            return false;
        Point point = (Point)obj;
        return ((x == point.getX()) && (y == point.getY()));
    }
	
	@Override
	public int hashCode(){
        return Integer.valueOf(x) ^ Integer.valueOf(y);
    }
	
	@Override
	public String toString(){
		return "(" + x + ", " + y + ")";
	}
	
}
