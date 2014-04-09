
public class MyWoodman implements Woodman {

	private int m_life_count;
	private static Point m_start;
	private Point m_location;
	private static String m_name;
	private Point m_finish;
	
	public MyWoodman(String name, Point start, Point finish) {
		m_life_count=3;
		m_start=m_location=start;
		m_finish=finish;
		m_name=name;
	}
	
    @Override
	public int GetLifeCount() {
		return m_life_count;
	}

    @Override
	public String GetName() {
		return m_name;
	}

    @Override
	public boolean Kill() {
		m_life_count=m_life_count-1;
		if (m_life_count>-1)
			return true;
		else
			return false;
	}

    @Override
	public void AddLife() {
		m_life_count=m_life_count+1;
	}

    @Override
	public Point GetLocation() {
		return m_location;
	}

    @Override
	public void SetLocation(Point location) {
		m_location=location;
	}

    @Override
	public void MoveToStart() {
		m_location=m_start;
	}

	public Point GetFinish() {
		return m_finish;
	}

}
