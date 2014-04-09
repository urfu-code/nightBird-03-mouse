package defpac;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import junit.framework.TestCase;

public class MyPrintableWoodTest extends TestCase {

	public void testCreateWoodman() throws IOException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		char[][] wood = {{'1','1','1','1'},{'1','0','K','1'},{'1','0','L','1'},{'1','1','1','1'}};
		MyPrintableWood forest = new MyPrintableWood (wood, out);
		forest.createWoodman("aaa", new Point(1,1), new Point(2,1));
		assertEquals(forest.move("aaa",Direction.None),Action.Ok);
		forest.printWood();
	}

	public void testMove() throws IOException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		char[][] forest = {{'1','1','1','1'},{'1','0','K','1'},{'1','0','L','1'},{'1','0','1','1'},{'1','1','1','1'}};
		MyPrintableWood wood = new MyPrintableWood (forest, out);
		wood.createWoodman("aaa", new Point(1,1), new Point(1,3));
		assertEquals(wood.move("aaa",Direction.Down),Action.Ok);
		assertEquals(wood.move("aaa",Direction.Right),Action.Life);
		assertEquals(wood.move("aaa",Direction.Up),Action.Dead);
		assertEquals(wood.move("aaa",Direction.None),Action.Dead);
		assertEquals(wood.move("aaa",Direction.Left),Action.Ok);
		assertEquals(wood.move("lol",Direction.Left),Action.WoodmanNotFound);
		assertEquals(wood.move("aaa",Direction.Right),Action.Dead);
		assertEquals(wood.move("aaa",Direction.None),Action.WoodmanNotFound);
		wood.createWoodman("aaa", new Point(1,2), new Point(1,3));
		assertEquals(wood.move("aaa",Direction.Down),Action.Finish);
	}

	public void testPrintWood() throws IOException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		//ByteArrayOutputStream stream = new ByteArrayOutputStream();
		//stream.write((╔══╗\n║A†║\n║ ♥║\n╚══╝\n\n♥ - life\n† - death\nA - aaa , lifes: 3").getBytes());
		char[][] forest = {{'1','1','1','1'},{'1','0','K','1'},{'1','0','L','1'},{'1','1','1','1'}};
		MyPrintableWood wood = new MyPrintableWood (forest,out);
		wood.createWoodman("aaa", new Point(1,1), new Point(2,1));
		String string = new String("╔══╗\n║A†║\n║ ♥║\n╚══╝\n\n♥ - life\n† - death\nA - aaa , lifes: 3");
		byte[] s_w = out.toByteArray();
		char[] bytes = new char[string.length()];
		for (int Idx = 0; Idx < string.length(); Idx++)
			bytes[Idx] = string.charAt(Idx);
		for (int Idx = 0; Idx < string.length(); Idx++)
			assert (s_w[Idx] == (byte) bytes[Idx]);
		//assertEquals(out,stream);
	}
}