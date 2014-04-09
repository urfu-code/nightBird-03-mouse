import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Iterator;

public class PrintableWood extends Wood {
	private HashMap<String, Character> namesAndSymbols = new HashMap<String, Character>(); // для легенды
	private final Character[] symbols = {'☺', '☻', 'Ò', 'Ó', 'Ô', 'Õ', 'Ö'};
	private boolean[] busiedSymbols = new boolean[symbols.length]; // чтобы учитывать освободившиеся символы в случае окончательной смерти
	HashMap<Point, Character> labyrinthOfSymbols = new HashMap<Point, Character>(); // отрисовываемый лабиринт
	private static HashMap<Point, Character> initialLabyrinth; // изначальное представление лабиринта, не меняется
	private HashMap<Character, Point> locationOfSymbols = new HashMap<Character, Point>(symbols.length); // для запоминания позиций картинок, чтоб при наложении не терять
	PrintStream ps;

	@Override
	Character getChar(Point location) {
		return initialLabyrinth.get(location);
	}

	PrintableWood(HashMap<Point, Character> lab, int length, int width, OutputStream out) throws IOException {
		super(lab, length, width);
		ps = new PrintStream(out);

		initialLabyrinth = new HashMap<Point, Character>(lab);
		labyrinthOfSymbols = lab;

		for (int i = 0; i < busiedSymbols.length; i++) {
			busiedSymbols[i] = false;
		}

		printLabyrinth();
	}

	@Override
	public void createWoodman(String name, Point start, Point finish) {
		// сначала проверяем, есть ли свободные картинки
		int counterOfSymbols;
		for (counterOfSymbols = 0; counterOfSymbols < busiedSymbols.length; counterOfSymbols++) {
			if (busiedSymbols[counterOfSymbols] == false)
				break;
		}
		if (counterOfSymbols == symbols.length)
			throw new NotEnoughSymbolsException("Картинки для вудменов закончились, убейте кого-то из вудменов");

		super.createWoodman(name, start, finish);

		namesAndSymbols.put(name, symbols[counterOfSymbols]); // взяли изображение вудмена, проассоциировали с именем
		busiedSymbols[counterOfSymbols] = true;	
		labyrinthOfSymbols.put(start, namesAndSymbols.get(name)); // поместили изображение на указаную точку
		locationOfSymbols.put(namesAndSymbols.get(name), start);

		printLabyrinth();
	}


	@Override
	public Action move(String name, Direction direction) {
		return super.move(name, direction);
	}

	/**
	 * проверяет, есть ли на точке, с которой уходит вудмен, другой вудмен, и отрисовывает либо его, либо элемент лабиринта   
	 * @param currentLocation
	 */
	private void ifThereisAnotherWoodman (Point currentLocation) {
		locationOfSymbols.remove(labyrinthOfSymbols.get(currentLocation));
		boolean isThereAnotherWoodman = false;
		Iterator<Character> keySetIterator = locationOfSymbols.keySet().iterator();
		while (keySetIterator.hasNext()) {
			Character key = keySetIterator.next();
			if (locationOfSymbols.get(key).equals(currentLocation)) {
				labyrinthOfSymbols.put(currentLocation, key);
				isThereAnotherWoodman = true;			
				break;
			}
		}
		if (isThereAnotherWoodman == false)
			labyrinthOfSymbols.put(currentLocation, associations(currentLocation));
	}

	@Override
	public Action result(Point currentLocation, String name, Point newLocation) {
		if (newLocation.equals(getWoodman(name).finish)) {
			ifThereisAnotherWoodman(currentLocation);
			getWoodman(name).SetLocation(newLocation);
			locationOfSymbols.put(namesAndSymbols.get(name), newLocation);
			labyrinthOfSymbols.put(newLocation, namesAndSymbols.get(name));
			printLabyrinth();
			return Action.Finish;
		}
		switch (getChar(newLocation)) {
		case ' ' : 
			ifThereisAnotherWoodman(currentLocation);
			getWoodman(name).SetLocation(newLocation);
			locationOfSymbols.put(namesAndSymbols.get(name), newLocation);
			labyrinthOfSymbols.put(newLocation, namesAndSymbols.get(name));
			printLabyrinth();
			return Action.Ok;

		case '◘' : 
			if (getChar(currentLocation) == '‡') {
				if (!getWoodman(name).Kill()) {
					listOfWoodmen.remove(name);
					int i = 0;
					for (i = 0; i < symbols.length; i++) {
						if (symbols[i].equals(namesAndSymbols.get(name)))
							break;
					}
					busiedSymbols[i] = false;
					namesAndSymbols.remove(name);				
					return Action.WoodmanNotFound;
				}
			}
			if (getChar(currentLocation) == '♥')
				getWoodman(name).AddLife();
			printLabyrinth();
			return Action.Fail;

		case '♥' :
			ifThereisAnotherWoodman(currentLocation);
			getWoodman(name).SetLocation(newLocation);
			locationOfSymbols.put(namesAndSymbols.get(name), newLocation);
			labyrinthOfSymbols.put(newLocation, namesAndSymbols.get(name));
			getWoodman(name).AddLife();
			printLabyrinth();
			return Action.Life;

		case '‡' :
			ifThereisAnotherWoodman(currentLocation);
			getWoodman(name).SetLocation(newLocation);
			if (getWoodman(name).Kill()) {
				locationOfSymbols.put(namesAndSymbols.get(name), newLocation);
				labyrinthOfSymbols.put(newLocation, namesAndSymbols.get(name));
				printLabyrinth();
				return Action.Dead;
			}
			else {
				listOfWoodmen.remove(name);
				// надо зафиксировать, что картинка освободилась
				int i = 0;
				for (i = 0; i < symbols.length; i++) {
					if (symbols[i].equals(namesAndSymbols.get(name)))
						break;
				}
				busiedSymbols[i] = false;
				namesAndSymbols.remove(name);
				printLabyrinth();
				return Action.WoodmanNotFound;
			}		
		}
		return Action.Ok;
	}

	private void printLabyrinth() {
		for (int y = 0; y < labWidth; y++) {
			for (int x = 0; x < labLength; x++) {
				ps.print(labyrinthOfSymbols.get(new Point(x, y)));
			}
			ps.print(System.getProperty("line.separator"));
		}
		ps.println("♥ - жизнь");
		ps.println("‡ - капкан");
		if (!namesAndSymbols.isEmpty()) {
			Iterator<String> keySetIterator = namesAndSymbols.keySet().iterator();
			while (keySetIterator.hasNext()) {
				String key = keySetIterator.next();
				ps.println(namesAndSymbols.get(key) + " - " + key + " (количество жизней: " + getWoodman(key).GetLifeCount() + ")");
			}
		}
	}

	/**
	 * 
	 * @param currentLocation текущая точка/ точка, с которой собираемся уйти
	 * @return соответствующий текущей точке символ-картинку в оригиналньом лабиринте
	 */
	private Character associations(Point currentLocation) {
		return initialLabyrinth.get(currentLocation);

	}

}
