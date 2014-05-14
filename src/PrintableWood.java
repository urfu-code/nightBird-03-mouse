import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;


public class PrintableWood extends MyWood {

	private OutputStreamWriter out;

	public PrintableWood(char[][] wood, int width, int height, OutputStream stream) {
		super(wood, width, height);
		try {
			this.out = new OutputStreamWriter(stream, System.getProperty("file.encoding"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void createWoodman (String name, Point start, Point finish) throws IOException {
		super.createWoodman(name, start, finish);
		printWood();
	}
	
	@Override
	public Action move (String name, Direction direction) {
		Action action = super.move(name, direction);
		printWood();
		return action;
	}

	private void printWood() {
		try {
			for (int j = 0; j < heightWood; j++) {
				for (int i = 0; i < widthWood; i++)
					for (MyWoodman elem : m_woodmanList.values()) {
						if (i == elem.GetLocation().getX() && j == elem.GetLocation().getY()) {
							out.write(elem.GetName().charAt(0));
						} else {
							out.write(toChangeSymbol(m_wood[i][j]));
						}
					}
				out.write(System.lineSeparator());
			}
			for (MyWoodman elem : m_woodmanList.values()) {
				out.write(elem.GetName().charAt(0) + " - " + elem.GetName() + 
						" (" + elem.GetLifeCount() + " lives)" + System.lineSeparator());
			}
//			out.write("♥ - life" + System.lineSeparator());
//			out.write("▒ - kill" + System.lineSeparator());
			out.write(System.lineSeparator());
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private char toChangeSymbol(char c) {
		if (c == '1') return '█';
		if (c == '0') return ' ';
		if (c == 'L') return '♥';
		if (c == 'K') return '▒';
		return c;
	}
}
