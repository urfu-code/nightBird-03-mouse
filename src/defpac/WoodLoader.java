package defpac;
import java.io.InputStream;

/**
 * ��������� ����
 */
public interface WoodLoader {
	/**
	 * ������� ��������� ���� �� ������ �� ������.
	 * @param stream ����� � ����������� � ����.
	 * @return ���
	 */
	Wood Load(InputStream stream);
}