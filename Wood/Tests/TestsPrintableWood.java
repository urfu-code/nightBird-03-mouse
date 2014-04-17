package Tests;

import java.io.ByteArrayOutputStream;

import org.junit.Before;
import org.junit.Test;

import Classes.Point;
import Classes.PrintableWood;
import Enums.Direction;
import Exceptions.UnexceptableNameException;

public class TestsPrintableWood {

	char[][] arr;
	PrintableWood wood;
	@Before
	public void before() {
		arr = new char[5][7];
		arr[0] = new char[]{'1', '1', '1', '1', '1', '1', '1'};
		arr[1] = new char[]{'1', 'L', '0', '0', '1', 'K', '1'};
		arr[2] = new char[]{'1', '1', '1', '0', '1', '0', '1'};
		arr[3] = new char[]{'1', '0', '0', '0', '0', '0', '1'};
		arr[4] = new char[]{'1', '1', '1', '1', '1', '1', '1'};
		
		/*	
			┌─┬─┐
			│♥│ │
			│ │ │
			│   │
			├─- ┤
			│⍹  │
			└─┴─┘
		 */
		
		
	}
	
	@Test
	public void testBorders() {
		ByteArrayOutputStream oStream = new ByteArrayOutputStream();
		wood = new PrintableWood(arr, oStream);
		byte[] outputArr = oStream.toByteArray();
		char[] cArr = new char[outputArr.length];
		for (int i=0; i<cArr.length; i++) {
			
		}
	}
	
	@Test
	public void testCreateWoodman() throws UnexceptableNameException {

		
		wood.createWoodman("Player2", new Point(1,3), new Point(1,1));
		wood.move("Player2", Direction.Down);
	}

	@Test
	public void testMove() {
		//fail("Not yet implemented");
	}

	@Test
	public void testPrintableWood() {
		//fail("Not yet implemented");
	}

}
