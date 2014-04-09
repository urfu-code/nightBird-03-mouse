package defpac;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class Actuator {
	
	public static void main (String[] args) {
		File file = new File("forest.txt");
		try {
			FileInputStream stream = new FileInputStream(file);
			MyWoodLoader wood_loader = new MyWoodLoader();
			MyPrintableWood wood = (MyPrintableWood)wood_loader.Load(stream);
			System.out.print("Please, write the start & finish points in format: start.x_start.y_finish.x_finish.y\n");
			Scanner n = new Scanner( System.in );
	        int a = n.nextInt();
	        int b = n.nextInt();
	        int c = n.nextInt();
	        int d = n.nextInt();
			Point start = new Point(b,a);
			Point finish = new Point (d,c);
			System.out.print("Please, write the name of mau5\n");
			n = new Scanner( System.in );
			String name = n.nextLine();
			MyWoodman mau5 = new MyWoodman(name,start); 
			wood.createWoodman(name,start,finish);
			Deadmau5 deadmau5 = new Deadmau5();
			Action action = Action.Fail;
			mau5.MoveToStart();
			while (true) {
 				if ((action == Action.WoodmanNotFound)||(action == Action.Finish)) {
 					break;
 				}
 				action = wood.move(name, deadmau5.NextMove(action));
 			}
 			if (action == Action.Finish) {
 				System.out.println("Mau5 finished, it's still alive!");
 			}
 			else {
 				System.out.println("Mau5 went to hell!");
 			}
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
}