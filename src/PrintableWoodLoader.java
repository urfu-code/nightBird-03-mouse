import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;


public class PrintableWoodLoader implements IWoodLoader {
	public PrintableWood Load(FileInputStream stream) throws IOException {
		HashMap<Point, Character> lab = new HashMap<Point, Character>();
		int length = 0;
		int width = 0;
		
		try {
			try {
				@SuppressWarnings("resource")
				Scanner sc = new Scanner(stream, "utf8");
				String labLine = "";
				int numberOfLine = 0;
				
				if (sc.hasNext()) {					
					labLine = sc.nextLine();
					length = labLine.length();
					LoadLine(lab, labLine, numberOfLine++);
				}
				while (sc.hasNext()) {
					labLine = sc.nextLine();
					if (labLine.length() != length)
						throw new RuntimeException("Лабиринт не прямоугольный!"); 

					LoadLine(lab, labLine, numberOfLine++);
				}
				
				length = labLine.length();
				width = numberOfLine;
			
			}
			finally {
				stream.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return new PrintableWood(lab, length, width, System.out);
		
	}
	
	private void LoadLine(HashMap<Point, Character> lab, String labLine, int numberOfLine) {
		for (int i = 0; i < labLine.length(); i++) {
			Character tempChar = labLine.charAt(i);
			lab.put(new Point(i, numberOfLine), associations(tempChar));
//			System.out.print(lab.get(new Point(i, numberOfLine)));
		}
//		System.out.print(System.getProperty("line.separator"));
	}
	
	private Character associations(char currentChar) {
		switch (currentChar) {
		case '1' : return '◘';
		case 'L' : return '♥';
		case 'K' : return '‡';
		default : return ' ';
		}		
	}
}
