import java.io.IOException;
import java.io.InputStream;

/**
 * ��������� ����
 */
public interface WoodLoader {
	/**
	 * ������� ��������� ���� �� ������ �� ������.
	 * @param stream ����� � ����������� � ����.
	 * @return ���
	 * @throws IOException 
	 */
	MyWood Load(InputStream stream) throws IOException;
}
