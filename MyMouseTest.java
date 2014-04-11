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
		wood.createWoodman("Dorian", new Point(1, 2), new Point(1,4));
		MyMouse mouse = new MyMouse();
		result = wood.move("Dorian", mouse.NextMove(result));
		while (result != Action.Finish){
			 			Direction direction = mouse.NextMove(result);
			 			result = wood.move("Dorian", direction);
			 		}
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
		while (result != Action.WoodmanNotFound){
 			Direction direction = mouse.NextMove(result);
 			result = wood.move("Dorian", direction);
 		}
	}


}
