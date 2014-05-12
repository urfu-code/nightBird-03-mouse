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
	 * ���������� ������ ���������.
	 */
	public int GetLifeCount() {
		return lifes;
	}
	/**
	 * ��� ���������.
	 */
	public String GetName() {
		return name;
	}

	/**
	 * ��������� ���������� ������.
	 * 
	 * @return ����� ���������� ������.
	 */
	public boolean Kill() {
		lifes--;
		if (lifes < 0 ) return false;
		return true;
	}
	/**
	 * �������� ����� ���������.
	 */
	public void AddLife() {
		lifes++;
	}
	/**
	 * �������������� ��������� �� ������� ����.
	 */
	public Point GetLocation() {
		return location;
	}
	/**
	 * ���������� ����� �������������� ��������� �� ������� ����.
	 */
	public void SetLocation(Point new_location) {
		location = new_location;
	}
	/**
	 * ����������� ������ � ��������� �����
	 */
	public void MoveToStart() {
		location = startLocation;
	}
}