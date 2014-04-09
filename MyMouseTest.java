import static org.junit.Assert.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.junit.Test;


public class MyMouseTest {
	@Test
	public void testNextMoveToFinish1() throws Exception {
		File file=new File("world.txt");
		InputStream instream=new FileInputStream(file);
		PrintableWoodLoader W=new PrintableWoodLoader();
		PrintableWood wood=W.PrintableWoodLoad(instream,System.out);
		Action result = Action.Ok;
		wood.createWoodman("Dorian", new Point(1, 1), new Point(1, 2));
		MyMouse mouse = new MyMouse();
		result = wood.move("Dorian", mouse.NextMove(result));
		assertEquals(Direction.Right, mouse.NextMove(result));
		assertEquals(Direction.Right, mouse.NextMove(result));
		assertEquals(Direction.Right, mouse.NextMove(result));
		assertEquals(Action.Finish, result);
	}
	@Test
	public void testNextMoveToFinish2() throws Exception {
		File file=new File("world.txt");
		InputStream instream=new FileInputStream(file);
		PrintableWoodLoader W=new PrintableWoodLoader();
		PrintableWood wood=W.PrintableWoodLoad(instream,System.out);
		Action result = Action.Ok;
		wood.createWoodman("Dorian", new Point(2, 7), new Point(5, 7));
		MyMouse mouse = new MyMouse();
		result = wood.move("Dorian", mouse.NextMove(result));
		assertEquals(Direction.Right, mouse.NextMove(result));
		assertEquals(Direction.Down, mouse.NextMove(result));
		assertEquals(Direction.Down, mouse.NextMove(result));
		assertEquals(Direction.Down, mouse.NextMove(result));
		assertEquals(Action.Finish, result);
	}

	@Test
	public void testNextMoveNotToFinish() throws Exception {
		File file=new File("w.txt");
		InputStream instream=new FileInputStream(file);
		PrintableWoodLoader W=new PrintableWoodLoader();
		PrintableWood wood=W.PrintableWoodLoad(instream,System.out);
		Action result = Action.Ok;
		wood.createWoodman("Dorian", new Point(2, 7), new Point(5, 7));
		MyMouse mouse = new MyMouse();
		result = wood.move("Dorian", mouse.NextMove(result));
		assertEquals(Direction.Right, mouse.NextMove(result));
		assertEquals(Direction.Up, mouse.NextMove(result));
		assertEquals(Direction.Up, mouse.NextMove(result));
		assertEquals(Direction.Up, mouse.NextMove(result));		
		assertEquals(Action.WoodmanNotFound, result);

	}


}
