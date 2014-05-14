
public class MyWoodman implements Woodman {
	private int m_lifeCount = 3;
	private String m_name;
	private Point m_location;
	private Point m_start;
	private Point m_finish;

	public MyWoodman(String name, Point start, Point finish) {
		m_name = name;
		m_location = m_start = start;
		m_finish = finish;
	}

	public int GetLifeCount() {
		return m_lifeCount;
	}

	public String GetName() {
		return m_name;
	}

	public boolean Kill() {
		if (m_lifeCount  <= 0) {
			return false;
		} else {
			m_lifeCount -= 1;
			return true;
		}
	}

	public void AddLife() {
		m_lifeCount += 1;
	}

	public Point GetLocation() {
		return m_location;
	}
	
	public Point GetFinish() {
		return m_finish;
	}

	public void SetLocation(Point location) {
		m_location = location;
	}

	public void MoveToStart() {
		m_location = m_start;
	}

}
