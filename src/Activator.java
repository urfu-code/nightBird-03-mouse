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
		System.out.println("Введите имя игрока:\n");
		String name = scanner.nextLine();
		System.out.println("Введите координаты точки старта:\n");
		int x_start = scanner.nextInt();
		int y_start = scanner.nextInt();
		System.out.println("Введите координаты точки финиша:\n");
		int x_finish = scanner.nextInt();
		int y_finish = scanner.nextInt();
		Point start = new Point(x_start, y_start);
		Point finish = new Point(x_finish,y_finish);
		
		Action currentAction = Action.Ok;
		wood.createWoodman(name, start, finish);
		MyMouse mouse = new MyMouse();
		if ((currentAction != Action.WoodmanNotFound)&&(currentAction != Action.Finish)) {
			currentAction = wood.move(name, mouse.NextMove(currentAction));
		} else {	
			if (currentAction == Action.Finish) {
				System.out.println(name+", ты дошел до финиша.");
			} else {
				System.out.println(name+", ты програл.");
			}
		}
	}
}
