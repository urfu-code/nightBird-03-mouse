
public class Actuator {
	public static void main (String[] args) {
		MyWoodLoader loader = new MyWoodLoader();
		try {
			PrintableWood pw = (PrintableWood) loader.prWood(System.out);
			pw.createWoodman("venera", new Point (1, 1), new Point (8, 6));
			MyMouse mouse = new MyMouse();
			Action action = Action.Ok;
			while (true) {
				Thread.sleep(500);
				if(action == Action.WoodmanNotFound){
					System.out.print("dead");
					break;
				}
				if(action == Action.Finish){
					System.out.print("finish");
					break;
				}
				action = pw.move("venera", mouse.NextMove(action));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
