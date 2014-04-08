import java.io.FileInputStream;

import Bots.Mouse;
import WoodEngine.Action;
import WoodEngine.Point;
import WoodEngine.PrintableWood;
import WoodEngine.WoodLoader;


public class Activator {

	public static void main(String[] args) {
		WoodLoader wl = new WoodLoader();
		try {
			PrintableWood pw = (PrintableWood) wl.LoadPrntbleWood(new FileInputStream("maze"), System.out);
			pw.createWoodman("penyokk", new Point(1, 1), new Point(6, 6));
			Mouse penyokkM = new Mouse("penyokk");
			Action act = Action.Ok;
			int steps = 0;
			while(true){
				if(act == Action.WoodmanNotFound){
					System.out.println("DEAD");
					break;
				}
				if(act == Action.Finish){
					System.out.println("FINISH");
					break;
				}
				act = pw.move("penyokk", penyokkM.NextMove(act));
				steps++;
			}
			System.out.println(steps);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
