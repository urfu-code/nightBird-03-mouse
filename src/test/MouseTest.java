package test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import mouse.My_Mouse;

import org.junit.Test;

import wood.Action;
import wood.Direction;
import wood.My_WoodLoader;
import wood.Point;
import wood.PrintWood;

public class MouseTest {
	@Test
	public void testNextMove() throws IOException {
		String name = "Mouse";
		Point start = new Point(1,1);
		Point finish = new Point(3,5);
		File file = new File("wood.txt");
		My_WoodLoader loader = new My_WoodLoader();
		PrintWood wood = (PrintWood)loader.Load(new FileInputStream(file), System.out);
		wood.createWoodman(name, start, finish);
		My_Mouse mouse = new My_Mouse();
		Action action = Action.Ok;
		while ((action != Action.WoodmanNotFound) && (action != Action.Finish)){
			Direction direction = mouse.NextMove(action);
			action = wood.move(name, direction);
			if(action == Action.WoodmanNotFound) System.out.println("Мышь умерла!:(");
			if(action == Action.Finish) System.out.println("Мышь дошла до финиша!:)");
	}
	/*@Test
	public void testNextMove2() throws IOException {
		String name = "Mouse";
		Point start = new Point(6,1);
		Point finish = new Point(6,6);
		File file = new File("wood1.txt");
		My_WoodLoader loader = new My_WoodLoader();
		PrintWood wood = (PrintWood)loader.Load(new FileInputStream(file), System.out);
		wood.createWoodman(name, start, finish);
		My_Mouse mouse = new My_Mouse();
		Action action = Action.Ok;
		while ((action != Action.WoodmanNotFound) && (action != Action.Finish)){
			Direction direction = mouse.NextMove(action);
			action = wood.move(name, direction);
			if(action == Action.WoodmanNotFound) System.out.println("Мышь умерла!:(");
			if(action == Action.Finish) System.out.println("Мышь дошла до финиша!:)");
		}
	}*/
/*	@Test
	public void testNextMove3() throws IOException {
		String name = "Mouse";
		Point start = new Point(1,1);
		Point finish = new Point(4,4);
		File file = new File("wood2.txt");
		My_WoodLoader loader = new My_WoodLoader();
		PrintWood wood = (PrintWood)loader.Load(new FileInputStream(file), System.out);
		wood.createWoodman(name, start, finish);
		My_Mouse mouse = new My_Mouse();
		Action action = Action.Ok;
		while ((action != Action.WoodmanNotFound) && (action != Action.Finish)){
			Direction direction = mouse.NextMove(action);
			action = wood.move(name, direction);
			if(action == Action.WoodmanNotFound) System.out.println("Мышь умерла!:(");
			if(action == Action.Finish) System.out.println("Мышь дошла до финиша!:)");
		}*/
	}
}
