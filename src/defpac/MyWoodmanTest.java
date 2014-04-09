package defpac;

import junit.framework.TestCase;

public class MyWoodmanTest extends TestCase {

	MyWoodman woodman = new MyWoodman("aaa", new Point (0, 0));

	public void testGetLifeCount() {
		assertEquals(woodman.GetLifeCount(), 3, 0.001);
	}

	public void testGetName() {
		assertEquals(woodman.GetName(), "aaa");
	}

	public void testKill() {
		woodman.Kill();
		assertEquals(woodman.GetLifeCount(), 2, 0.001);
		woodman.Kill();
		woodman.Kill();
		assertEquals(woodman.Kill(), false);
	}

	MyWoodman woodmanNew = new MyWoodman("aaa", new Point (0, 0));
	
	public void testAddLife() {
		woodmanNew.AddLife();
		assertEquals(woodmanNew.GetLifeCount(), 4, 0.001);
	}

	public void testGetLocation() {
		assertEquals(woodmanNew.GetLocation(), new Point (0,0));
	}

	public void testSetLocation() {
		woodmanNew.SetLocation(new Point(1,2));
		assertEquals(woodmanNew.GetLocation(), new Point (1,2));
	}

	public void testMoveToStart() {
		woodmanNew.MoveToStart();
		assertEquals(woodmanNew.GetLocation(), new Point (0,0));
	}

}
