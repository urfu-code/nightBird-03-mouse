package defpac;

public interface Mau5 {
	/**
	 * ������� � ����������� ���� ������, �� ������ ���������� �����������.
	 * 
	 * @param action ��������� ����������� ����
	 * @return ����������� ���������� ����
	 */
	Direction NextMove(Action action);
}