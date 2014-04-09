import static org.junit.Assert.*;
import java.util.Iterator;
import org.junit.Test;

// при создании мыша создаём начальную точку. специальная функция меняет положение точки, в зависимости от результата nextMove

public class MouseTest {

	Mouse mouse = new Mouse("Mickey");

	@Test
	public void testMouseName() {
		assertEquals("Mickey", mouse.getName());
	}

	@Test
	public void testMouseGetDirection() {
		assertEquals(Direction.None, mouse.getDirection());
	}

	@Test
	public void testMouseSetDirection() {
		mouse.setDirection(Direction.Left);
		assertEquals(Direction.Left, mouse.getDirection());
	}

	@Test
	public void testMouseEnergyCounter() {
		assertEquals(3, mouse.getEnergyPoints());
	}

	@Test
	public void testMouseLifeStatus() {
		assertEquals(false, mouse.getLifeStatus());
	}

	@Test
	public void testMouseEmptinessStatus() {
		assertEquals(true, mouse.getEmptinessStatus());
	}

		@Test
		public void testNextMoveOkFirstly() {
			assertEquals(Direction.None, mouse.NextMove(Action.Ok));
		}
		
		@Test
		public void testNextMoveOk() { 
			mouse.NextMove(Action.Ok);
			assertEquals(Direction.Right, mouse.NextMove(Action.Ok));
		}
		
		@Test
		public void testNextMoveFail() {
			mouse.NextMove(Action.Ok);
			mouse.NextMove(Action.Ok);
			assertEquals(Direction.Up, mouse.NextMove(Action.Fail));
		}
		
		@Test
		public void testNextMoveDead() {
			mouse.NextMove(Action.Ok);
			assertEquals(Direction.Right, mouse.NextMove(Action.Dead));
		}
		
		@Test
		public void testNextMoveLife() {
			mouse.NextMove(Action.Ok);
			assertEquals(Direction.None, mouse.NextMove(Action.Life));
		}
		
		@Test
		public void testNextMoveLifeAndGetEnergy() {
			mouse.NextMove(Action.Ok);
			mouse.NextMove(Action.Life);
			assertEquals(2, mouse.getEnergyPoints());
		}

		@Test
		public void testNextMoveFromLife() { // проверяем, чем заполнился стек 
			mouse.NextMove(Action.Ok);
			mouse.NextMove(Action.Life);
			mouse.NextMove(Action.Life);
			mouse.NextMove(Action.Life);
			mouse.NextMove(Action.Life);
			mouse.NextMove(Action.Life); // Right
			
			mouse.NextMove(Action.Ok); // Down
			mouse.NextMove(Action.Fail); // Right
			mouse.NextMove(Action.Ok);	// Down	
			mouse.NextMove(Action.Ok); // Left
			mouse.NextMove(Action.Fail); // Down
			mouse.NextMove(Action.Fail); // Right
			mouse.NextMove(Action.Fail); // Up
			mouse.NextMove(Action.Ok); // Right
			Iterator<Direction> stackIterator = mouse.wayFromLife.iterator();
			while (stackIterator.hasNext()) {
				Direction dir = stackIterator.next();
				assertEquals(Direction.Right, dir);
			}
		}

	@Test
	public void testWayToLifeAndReturning() { 
		mouse.NextMove(Action.Ok);
		mouse.NextMove(Action.Life);
		mouse.NextMove(Action.Life);
		mouse.NextMove(Action.Life);
		mouse.NextMove(Action.Life);
		mouse.NextMove(Action.Life); // Right

		mouse.NextMove(Action.Ok); // Down
		mouse.NextMove(Action.Fail); // Right
		mouse.NextMove(Action.Ok);
		mouse.NextMove(Action.Fail);
		mouse.NextMove(Action.Ok);
		mouse.NextMove(Action.Fail); 
		
		mouse.nameOfAlghoritm = "Back to life";
		assertEquals(Direction.Left, mouse.NextMove(Action.Ok));
		assertEquals(Direction.Left, mouse.NextMove(Action.Ok));
		assertEquals(Direction.Left, mouse.NextMove(Action.Ok));

		assertEquals(Direction.Right, mouse.NextMove(Action.Ok));
		assertEquals(Direction.Right, mouse.NextMove(Action.Ok));
		assertEquals(Direction.Right, mouse.NextMove(Action.Ok));
	}

}
