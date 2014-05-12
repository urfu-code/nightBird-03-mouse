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
		wood.createWoodman("losyash", new Point(1,3), new Point(1,2));
		assertEquals(wood.move("aaa",Direction.Down),Action.Ok);
		assertEquals(wood.move("aaa",Direction.Right),Action.Life);
		assertEquals(wood.move("aaa",Direction.None),Action.Life);
		assertEquals(wood.move("aaa",Direction.Right),Action.Fail);
		assertEquals(wood.move("aaa",Direction.Up),Action.Dead);
		assertEquals(wood.move("aaa",Direction.None),Action.Dead);
		assertEquals(wood.move("aaa",Direction.Up),Action.Fail);
		assertEquals(wood.move("aaa",Direction.Up),Action.Fail);
		assertEquals(wood.move("aaa",Direction.Up),Action.Fail);
		assertEquals(wood.move("aaa",Direction.Up),Action.Fail);
		assertEquals(wood.move("aaa",Direction.Up),Action.Fail);
		assertEquals(wood.move("aaa",Direction.Up),Action.WoodmanNotFound);
		assertEquals(wood.move("lol",Direction.Left),Action.WoodmanNotFound);
		wood.createWoodman("aaa", new Point(1,1), new Point(1,2));
		assertEquals(wood.move("aaa",Direction.Down),Action.Finish);
		wood.createWoodman("nyash", new Point(1,1), new Point(1,3));
		assertEquals(wood.move("nyash",Direction.Right),Action.Dead);
		assertEquals(wood.move("nyash",Direction.Right),Action.Fail);
		assertEquals(wood.move("nyash",Direction.Right),Action.Fail);
		assertEquals(wood.move("nyash",Direction.Right),Action.Fail);
		wood.createWoodman("myash", new Point(1,1), new Point(1,3));		
	}

	public void testPrintWood() throws IOException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		char[][] forest = {{'1','1','1','1','1','1','1'},{'1','1','1','K','L','1','1'},{'1','1','0','1','0','1','1'},{'1','0','A','0','1','1','1'},{'1','1','1','1','1','1','1'}};
		MyPrintableWood wood = new MyPrintableWood (forest,out);
		wood.createWoodman("aaa", new Point(2,3), new Point(4,2));
		String string = new String("╔╦╦══╦╗\n╠╬╝†♥╠╣\n╠╝ ╬ ╠╣\n║ A ╔╬╣\n╚╩══╩╩╝\n\n♥ - life\n† - death\nA - aaa , lifes: 3");
		byte[] s_w = out.toByteArray();
		char[] bytes = new char[string.length()];
		for (int Idx = 0; Idx < string.length(); Idx++)
			bytes[Idx] = string.charAt(Idx);
		for (int Idx = 0; Idx < string.length(); Idx++)
			assert (s_w[Idx] == (byte) bytes[Idx]);
	}
}