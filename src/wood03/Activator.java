package wood03;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

import wood01.Action;
import wood01.Point;
import wood01.PrintableTheWood;
import wood01.TheWoodLoader;

public class Activator {
	
	public static void main(String[] Arguments) throws Exception {
		File file = new File("C:\\Users\\epS\\workspace\\TheNightBirdGame\\wood\\wood03\\wood.txt");
		if ((!file.isFile())||(!file.canRead())) {
			throw new Exception("���-�� � ������ ����!");
		}
		TheWoodLoader loader = new TheWoodLoader();
		PrintableTheWood wood = (PrintableTheWood) loader.Load(new FileInputStream(file),System.out);
		try {
			@SuppressWarnings("resource")
			Scanner scanner = new Scanner(System.in);
			System.out.println("����������, ������� ��� ����� �����:\n");
			String name = scanner.nextLine();
			System.out.println("����������, ������� ���������� ����� ������ � �����:\n");
			int x;
			int y;
			Point finishPoint;
			Point startPoint;
			x = scanner.nextInt();
			y = scanner.nextInt();
			startPoint = new Point(x,y);
			x = scanner.nextInt();
			y = scanner.nextInt();
			finishPoint = new Point(x,y);
			Action currentAction = Action.Ok;
			wood.createWoodman(name, startPoint,finishPoint);
			TheMouse mouse = new TheMouse();
			while (true) {
				if ((currentAction == Action.WoodmanNotFound)||(currentAction == Action.ExitFound)) {
					break;
				}
				currentAction = wood.move(name, mouse.NextMove(currentAction));
			}
			if (currentAction == Action.ExitFound) {
				System.out.println("����������! ����� ����� �����!");
			}
			else {
				System.out.println("����� ������ :(");
			}
		}
		catch(IOException e) {
			e.getMessage();
		}
	
	}
	
}
