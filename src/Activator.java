import java.io.FileInputStream;

import Bot.Mouse;
import WoodEngine.Action;
import WoodEngine.Point;
import WoodEngine.PrintableWood;
import WoodEngine.WoodLoader;


public class Activator {

	public static void main(String[] args) {
		WoodLoader wl = new WoodLoader();
		try {
			PrintableWood pw = (PrintableWood) wl.LoadPrntbleWood(new FileInputStream("maze"), System.out);
			pw.createWoodman("penyokk", new Point(17, 13), new Point(1, 13));
			//pw.createWoodman("penyokk", new Point(1, 1), new Point(6, 6));
			Mouse penyokkM = new Mouse("penyokk");
			Action act = Action.Ok;
			int steps = 0;
			while(true){
				if(act == Action.WoodmanNotFound){
					System.out.print("DEAD");
					break;
				}
				if(act == Action.Finish){
					System.out.print("FINISH");
					break;
				}
				act = pw.move("penyokk", penyokkM.NextMove(act));
				steps++;
			}
			System.out.println(" in " + steps);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
