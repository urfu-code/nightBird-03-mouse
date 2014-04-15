package mouse;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import wood.Action;
import wood.Direction;
import wood.My_WoodLoader;
import wood.Point;
import wood.PrintWood;

public class Actuator {
	
	public static void main(String[] args){
		File file = new File("wood.txt");
		My_WoodLoader loader = new My_WoodLoader();
		PrintWood wood;
		try {
			wood = (PrintWood)loader.Load(new FileInputStream(file), System.out);
			Scanner scanner = new Scanner(System.in);
			System.out.println("Введите имя мышки:\n");
			String name = scanner.nextLine();
			System.out.println("Введите точки старта и финиша:\n");
			int x;
			int y;
			Point finish;
			Point start;
			x = scanner.nextInt();
			y = scanner.nextInt();
			start = new Point(x,y);
			x = scanner.nextInt();
			y = scanner.nextInt();
			finish = new Point(x,y);
			wood.createWoodman(name, start, finish);
			My_Mouse mouse = new My_Mouse();
			Action action = Action.Ok;
			while ((action != Action.WoodmanNotFound) && (action != Action.Finish)){
				Direction direction = mouse.NextMove(action);
				action = wood.move(name, direction);
				if(action == Action.WoodmanNotFound) System.out.println("Мышь умерла!:(");
				if(action == Action.Finish) System.out.println("Мышь дошла до финиша!:)");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
		
}
