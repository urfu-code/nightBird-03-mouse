import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;

import org.junit.Test;


public class PrintableWood_test {
	
	
	@Test
	public void testPrintWood2() throws Exception { 
		char[][] wood_map = new char[4][4];
		for (int i=0; i<4; i++) {
			wood_map[0][i]='1';
			wood_map[3][i]='1';
			wood_map[i][0]='1';
			wood_map[i][3]='1';
		}
		
		wood_map[1][1]='0';
		wood_map[1][2]='0';
		wood_map[2][1]='L';
		wood_map[2][2]='K';
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		PrintableWood w = new PrintableWood(wood_map, output);
		w.createWoodman("Armstrong", new Point (1,1), null);
		String out_wood = "▓▓▓▓\r\n▓✢♥▓\r\n▓░✡▓\r\n▓▓▓▓\r\n\r\n♥ - дополнительная жизнь\r\n✡ - капкан\r\n✢ - Armstrong (Количестро жизней:3)\r\n";
		assertEquals(out_wood, output.toString());
	}
	
	@Test
	public void testPrintWood3() throws Exception { 
		char[][] wood_map = new char[4][4];
		for (int i=0; i<4; i++) {
			wood_map[0][i]='1';
			wood_map[3][i]='1';
			wood_map[i][0]='1';
			wood_map[i][3]='1';
		}
		
		wood_map[1][1]='0';
		wood_map[1][2]='0';
		wood_map[2][1]='L';
		wood_map[2][2]='K';
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		PrintableWood w = new PrintableWood(wood_map, output);
		w.createWoodman("Gagarin", new Point (1,1), null);
		w.move("Gagarin", Direction.Right);
		//Выведет сначала createWoodman, а затем move
		String out_wood = "▓▓▓▓\r\n▓✢♥▓\r\n▓░✡▓\r\n▓▓▓▓\r\n\r\n♥ - дополнительная жизнь\r\n✡ - капкан\r\n✢ - Gagarin (Количестро жизней:3)\r\n▓▓▓▓\r\n▓░✢▓\r\n▓░✡▓\r\n▓▓▓▓\r\n\r\n♥ - дополнительная жизнь\r\n✡ - капкан\r\n✢ - Gagarin (Количестро жизней:4)\r\n";
		assertEquals(out_wood, output.toString());
	}
}