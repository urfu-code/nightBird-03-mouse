package defpac;

import java.io.IOException;



public interface Wood {
	/**
	 * ������� ������ ������� ������.
	 * 
	 * @param name ��� ������� ������
	 * @param start ����� ���������
	 * @throws IOException 
	 */
	void createWoodman(String name, Point start, Point finish) throws IOException;

	/**
	 * ���������� ������� ������.
	 * 
	 * @param name ��� ������� ������
	 * @param direction ����������� �����������
	 * @return ��������� �����������
	 * @throws IOException 
	 */
	Action move(String name, Direction direction) throws IOException;
}