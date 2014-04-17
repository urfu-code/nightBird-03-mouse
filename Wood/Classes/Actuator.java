package Classes;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;


import Enums.Action;
import Enums.Direction;
import Exceptions.EmptyFileException;
import Exceptions.InvalidFileException;
import Exceptions.UnexceptableNameException;

public class Actuator {

	public static void main(String[] args) throws FileNotFoundException, EmptyFileException, InvalidFileException, UnexceptableNameException {
		WoodLoader woodLoader = new WoodLoader();
		File file = new File("input.txt");
		FileInputStream FIS = new FileInputStream(file);
		//InputStream FIS = System.in;
		PrintableWood myWood = woodLoader.printableLoader(FIS, System.out);
		Scanner scanner = new Scanner(System.in);
		String name = null;
		try {
			System.out.println("введите имя игрока: ");
			name = scanner.nextLine();
			int xS, yS;
			System.out.println("Координаты старта: (в моей карте это 1 1)");
			xS = scanner.nextInt();
			yS = scanner.nextInt();
			System.out.println("Координаты финиша: (в моей карте это 1 10)");
			int xF, yF;
			xF = scanner.nextInt();
			yF = scanner.nextInt();
			myWood.createWoodman(name, new Point(xS, yS), new Point(xF, yF));
		}
		finally {
			scanner.close();
		}
		
		Action response = Action.Ok;
		Mause mause = new Mause();
		Direction dir;
		while (response != Action.Finish && response != Action.WoodmanNotFound) {
			dir = mause.NextMove(response);
			System.out.println(dir.name());
			response = myWood.move(name, dir);
			System.out.println(response.name());
		}
	}

}
