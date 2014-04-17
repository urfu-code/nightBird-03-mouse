package Classes;

import Interfaces.WoodmanInterface;

public class Woodman implements WoodmanInterface{
	private Point m_startPoint;
	private Point m_location;
	private int m_lifes;
	private String m_name;

	public Woodman(Point startPoint, String name) {
		m_startPoint = startPoint;
		m_location = startPoint;
		m_lifes = 3;
		m_name = name;
	}
	public int GetLifeCount() {
		return m_lifes;
	}

	public String GetName() {
		return m_name;
	}

	public boolean Kill() {
		m_lifes--;
		return m_lifes>=0;
	}

	public void AddLife() {
		m_lifes++;
	}

	public Point GetLocation() {
		return m_location;
	}

	public void SetLocation(Point location) {
		m_location = location;
		
	}

	public void MoveToStart() {
		m_location=m_startPoint;
		
	}
}
