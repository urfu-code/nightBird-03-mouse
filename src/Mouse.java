
public interface Mouse {
	/**
	 * ������� � ����������� ���� ������, �� ������ ���������� �����������.
	 * 
	 * @param action ��������� ����������� ����
	 * @return ����������� ���������� ����
	 */
	Direction NextMove(Action action);
}
