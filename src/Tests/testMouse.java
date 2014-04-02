package Tests;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;

import org.junit.Before;
import org.junit.Test;

import wood01.Action;
import wood01.Point;
import wood01.PrintableTheWood;
import wood01.TheWoodLoader;
import wood03.TheMouse;

public class testMouse {

	TheMouse mouse;
	PrintableTheWood wood;
	String name;
	@Before
	public void setUp() throws Exception {
		Point startPoint = new Point(7,2);
		Point finishPoint = new Point(1,1);
		File file = new File("C:\\Users\\epS\\workspace\\TheNightBirdGame\\wood\\wood03\\wood.txt");
		TheWoodLoader loader = new TheWoodLoader();
		name = "Kolya";
		wood = (PrintableTheWood) loader.Load(new FileInputStream(file),System.out);
		wood.createWoodman(name, startPoint,finishPoint);
		mouse = new TheMouse();
	}

	@Test
	public void testMouseFoundPath() throws Exception {
		Action currentAction = Action.Ok;
		while (true) {
			if ((currentAction == Action.WoodmanNotFound)||(currentAction == Action.ExitFound)) {
				break;
			}
			currentAction = wood.move(name, mouse.NextMove(currentAction));
		}
		assertEquals(Action.ExitFound, currentAction);
	}
	
	@Test
	public void testMouseDontFoundPath() throws Exception {
		name = "Petya";
		wood.createWoodman(name, new Point(1,1), new Point(7,2));
		Action currentAction = Action.Ok;
		while (true) {
			if ((currentAction == Action.WoodmanNotFound)||(currentAction == Action.ExitFound)) {
				break;
			}
			currentAction = wood.move(name, mouse.NextMove(currentAction));
		}
		assertEquals(Action.WoodmanNotFound, currentAction);
	}

}
