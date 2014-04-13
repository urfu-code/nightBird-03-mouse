import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;

import org.junit.Test;


public class MyMouse_test extends MyMouse {

	@Test
	public void testNextMove_finish() throws Exception {
		File file = new File ("test_MouseWood.txt");
		FileInputStream stream = new FileInputStream(file);
		MyWoodLoader loder = new MyWoodLoader();
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		PrintableWood wood = loder.Load(stream, output);
		String actions = "";
		
		Action action = Action.Ok;
		wood.createWoodman("Armstrong", new Point(3, 1), new Point(4, 5));
		MyMouse mouse = new MyMouse();
		while ((action != Action.WoodmanNotFound)&&(action != Action.Finish)) {
			action = wood.move("Armstrong", mouse.NextMove(action));
			actions += action.name();
		}
		assertEquals("FailOkFailFailOkFailOkOkFailOkOkFailFailOkFailOkFailFailOkOkFailOkOkFailOkFailOkFailLifeLifeOkFailOkOkFailFailFinish", actions);
	}

	@Test
	public void testNextMove_dead() throws Exception {
		File file = new File ("test_MouseWood.txt");
		FileInputStream stream = new FileInputStream(file);
		MyWoodLoader loder = new MyWoodLoader();
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		PrintableWood wood = loder.Load(stream, output);
		String actions = "";
		
		Action action = Action.Ok;
		wood.createWoodman("Gagarin", new Point(3, 1), new Point(8, 1));
		MyMouse mouse = new MyMouse();
		while ((action != Action.WoodmanNotFound)&&(action != Action.Finish)) {
			action = wood.move("Gagarin", mouse.NextMove(action));
			actions += action.name();
		}
		assertEquals("FailOkFailFailOkFailOkOkFailOkOkFailFailOkFailOkFailFailOkOkFailOkOkFailOkFailOkFailLifeLifeOkFailOkOkFailFailOkFailOkFailFailOkFailOkFailOkFailWoodmanNotFound", actions);
	}
}
