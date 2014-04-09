import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.CodeSource;


public class Actuator {
	
	public static void main(String[] args) throws IOException, URISyntaxException {
		String name = "Norushka";
		CodeSource codeSource = Actuator.class.getProtectionDomain().getCodeSource();

		File file = new File(new File(codeSource.getLocation().toURI().getPath()).getParentFile().getPath(), "src/input.txt");
		try {
			FileInputStream stream = new FileInputStream(file);
			try {
				PrintableWoodLoader wl = new PrintableWoodLoader();
				PrintableWood wood = wl.Load(stream);

				wood.createWoodman(name, new Point(1, 1), new Point (7, 2));
				Mouse norushka = new Mouse(name);
				Direction latestDirection = norushka.NextMove(Action.Ok); // первый шаг, мышь решает, куда идти после него
				Direction newDirection = Direction.None;
				Action newAction = Action.Ok;

				while (wood.getWoodman(name).GetLifeCount() >= 0 && !(newAction == Action.Finish)) {
					newAction = wood.move(name, latestDirection); // берём последнее направление, выбранное мышью, передаём вудману, получаем статус
					newDirection = norushka.NextMove(newAction); // статус отдаём мыши, получаем новое направление					
					latestDirection = newDirection; // делаем новое направление старым и идём на новый круг
					if (wood.getWoodman(name).GetLifeCount() < 2)
						norushka.nameOfAlghoritm = "Back to life";				
				}
				
				if (wood.getWoodman(name).GetLifeCount() < 0)
					System.out.println("Мышь умерла");
				if (newAction == Action.Finish)
					System.out.println("Финиш!");
			} 
			finally {
				stream.close();
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
