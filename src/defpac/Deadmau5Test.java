package defpac;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import junit.framework.TestCase;

public class Deadmau5Test extends TestCase {

	public void testDeadmau5() {
		Deadmau5 mau5 = new Deadmau5();
		assertEquals(mau5.X, 3);
		assertEquals(mau5.Y, 3);
	}

	public void testNextMove() throws IOException {
		Deadmau5 mau5 = new Deadmau5();
		File file = new File ("simple forest.txt");
		FileInputStream stream = new FileInputStream(file);
		MyWoodLoader wood_loader = new MyWoodLoader();
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		MyPrintableWood wood = wood_loader.Load(stream, output);
		Action action = Action.Ok;
		String[] directions = {"Down","Down","Down","Down","Down","Right","Right","Right","Down","Up","Up","Up","Up","Up","Right",
				"None","None","None","None","Right","Right","None","None","Right","Right","Down","Down","Down","Down","Down","Right",
				"Left","Up","Right","Left","Up","Right","Left","Up","Right","Up","Up","Left","Up","Left","Down","Down","Down","Down"};
		int Idx = 0;
		wood.createWoodman("Deadmau5", new Point(1, 1), new Point(5, 5));
		while ((action != Action.WoodmanNotFound)&&(action != Action.Finish)) {
			Direction direction = mau5.NextMove(action);
			action = wood.move("Deadmau5", direction);
			assert (directions[Idx++] == direction.toString());	
		}
	}

}
