/**
 * ������ ������
 */
public interface Woodman {
	/**
	 * ���������� ������ ���������.
	 */
	int GetLifeCount();
	
	/**
	 * ��� ���������.
	 */
	String GetName();

	/**
	 * ��������� ���������� ������.
	 * 
	 * @return ����� ���������� ������.
	 */
	boolean Kill();

	/**
	 * �������� ����� ���������.
	 */
	void AddLife();
	
	/**
	 * �������������� ��������� �� ������� ����.
	 */
	Point GetLocation();
	
	/**
	 * ���������� ����� �������������� ��������� �� ������� ����.
	 */
	void SetLocation(Point location);
	
	/**
	 * ����������� ������ � ��������� �����
	 */
	public void MoveToStart();
}