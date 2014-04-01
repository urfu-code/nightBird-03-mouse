 package wood01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import woodInterfaces.WoodLoader;

public class TheWoodLoader implements WoodLoader {
	
	protected Map<Character,Character> elements;
	
	public TheWoodLoader() {
		elements = new HashMap<Character,Character>();
		elements.put('┌', '1');
		elements.put('─', '1');
		elements.put('┬', '1');
		elements.put('┐', '1');
		elements.put('│', '1');
		elements.put('┼', '1');
		elements.put('┤', '1');
		elements.put('├', '1');
		elements.put('└', '1');
		elements.put('─', '1');
		elements.put('┴', '1');
		elements.put('┘', '1');
		elements.put('♥', 'L');
		elements.put(' ', '0');
		elements.put('□', '1');
		elements.put('ѻ', 'K');
		elements.put('1', '1');
		elements.put('0', '0');
		elements.put('K', 'K');
		elements.put('L', 'L');
	}

	
	@Override
	public TheWood Load(InputStream inStream) throws Exception {
		char[][] wood = makeWood(inStream);
		return new TheWood(wood);
	}
	
	@Override
	public PrintableTheWood Load(InputStream inStream, OutputStream outStream) throws Exception {
		char[][] wood = makeWood(inStream);
		return new PrintableTheWood(wood,outStream);
	}

	public char[][] makeWood(InputStream stream) throws Exception {
		BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
		String s;
		LinkedList<String>woodArrList = new LinkedList<String>();
		int x = 0;
		int y = 0;
		try {
			s = reader.readLine();
			y = s.length();
			while (s != null) {
				if (s.length() != y) {
					throw new IOException("непрямоугольный лес!");
				}
				woodArrList.add(s);
				s = reader.readLine();
				x++;
			}
			char[][] wood = new char[x][y];
			for (int i = 0; i < x; i++) {
				s = woodArrList.removeFirst();
				for (int j = 0; j < y; j++) {
					if (!elements.containsKey(s.charAt(j))) {
						throw new IOException("неизвестная клетка..");
					}
					wood[i][j] = elements.get(s.charAt(j));
				}
			}
			return  wood;
		} catch (IOException e) {
			System.out.println("с лесом беда...");
		}
		finally {
			reader.close();
		}
		throw new IOException("данные не получены");
	}

}
