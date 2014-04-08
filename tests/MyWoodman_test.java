import static org.junit.Assert.*;

import org.junit.Test;


public class MyWoodman_test {

	MyWoodman woodman = new MyWoodman("Armstrong", new Point(0,0), null);
	
	@Test
	public void GetLifeCount_test() {
		assertEquals(3, woodman.GetLifeCount());
	}

	@Test
	public void GetName_test() {
		assertEquals("Armstrong", woodman.GetName());
	}
	
	@Test
	public void Kill_MinusLifeTest() {
		assertEquals(3, woodman.GetLifeCount());
		assertEquals(true, woodman.Kill());
		assertEquals(2, woodman.GetLifeCount());
	}
	
	@Test
	public void Kill_DeathTest() {
		assertEquals(true, woodman.Kill());
		assertEquals(true, woodman.Kill());
		assertEquals(true, woodman.Kill());
		assertEquals(false, woodman.Kill());
	}
	
	@Test
	public void AddLife_test() {
		assertEquals(3, woodman.GetLifeCount());
		woodman.AddLife();
		assertEquals(4, woodman.GetLifeCount());
	}
	
	@Test
	public void GetLocation_test() {
		assertEquals(0, woodman.GetLocation().getX());
		assertEquals(0, woodman.GetLocation().getY());
	}
	
	@Test
	public void SetLocation_test() {
		assertEquals(0, woodman.GetLocation().getX());
		assertEquals(0, woodman.GetLocation().getY());
		woodman.SetLocation(new Point(1, 1));
		assertEquals(1, woodman.GetLocation().getX());
		assertEquals(1, woodman.GetLocation().getY());
	}
	
	@Test
	public void MoveToStart_test() {
		woodman.SetLocation(new Point(1, 1));
		woodman.MoveToStart();
		assertEquals(0, woodman.GetLocation().getX());
		assertEquals(0, woodman.GetLocation().getY());
	}
}
