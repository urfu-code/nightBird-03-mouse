package Bot.Test;

import static org.junit.Assert.*;

import org.junit.Test;

import WoodEngine.Action;
import WoodEngine.Direction;
import Bot.Mouse;

public class testMouse {

	@Test
	public final void testNextMove0() throws Exception {
		Mouse m = new Mouse("penyok");
		assertEquals(Direction.None, m.NextMove(Action.Ok));
		assertEquals(Direction.None, m.NextMove(Action.Life));
		Direction d = m.NextMove(Action.Life); // захилялся до 5hp и пошёл гулять
		assertTrue( d == Direction.Down || d == Direction.Up || d == Direction.Left || d == Direction.Right);
	}

	@Test(expected=Exception.class)
	public final void testNextMove1() throws Exception {
		Mouse m = new Mouse("penyok");
		m.NextMove(Action.Finish); // а первый экшон не играет роли
		m.NextMove(Action.Fail);
		m.NextMove(Action.Fail);
		m.NextMove(Action.Fail);
		m.NextMove(Action.Fail); // исходил всё, не финишировал и не проиграл - "No solutions found"
	}
	
	@Test(expected=Exception.class)
	public final void testNextMove2() throws Exception {
		Mouse m = new Mouse("penyok");
		m.NextMove(Action.Finish); // а первый экшон не играет роли
		m.NextMove(Action.Finish); // к этому его не готовили - "Illegal action"
	}
	
	@Test(expected=Exception.class)
	public final void testNextMove3() throws Exception {
		Mouse m = new Mouse("penyok");
		m.NextMove(Action.Finish); // а первый экшон не играет роли
		m.NextMove(Action.WoodmanNotFound); // к этому его не готовили - "Illegal action"
	}
	// что и как ещё тестировать не знаю ¯\_(ツ)_/¯
}
