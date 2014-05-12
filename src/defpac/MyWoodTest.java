package defpac;

import java.io.IOException;

import junit.framework.TestCase;

public class MyWoodTest extends TestCase {

	public void testCreateWoodman() throws IOException {
		char[][] wood = {{'1','1','1','1'},{'1','0','K','1'},{'1','0','L','1'},{'1','1','1','1'}};
		MyWood forest = new MyWood (wood);
		forest.createWoodman("aaa", new Point(1,1), new Point(2,1));
		assertEquals(forest.move("aaa",Direction.None),Action.Ok);
	}

	public void testMove() throws IOException {
		char[][] forest = {{'1','1','1','1'},{'1','0','K','1'},{'1','0','L','1'},{'1','0','0','1'},{'1','1','1','1'}};
		MyWood wood = new MyWood (forest);
		wood.createWoodman("aaa", new Point(1,1), new Point(1,3));
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
	}
}