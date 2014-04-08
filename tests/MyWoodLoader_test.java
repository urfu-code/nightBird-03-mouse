import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;

import org.junit.Test;


public class MyWoodLoader_test {

	@Test
	public void Up() throws Exception {
		File file = new File ("test_input_wood.txt");
		FileInputStream stream = new FileInputStream(file);
		MyWoodLoader loader = new MyWoodLoader();
		MyWood wood = loader.Load(stream);
		wood.createWoodman("Armstrong", new Point (1,1), null);
		assertEquals(Action.Fail, wood.move("Armstrong", Direction.Up));
}
	
	@Test
	public void Down() throws Exception {
		File file = new File ("test_input_wood.txt");
		FileInputStream stream = new FileInputStream(file);
		MyWoodLoader loader = new MyWoodLoader();
		MyWood wood = loader.Load(stream);
		wood.createWoodman("Armstrong", new Point (1,1), null);
		assertEquals(Action.Ok, wood.move("Armstrong", Direction.Down));
}
	
	@Test
	public void Right() throws Exception {
		File file = new File ("test_input_wood.txt");
		FileInputStream stream = new FileInputStream(file);
		MyWoodLoader loader = new MyWoodLoader();
		MyWood wood = loader.Load(stream);
		wood.createWoodman("Armstrong", new Point (1,1), null);
		assertEquals(Action.Life, wood.move("Armstrong", Direction.Right));
}
	
	@Test
	public void Left() throws Exception {
		File file = new File ("test_input_wood.txt");
		FileInputStream stream = new FileInputStream(file);
		MyWoodLoader loader = new MyWoodLoader();
		MyWood wood = loader.Load(stream);
		wood.createWoodman("Armstrong", new Point (1,1), null);
		assertEquals(Action.Fail, wood.move("Armstrong", Direction.Left));
}
	}


