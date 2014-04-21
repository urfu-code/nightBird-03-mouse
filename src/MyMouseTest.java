import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.junit.Test;


public class MyMouseTest {
	@Test
	public void testNextMoveToFinish() throws Exception {
		File file=new File("world.txt");
		InputStream instream=new FileInputStream(file);
		PrintableWoodLoader W=new PrintableWoodLoader();
		PrintableWood wood=W.PrintableWoodLoad(instream,System.out);	
		wood.createWoodman("Dorian", new Point(1, 2), new Point(1,5));
		MyMouse mouse = new MyMouse("Dorian");
		Direction direction= mouse.NextMove(Action.Ok);
		try {
			wood.move("Dorian", direction);	
			assertEquals(direction,Direction.Right);
			assertEquals(wood.move("Dorian", direction),Action.Ok);
			direction= mouse.NextMove(Action.Ok);
			assertEquals(direction,Direction.Up);
			assertEquals(wood.move("Dorian", direction),Action.Fail);
			direction= mouse.NextMove(Action.Fail);
			assertEquals(direction,Direction.Right);
			assertEquals(wood.move("Dorian", direction),Action.Finish);
		} finally {
			instream.close();
		}
	}

	@Test(expected=CodeException.class)
	public void testNextMoveNotToFinish() throws Exception {
		File file=new File("w.txt");
		InputStream instream=new FileInputStream(file);
		PrintableWoodLoader W=new PrintableWoodLoader();
		PrintableWood wood=W.PrintableWoodLoad(instream,System.out);	
		wood.createWoodman("Dorian", new Point(2, 7), new Point(1,4));
		MyMouse mouse = new MyMouse("Dorian");
		try {
			Direction direction= mouse.NextMove(Action.Ok);
			wood.move("Dorian", direction);
			assertEquals(direction,Direction.Right);
			assertEquals(wood.move("Dorian", direction),Action.Fail);
			direction= mouse.NextMove(Action.Fail);
			assertEquals(direction,Direction.Down);
			assertEquals(wood.move("Dorian", direction),Action.Fail);
			direction= mouse.NextMove(Action.Fail);
			assertEquals(direction,Direction.Left);
			assertEquals(wood.move("Dorian", direction),Action.Fail);
			direction= mouse.NextMove(Action.Fail);
			assertEquals(direction,Direction.Up);
			assertEquals(wood.move("Dorian", direction),Action.Dead);
			direction= mouse.NextMove(Action.Fail);
			assertEquals(direction,Direction.Right);
			assertEquals(wood.move("Dorian", direction),Action.Fail);
			direction= mouse.NextMove(Action.Fail);
			assertEquals(direction,Direction.Down);
			assertEquals(wood.move("Dorian", direction),Action.Ok);
			assertEquals(direction,Direction.Down);
			assertEquals(wood.move("Dorian", direction),Action.Fail);
			direction= mouse.NextMove(Action.Fail);
			assertEquals(direction,Direction.Left);
			assertEquals(wood.move("Dorian", direction),Action.Fail);
			direction= mouse.NextMove(Action.Fail);
			assertEquals(direction,Direction.Up);
			assertEquals(wood.move("Dorian", direction),Action.Dead);
			direction= mouse.NextMove(Action.Fail);
			assertEquals(direction,Direction.Right);
			assertEquals(wood.move("Dorian", direction),Action.WoodmanNotFound);
		} finally {
			instream.close();
		}
	}
}
