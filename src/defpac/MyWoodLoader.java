package defpac;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class MyWoodLoader implements WoodLoader {

	public Wood Load(InputStream stream) {
		InputStreamReader readerStream = new InputStreamReader(stream);
		BufferedReader reader = new BufferedReader(readerStream);
		LinkedList<char[]>tempWood = new LinkedList<char[]>();
		char[][] wood1 = null;
		try {
			String s;
			int length;
			int height = 0;
			
			if ((s = reader.readLine()) == null) {
				throw new IOException("Пустой лес");
			}
			length = s.length();
			while ( s != null) {
				if (s.length() != length) {
					throw new IOException("Неправильный лес: не совпадает ширина разных строк");
				}
				tempWood.add(s.toCharArray());
				height++;
				s = reader.readLine();
			}
			wood1 = new char[height][length];
			for (int j = 0; j < height; j++) {
				for (int i = 0; i < length; i++) {
					wood1[j][i] = (tempWood.getFirst())[i];
				}
				tempWood.removeFirst();
			}
			
		}
		catch(IOException e) {
			e.getMessage();
		}
		finally {
			try {
				reader.close();
				readerStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return new MyWood(wood1);
	}
}