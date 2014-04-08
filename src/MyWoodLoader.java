import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.LinkedList;


public class MyWoodLoader implements WoodLoader {
	
	public MyWood Load (InputStream stream) throws Exception {
		
		LinkedList<char[]> list = new LinkedList<char[]>();
		BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
		String ln = null; 
		try { while ((ln = reader.readLine()) != null) {
			list.add(ln.toCharArray());
			}
		} catch (IOException e) { 
			e.printStackTrace();
			}
		char [][] m_wood = (char [][]) list.toArray(new char[list.get(0).length][list.size()]);
		for (int i = 1; i < m_wood.length; i++) {
			if (list.get(i).length != list.get(0).length) {
				throw new Exception("WoodIsBad");
			}
		}
		char [][] wood = new char[list.size()][list.get(0).length];
		for (int i = 0; i < m_wood.length; i++) {
			for (int j = 0; j < m_wood[0].length; j++) {
				wood [i][j] = m_wood[j][i];
			}
		}
		
		MyWood my_wood = new MyWood(wood);
		return my_wood;
	}

public PrintableWood Load (InputStream in_s, OutputStream out_s) throws Exception {
		
		LinkedList<char[]> list = new LinkedList<char[]>();
		BufferedReader reader = new BufferedReader(new InputStreamReader(in_s));
		String ln = null; 
		try { while ((ln = reader.readLine()) != null) {
			list.add(ln.toCharArray());
			}
		} catch (IOException e) { 
			e.printStackTrace();
			}
		char [][] m_wood = (char [][]) list.toArray(new char[list.get(0).length][list.size()]);
		for (int i = 1; i < m_wood.length; i++) {
			if (list.get(i).length != list.get(0).length) {
				throw new Exception("WoodIsBad");
			}
		}
		char [][] wood = new char[list.size()][list.get(0).length];
		for (int i = 0; i < m_wood.length; i++) {
			for (int j = 0; j < m_wood[0].length; j++) {
				wood [i][j] = m_wood[j][i];
			}
		}
		
		PrintableWood my_wood = new PrintableWood(wood, out_s);
		return my_wood;
	}
}
