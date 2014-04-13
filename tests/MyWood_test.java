import static org.junit.Assert.*;

import org.junit.Test;


public class MyWood_test {
	
	@Test
	public void Fail_test() throws Exception{
		//1111
		//10L1
		//10K1
		//1111
		
		char[][] wood_map = new char[4][4];
		for (int i=0; i<4; i++) {
			wood_map[0][i]='1';
			wood_map[3][i]='1';
			wood_map[i][0]='1';
			wood_map[i][3]='1';
		}
		
		wood_map[1][1]='0';
		wood_map[1][2]='0';
		wood_map[2][1]='L';
		wood_map[2][2]='K';

		MyWood wood = new MyWood(wood_map);
		
		wood.createWoodman("Armstrong", new Point (1,1), null);
		assertEquals(Action.Fail, wood.move("Armstrong", Direction.Up));
	}

	@Test
	public void Ok_test() throws Exception{
		//1111
		//10L1
		//10K1
		//1111
		
		char[][] wood_map = new char[4][4];
		
		for (int i=0; i<4; i++) {
			wood_map[0][i]='1';
			wood_map[3][i]='1';
			wood_map[i][0]='1';
			wood_map[i][3]='1';
		}
		
		wood_map[1][1]='0';
		wood_map[1][2]='0';
		wood_map[2][1]='L';
		wood_map[2][2]='K';

		MyWood wood = new MyWood(wood_map);
		
		wood.createWoodman("Armstrong", new Point (1,1), null);
		assertEquals(Action.Ok, wood.move("Armstrong", Direction.Down));
	}
	
	@Test
	public void Life_test() throws Exception{
		//1111
		//10L1
		//10K1
		//1111
		
		char[][] wood_map = new char[4][4];
		
		for (int i=0; i<4; i++) {
			wood_map[0][i]='1';
			wood_map[3][i]='1';
			wood_map[i][0]='1';
			wood_map[i][3]='1';
		}
		
		wood_map[1][1]='0';
		wood_map[1][2]='0';
		wood_map[2][1]='L';
		wood_map[2][2]='K';

		MyWood wood = new MyWood(wood_map);
		
		wood.createWoodman("Armstrong", new Point (1,1), null);
		assertEquals(Action.Life, wood.move("Armstrong", Direction.Right));
	}
	
	@Test
	public void Dead_test() throws Exception{
		//1111
		//10L1
		//10K1
		//1111
		
		char[][] wood_map = new char[4][4];
		
		for (int i=0; i<4; i++) {
			wood_map[0][i]='1';
			wood_map[3][i]='1';
			wood_map[i][0]='1';
			wood_map[i][3]='1';
		}
		
		wood_map[1][1]='0';
		wood_map[1][2]='0';
		wood_map[2][1]='L';
		wood_map[2][2]='K';

		MyWood wood = new MyWood(wood_map);
		
		wood.createWoodman("Armstrong", new Point (1,1), null);
		wood.move("Armstrong", Direction.Down);
		assertEquals(Action.Dead, wood.move("Armstrong", Direction.Right));
	}
	
	@Test
	public void Dead_2_test() throws Exception{
		//1111
		//10L1
		//10K1
		//1111
		
		char[][] wood_map = new char[4][4];
		
		for (int i=0; i<4; i++) {
			wood_map[0][i]='1';
			wood_map[3][i]='1';
			wood_map[i][0]='1';
			wood_map[i][3]='1';
		}
		
		wood_map[1][1]='0';
		wood_map[1][2]='0';
		wood_map[2][1]='L';
		wood_map[2][2]='K';

		MyWood wood = new MyWood(wood_map);
		
		wood.createWoodman("Armstrong", new Point (1,1), null);
		wood.move("Armstrong", Direction.Down);
		wood.move("Armstrong", Direction.Right);
		wood.move("Armstrong", Direction.Left);
		wood.move("Armstrong", Direction.Right);
		wood.move("Armstrong", Direction.Left);
		wood.move("Armstrong", Direction.Right);
		wood.move("Armstrong", Direction.Left);
		assertEquals(Action.WoodmanNotFound, wood.move("Armstrong", Direction.Right));
	}
	
	@Test
	public void WoodmanNotFound_test() throws Exception{
		//1111
		//10L1
		//10K1
		//1111
		
		char[][] wood_map = new char[4][4];
		
		for (int i=0; i<4; i++) {
			wood_map[0][i]='1';
			wood_map[3][i]='1';
			wood_map[i][0]='1';
			wood_map[i][3]='1';
		}
		
		wood_map[1][1]='0';
		wood_map[1][2]='0';
		wood_map[2][1]='L';
		wood_map[2][2]='K';

		MyWood wood = new MyWood(wood_map);
		
		wood.createWoodman("Armstrong", new Point (1,1), null);
		wood.move("Armstrong", Direction.Down);
		assertEquals(Action.WoodmanNotFound, wood.move("Gagarin", Direction.Right));
	}
}