package Tests;




import junit.framework.TestCase;

import Classes.Point;
import Classes.Woodman;

public class WoodmanTests extends TestCase{

	Woodman man = new Woodman(new Point(1,1), "TestMan");

	public void testGetLifeCount() {
		assertEquals(3, man.GetLifeCount());
	}

	public void testGetName() {
		assertEquals("TestMan", man.GetName());
	}

	public void testKill() {
		Woodman man1 = new Woodman(new Point(1,1), "TestMan1");
		assertEquals(3, man1.GetLifeCount());
		assertEquals(true, man1.Kill());
		assertEquals(2, man1.GetLifeCount());
		assertEquals(true, man1.Kill());
		assertEquals(true, man1.Kill());
		assertEquals(false, man1.Kill());
	}

	public void testAddLife() {
		Woodman man1 = new Woodman(new Point(1,1), "TestMan1");
		assertEquals(3, man1.GetLifeCount());
		man1.AddLife();
		assertEquals(4, man1.GetLifeCount());
	}

	public void testGetLocation() {
		assertEquals(new Point(1,1), man.GetLocation());
	}

	public void testSetLocation() {
		Woodman man1 = new Woodman(new Point(1,1), "TestMan1");
		assertEquals(new Point(1,1), man1.GetLocation());
		man1.SetLocation(new Point(2, 2));
		assertEquals(new Point(2, 2), man1.GetLocation());
	}

	public void testMoveToStart() {
		Woodman man1 = new Woodman(new Point(1,1), "TestMan1");
		assertEquals(new Point(1,1), man1.GetLocation());
		man1.SetLocation(new Point(2, 2));
		assertEquals(new Point(2,2), man1.GetLocation());
		man1.MoveToStart();
		assertEquals(new Point(1, 1), man1.GetLocation());
	}

}
