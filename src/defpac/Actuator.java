package defpac;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class Actuator {

	public static void main (String[] args) throws IOException {
		//File file = new File("test_forest.txt");
		//File file = new File("test_forest");
		File file = new File("simple forest.txt");
		FileInputStream stream = new FileInputStream(file);
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		try {
			MyWoodLoader wood_loader = new MyWoodLoader();
			MyWood wood = (MyWood)wood_loader.Load(stream);
			//MyPrintableWood pr_wood = (MyPrintableWood)wood_loader.Load(stream,os);
			System.out.print("Please, write the start & finish points in format: start.x_start.y_finish.x_finish.y\n");
			Scanner n = new Scanner( System.in );
			int a = n.nextInt();
			int b = n.nextInt();
			int c = n.nextInt();
			int d = n.nextInt();
			Point start = new Point(a,b);
			Point finish = new Point (c,d);
			System.out.print("Please, write the name of mau5\n");
			n = new Scanner( System.in );
			String name = n.nextLine();
			MyWoodman mau5 = new MyWoodman(name,start); 
			wood.createWoodman(name,start,finish);
			//pr_wood.createWoodman(name,start,finish);
			Deadmau5 deadmau5 = new Deadmau5();
			Action action = Action.Ok;
			mau5.MoveToStart();
			while (true) {
				if (action == Action.Finish || action == Action.WoodmanNotFound) {
					break;
				}
				Direction direction = deadmau5.NextMove(action);
				action = wood.move(name, direction);
				//action = pr_wood.move(name, direction);
				//deadmau5.printMap();
				System.out.println(direction);
			}
			if (action == Action.Finish) {
				System.out.println("Mau5 finished!");
			}
			if (action == Action.WoodmanNotFound) {
				System.out.println("POTRA4ENO");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			os.close();
			stream.close();
		}
	}
}