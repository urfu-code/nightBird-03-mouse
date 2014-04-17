package Tests;
import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;

import org.junit.Before;
import org.junit.Test;

import Classes.Point;
import Classes.WoodLoader;
import Enums.Action;
import Enums.Direction;
import Exceptions.EmptyFileException;
import Exceptions.InvalidFileException;
import Exceptions.UnexceptableNameException;
import Interfaces.WoodInterface;
import Interfaces.WoodLoaderInterface;



public class WoodLoaderTests{
	private WoodInterface myWood;
	@Before
	public void makeWood() {
		String[] masOfStr = new String [4];
		masOfStr[0] = "1111\n";
		masOfStr[1] = "1001\n";
		masOfStr[2] = "1LK1\n";
		masOfStr[3] = "1111\n";
		StringBuilder strBuild = new StringBuilder();
		for (int i=0; i<masOfStr.length; i++)
			strBuild.append(masOfStr[i]);
		String str = strBuild.toString();
		byte[] buf = str.getBytes();
		ByteArrayInputStream stream = new ByteArrayInputStream(buf);
		WoodLoaderInterface loader = new WoodLoader();
		try {
			myWood = loader.Load(stream);
		} catch (EmptyFileException e) {
			fail("EmptyFileException");
			e.printStackTrace();
		} catch (InvalidFileException e) {
			fail("InvalidFileException");
			e.printStackTrace();
		}
	}

	@Test
	public void testBoxDrawingCharacter() {
		String[] masOfStr = new String [7];
		masOfStr[0] = "┌─┬─┐\n";
		masOfStr[1] = "│ │ │\n";
		masOfStr[2] = "│♥│ │\n";
		masOfStr[3] = "│☢  │\n";
		masOfStr[4] = "├─┼ ┤\n";
		masOfStr[5] = "│⍹  │\n";
		masOfStr[6] = "└─┴─┘\n";
		StringBuilder strBuild = new StringBuilder();
		for (int i=0; i<masOfStr.length; i++)
			strBuild.append(masOfStr[i]);
		String str = strBuild.toString();
		byte[] buf = str.getBytes();
		ByteArrayInputStream stream = new ByteArrayInputStream(buf);
		WoodLoaderInterface loader = new WoodLoader();
		WoodInterface testWood = null;
		try {
			testWood = loader.Load(stream);
		} catch (EmptyFileException e) {
			fail("EmptyFileException");
			e.printStackTrace();
		} catch (InvalidFileException e) {
			fail("InvalidFileException");
			e.printStackTrace();
		}
		try {
			testWood.createWoodman("Player", new Point (1, 1), new Point (0,0));
		} catch (UnexceptableNameException e) {
			fail("UnexceptableNameException");
			e.printStackTrace();
		}
		assertEquals(Action.Life, testWood.move("Player", Direction.Down));
		assertEquals(Action.Dead, testWood.move("Player", Direction.Down));
		assertEquals(Action.Fail, testWood.move("Player", Direction.Left));
		assertEquals(Action.Ok, testWood.move("Player", Direction.Right));
	}
	
	@Test(expected = EmptyFileException.class)
	public void EmptyFileExceptionTest() throws EmptyFileException, InvalidFileException {
		String str = "";
		byte[] mas = str.getBytes();
		ByteArrayInputStream stream = new ByteArrayInputStream(mas);
		WoodLoaderInterface loader = new WoodLoader();
		loader.Load(stream);
	}
	
	
	@Test(expected = InvalidFileException.class)
	public void InvalidFileExceptionTest() throws EmptyFileException, InvalidFileException {
		String str = "111\n101\n1011";
		byte[] mas = str.getBytes();
		ByteArrayInputStream stream = new ByteArrayInputStream(mas);
		WoodLoaderInterface loader = new WoodLoader();
		loader.Load(stream);
	}
	
	
	@Test
	public void testLoad() throws EmptyFileException, InvalidFileException {
		try {
			myWood.createWoodman("Player1", new Point(1, 1), new Point(0, 0));
			assertEquals(Action.Life, myWood.move("Player1", Direction.Down));
			assertEquals(Action.Dead, myWood.move("Player1", Direction.Right));
			assertEquals(Action.Ok, myWood.move("Player1", Direction.Up)); //1 жизнь
			assertEquals(Action.Ok, myWood.move("Player1", Direction.Left));
			
		} catch (UnexceptableNameException e) {
			fail("UnexceptableNameException");
			e.printStackTrace();
		}
	}

}
