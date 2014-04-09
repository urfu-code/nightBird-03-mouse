import static org.junit.Assert.*;

//import java.io.IOException;
import java.util.HashMap;

import org.junit.Test;


public class WoodTest {
	Point testPoint = new Point(1, 1);
	Point testEndPoint = new Point(2, 2);
	HashMap<Point, Character> lab = new HashMap<Point, Character>();
	int length = 4;
	int width = 4;

//	@Test
//	public void testCreateWoodman() throws IOException {		
//		for (int i = 0; i < length; i++) {
//			lab.put(new Point(i, 0), '1');
//			lab.put(new Point(i, 3), '1');
//			lab.put(new Point(0, i), '1');
//			lab.put(new Point(3, i), '1');
//		}
//		lab.put(new Point(1, 1), '0');
//		lab.put(new Point(2, 2), '0');
//		lab.put(new Point(2, 1), 'K');
//		lab.put(new Point(1, 2), 'L');
//
//		Wood hyrule = new Wood(lab, length, width);
//		hyrule.createWoodman("Zelda", testPoint, testEndPoint);
//		assertEquals(new Woodman("Zelda", new Point(1, 1), new Point(2, 2)), hyrule.getWoodman("Zelda"));
//
//	}
//
//	@Test(expected = WoodmanExistsException.class)
//	public void testCreateDuplicateWoodman() {
//		for (int i = 0; i < length; i++) {
//			lab.put(new Point(i, 0), '1');
//			lab.put(new Point(i, 3), '1');
//			lab.put(new Point(0, i), '1');
//			lab.put(new Point(3, i), '1');
//		}
//		lab.put(new Point(1, 1), '0');
//		lab.put(new Point(2, 2), '0');
//		lab.put(new Point(2, 1), 'K');
//		lab.put(new Point(1, 2), 'L');
//
//		Wood hyrule = new Wood(lab, length, width);
//		hyrule.createWoodman("Zelda", testPoint, testEndPoint);
//		hyrule.createWoodman("Zelda", new Point(2, 2), testEndPoint);
//	}
//
//	@Test
//	public void testMoveOk() {
//		for (int i = 0; i < length; i++) {
//			lab.put(new Point(i, 0), '1');
//			lab.put(new Point(i, 3), '1');
//			lab.put(new Point(0, i), '1');
//			lab.put(new Point(3, i), '1');
//		}
//		lab.put(new Point(1, 1), '0');
//		lab.put(new Point(2, 2), '0');
//		lab.put(new Point(2, 1), '0');
//		lab.put(new Point(1, 2), 'L');
//
//		Wood hyrule = new Wood(lab, length, width);
//		hyrule.createWoodman("Epona", testPoint, testEndPoint);
//
//		assertEquals(Action.Ok, hyrule.move("Epona", Direction.Right));
//	}
//
//	@Test
//	public void testMoveFail() {
//		for (int i = 0; i < length; i++) {
//			lab.put(new Point(i, 0), '1');
//			lab.put(new Point(i, 3), '1');
//			lab.put(new Point(0, i), '1');
//			lab.put(new Point(3, i), '1');
//		}
//		lab.put(new Point(1, 1), '0');
//		lab.put(new Point(2, 2), '0');
//		lab.put(new Point(2, 1), 'K');
//		lab.put(new Point(1, 2), 'L');
//
//		Wood hyrule = new Wood(lab, length, width);
//		hyrule.createWoodman("Epona", testPoint, testEndPoint);
//
//		assertEquals(Action.Fail, hyrule.move("Epona", Direction.Left));
//	}
//
//	@Test
//	public void testMoveDead() {
//		for (int i = 0; i < length; i++) {
//			lab.put(new Point(i, 0), '1');
//			lab.put(new Point(i, 3), '1');
//			lab.put(new Point(0, i), '1');
//			lab.put(new Point(3, i), '1');
//		}
//		lab.put(new Point(1, 1), '0');
//		lab.put(new Point(2, 2), '0');
//		lab.put(new Point(2, 1), 'K');
//		lab.put(new Point(1, 2), 'L');
//
//		Wood hyrule = new Wood(lab, length, width);
//		hyrule.createWoodman("Dodongo", new Point(2, 2), testEndPoint);
//
//		assertEquals(Action.Dead, hyrule.move("Dodongo", Direction.Up));
//	}
//
//	@Test
//	public void testMoveWallDead() {
//		for (int i = 0; i < length; i++) {
//			lab.put(new Point(i, 0), '1');
//			lab.put(new Point(i, 3), '1');
//			lab.put(new Point(0, i), '1');
//			lab.put(new Point(3, i), '1');
//		}
//		lab.put(new Point(1, 1), '0');
//		lab.put(new Point(2, 2), '0');
//		lab.put(new Point(2, 1), 'K');
//		lab.put(new Point(1, 2), 'L');
//
//		Wood hyrule = new Wood(lab, length, width);
//		hyrule.createWoodman("Dodongo", new Point(2, 1), testEndPoint);
//		assertEquals(3, hyrule.getWoodman("Dodongo").GetLifeCount());
//		hyrule.move("Dodongo", Direction.Up);
//		assertEquals(2, hyrule.getWoodman("Dodongo").GetLifeCount());
//	}
//
//	@Test
//	public void testMoveLife() {
//		for (int i = 0; i < length; i++) {
//			lab.put(new Point(i, 0), '1');
//			lab.put(new Point(i, 3), '1');
//			lab.put(new Point(0, i), '1');
//			lab.put(new Point(3, i), '1');
//		}
//		lab.put(new Point(1, 1), '0');
//		lab.put(new Point(2, 2), '0');
//		lab.put(new Point(2, 1), 'K');
//		lab.put(new Point(1, 2), 'L');
//
//		Wood hyrule = new Wood(lab, length, width);
//		hyrule.createWoodman("Link", testPoint, testEndPoint);
//
//		assertEquals(Action.Life, hyrule.move("Link", Direction.Down));
//	}
//
//	@Test
//	public void testMoveWallLife() {
//		for (int i = 0; i < length; i++) {
//			lab.put(new Point(i, 0), '1');
//			lab.put(new Point(i, 3), '1');
//			lab.put(new Point(0, i), '1');
//			lab.put(new Point(3, i), '1');
//		}
//		lab.put(new Point(1, 1), '0');
//		lab.put(new Point(2, 2), '0');
//		lab.put(new Point(2, 1), 'K');
//		lab.put(new Point(1, 2), 'L');
//
//		Wood hyrule = new Wood(lab, length, width);
//		hyrule.createWoodman("Link", new Point(1, 2), testEndPoint);
//		assertEquals(3, hyrule.getWoodman("Link").GetLifeCount());
//		hyrule.move("Link", Direction.Down);
//		assertEquals(4, hyrule.getWoodman("Link").GetLifeCount());
//	}
//
//	@Test(expected = WoodmanOnTheWallException.class)
//	public void testWrongStart() {
//		for (int i = 0; i < length; i++) {
//			lab.put(new Point(i, 0), '1');
//			lab.put(new Point(i, 3), '1');
//			lab.put(new Point(0, i), '1');
//			lab.put(new Point(3, i), '1');
//		}
//		lab.put(new Point(1, 1), '0');
//		lab.put(new Point(2, 2), '0');
//		lab.put(new Point(2, 1), 'K');
//		lab.put(new Point(1, 2), 'L');
//
//		Wood hyrule = new Wood(lab, length, width);
//		hyrule.createWoodman("Epona", new Point(0, 0), testEndPoint);	
//	}
//
//	@Test
//	public void testWrongName() {
//		for (int i = 0; i < length; i++) {
//			lab.put(new Point(i, 0), '1');
//			lab.put(new Point(i, 3), '1');
//			lab.put(new Point(0, i), '1');
//			lab.put(new Point(3, i), '1');
//		}
//		lab.put(new Point(1, 1), '0');
//		lab.put(new Point(2, 2), '0');
//		lab.put(new Point(2, 1), 'K');
//		lab.put(new Point(1, 2), 'L');
//
//		Wood hyrule = new Wood(lab, length, width);
//		hyrule.createWoodman("Great Deku Tree", testPoint, testEndPoint);
//		assertEquals(Action.WoodmanNotFound, hyrule.move("Epona", Direction.Right));
//	}
//
//	@Test
//	public void testFatality() {
//		for (int i = 0; i < length; i++) {
//			lab.put(new Point(i, 0), '1');
//			lab.put(new Point(i, 3), '1');
//			lab.put(new Point(0, i), '1');
//			lab.put(new Point(3, i), '1');
//		}
//		lab.put(new Point(1, 1), '0');
//		lab.put(new Point(2, 2), '0');
//		lab.put(new Point(2, 1), 'K');
//		lab.put(new Point(1, 2), 'L');
//
//		Wood hyrule = new Wood(lab, length, width);
//		hyrule.createWoodman("Dodongo", new Point(2, 2), testEndPoint);
//		for (int i = 0; i < 4; i++) {
//			hyrule.move("Dodongo", Direction.Up);
//			hyrule.move("Dodongo", Direction.Down);
//		}
//		assertEquals(Action.WoodmanNotFound, hyrule.move("Dodongo", Direction.Up));	
//	}

	@Test
	public void testFinish() {
		for (int i = 0; i < length; i++) {
			lab.put(new Point(i, 0), '1');
			lab.put(new Point(i, 3), '1');
			lab.put(new Point(0, i), '1');
			lab.put(new Point(3, i), '1');
		}
		lab.put(new Point(1, 1), '0');
		lab.put(new Point(2, 2), '0');
		lab.put(new Point(2, 1), 'K');
		lab.put(new Point(1, 2), 'L');
		
		Wood hyrule = new Wood(lab, length, width);
		hyrule.createWoodman("Great Deku Tree", testEndPoint, testEndPoint);
		
		assertEquals(Action.Finish, hyrule.move("Great Deku Tree", Direction.None));
	}

}
