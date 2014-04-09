package defpac;

public class MyWoodman implements Woodman {
	private String name;
	private Point location;
	private Point startLocation;
	private int lifes;
	
	MyWoodman (String m_name, Point start) {
		name = m_name;
		startLocation = location = start;
		lifes = 3;
	}
	/**
	 *  оличество жизней персонажа.
	 */
	public int GetLifeCount() {
		return lifes;
	}
	/**
	 * »м€ персонажа.
	 */
	public String GetName() {
		return name;
	}

	/**
	 * ”меньшить количество жизней.
	 * 
	 * @return успех уменьшени€ жизней.
	 */
	public boolean Kill() {
		lifes--;
		if (lifes < 0 ) return false;
		return true;
	}
	/**
	 * ƒобавить жизнь персонажу.
	 */
	public void AddLife() {
		lifes++;
	}
	/**
	 * ћестоположение персонажа на игровом поле.
	 */
	public Point GetLocation() {
		return location;
	}
	/**
	 * ”становить новое местоположение персонажа на игровом поле.
	 */
	public void SetLocation(Point new_location) {
		location = new_location;
	}
	/**
	 * ѕереместить игрока в стартовую точку
	 */
	public void MoveToStart() {
		location = startLocation;
	}
}