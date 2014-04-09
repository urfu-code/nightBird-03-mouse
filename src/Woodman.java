
public class Woodman implements IWoodman {
	protected final String name;
	private int lifeCount;
	private Point lctn;
	@SuppressWarnings("unused")
	private Point start;
	Point finish;
	
	Woodman(String name, Point start, Point finish) {
		this.name = name;
		this.start = this.lctn = start;
		this.finish = finish;
		this.lifeCount = 3;
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
		if (lifeCount >= 0)
			return true;
		return false;
	}

	@Override
	public void AddLife() {
		lifeCount++;
	}

	@Override
	public Point GetLocation() {
		return lctn;
	}

	@Override
	public void SetLocation(Point location) {
		this.lctn = location;
	}
	
	@Override
	public boolean equals(Object woodman) {

		if (woodman == null) {
			return false;
		}

		if (woodman.getClass() != Woodman.class) {
			return false;
		}
		
		Woodman wm = (Woodman) woodman;
		if (wm.GetName() != GetName()) {
			return false;
		}
		
		return true;
	}

}
