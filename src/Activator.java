import java.io.File;
import java.io.FileInputStream;
import java.util.Scanner;


public class Activator {

	public static void main(String[] args) throws Exception {
		File file = new File ("wood.txt");
		FileInputStream stream = new FileInputStream(file);
		MyWoodLoader loder = new MyWoodLoader();
		PrintableWood wood = loder.Load(stream, System.out);
		Scanner scanner = new Scanner(System.in);
		System.out.println("Name:\n");
		String name = scanner.nextLine();
		System.out.println("Start point:\n");
		int x_start = scanner.nextInt();
		int y_start = scanner.nextInt();
		System.out.println("Finish point:\n");
		int x_finish = scanner.nextInt();
		int y_finish = scanner.nextInt();
		Point start = new Point(x_start, y_start);
		Point finish = new Point(x_finish,y_finish);
		
		Action currentAction = Action.Ok;
		wood.createWoodman(name, start, finish);
		MyMouse mouse = new MyMouse();
		while (true) {
			if (currentAction == Action.Finish) {
				System.out.println(name+" came to the Finish!");
				break;
			}
			if (currentAction == Action.WoodmanNotFound) {
				System.out.println(name+"'s dead.");
				break;
			}
			Direction currentDirection = mouse.NextMove(currentAction);
			currentAction = wood.move(name, currentDirection);
		} 
		scanner.close();
	}
}
