package defpac;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class MyPrintableWood extends MyWood {
	private OutputStream output;

	public MyPrintableWood(char[][] m_wood, OutputStream output) {
		super(m_wood);
		this.output = output;
	}

	/*
	 * private void printWood() { ByteArrayOutputStream stream = new...();
	 * stream.write(123); byte[] array = stream.toByteArray(); }
	 */

	public void printWood() throws IOException {
		PrintWriter out = new PrintWriter(
				new OutputStreamWriter(
				new BufferedOutputStream(output)));
		try {
		int IdxSymbols = 1;
		Map<String, String> code = new HashMap<String, String>();
		String symbols = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		for (MyWoodman man : m_woodmanList.values()) {
			code.put(man.GetName(), symbols.substring(IdxSymbols - 1,
					IdxSymbols++));
		}
		for (int j = 0; j < m_wood.length; j++) {
			for (int i = 0; i < m_wood[0].length; i++) {
				boolean player = false;
				for (MyWoodman man : m_woodmanList.values()) {
					Point point = new Point(i, j);
					if (man.GetLocation().equals(point)) {
						System.out.print(code.get(man.GetName()));
						//out.write((code.get(man.GetName())).getBytes());
						out.print((code.get(man.GetName())).getBytes());
						player = true;
					}
				}
				if (player == false) {
					switch (m_wood[j][i]) {
					case '1':
						String symbol = "*";
						if (i == 0 && j == 0) symbol = "╔";
						if (i == m_wood[0].length - 1 && j == 0) symbol = "╗";
						if (i == 0 && j == m_wood.length - 1) symbol = "╚";
						if (i == m_wood[0].length - 1 && j == m_wood.length - 1) symbol = "╝";
						
						if (i == 0 && j != m_wood.length - 1 && j != 0) {
							symbol = "║";
							if (m_wood[j][i+1] == 1 && m_wood[j-1][i] == 1 && m_wood[j+1][i] == 1) symbol = "╠";
							if (m_wood[j][i+1] == 1 && m_wood[j-1][i] == 1 && m_wood[j+1][i] != 1) symbol = "╚";
							if (m_wood[j][i+1] == 1 && m_wood[j-1][i] != 1 && m_wood[j+1][i] == 1) symbol = "╔";
							//if (m_wood[j][i+1] != 1 && m_wood[j-1][i] == 1 && m_wood[j+1][i] == 1) symbol = "║";
							if (m_wood[j][i+1] == 1 && m_wood[j-1][i] != 1 && m_wood[j+1][i] != 1) symbol = "═";
						}
						if (j == 0 && i != m_wood[0].length - 1 && i != 0) {
							symbol = "═";
							if (m_wood[j+1][i] == 1 && m_wood[j][i-1] == 1 && m_wood[j][i+1] == 1) symbol = "╦";
							if (m_wood[j+1][i] == 1 && m_wood[j][i-1] == 1 && m_wood[j][i+1] != 1) symbol = "╗";
							if (m_wood[j+1][i] == 1 && m_wood[j][i-1] != 1 && m_wood[j][i+1] == 1) symbol = "╔";
							if (m_wood[j][i+1] != 1 && m_wood[j][i-1] != 1 && m_wood[j+1][i] == 1) symbol = "║";
							//if (m_wood[j][i+1] == 1 && m_wood[j][i-1] == 1 && m_wood[j+1][i] != 1) symbol = "═";
						}
						if (j == m_wood.length - 1 && i != 0 && i != m_wood[0].length - 1) {
							symbol = "═";
							if (m_wood[j-1][i] == 1 && m_wood[j][i-1] == 1 && m_wood[j][i+1] == 1) symbol = "╩";
							if (m_wood[j-1][i] == 1 && m_wood[j][i-1] == 1 && m_wood[j][i+1] != 1) symbol = "╝";
							if (m_wood[j-1][i] == 1 && m_wood[j][i-1] != 1 && m_wood[j][i+1] == 1) symbol = "╚";
							if (m_wood[j][i+1] != 1 && m_wood[j][i-1] != 1 && m_wood[j-1][i] == 1) symbol = "║";
							//if (m_wood[j][i+1] == 1 && m_wood[j][i-1] == 1 && m_wood[j-1][i] != 1) symbol = "═";
						}
						if (i == m_wood[0].length - 1 && j != 0 && j != m_wood.length - 1) {
							symbol = "║";
							if (m_wood[j-1][i] == 1 && m_wood[j+1][i] == 1 && m_wood[j][i-1] == 1) symbol = "╣";
							if (m_wood[j-1][i] == 1 && m_wood[j][i-1] == 1 && m_wood[j+1][i] != 1) symbol = "╝";
							if (m_wood[j-1][i] != 1 && m_wood[j][i-1] == 1 && m_wood[j+1][i] == 1) symbol = "╗";
							//if (m_wood[j-1][i] == 1 && m_wood[j][i-1] != 1 && m_wood[j+1][i] == 1) symbol = "║";
							if (m_wood[j-1][i] != 1 && m_wood[j][i-1] == 1 && m_wood[j][i+1] == 1) symbol = "═";
						}
						
						if (j != 0 && j != m_wood.length - 1 && i != 0 && i != m_wood[0].length - 1) {
							if ((m_wood[j-1][i] == 1 && m_wood[j+1][i] == 1 && m_wood[j][i-1] == 1 && m_wood[j][i+1] == 1)||
							(m_wood[j-1][i] != 1 && m_wood[j+1][i] != 1 && m_wood[j][i-1] != 1 && m_wood[j][i+1] != 1)) symbol = "╬";
							if (m_wood[j-1][i] != 1 && m_wood[j+1][i] == 1 && m_wood[j][i-1] == 1 && m_wood[j][i+1] == 1) symbol = "╦";
							if (m_wood[j-1][i] == 1 && m_wood[j+1][i] != 1 && m_wood[j][i-1] == 1 && m_wood[j][i+1] == 1) symbol = "╩";
							if (m_wood[j-1][i] == 1 && m_wood[j+1][i] == 1 && m_wood[j][i-1] != 1 && m_wood[j][i+1] == 1) symbol = "╠";
							if (m_wood[j-1][i] == 1 && m_wood[j+1][i] == 1 && m_wood[j][i-1] == 1 && m_wood[j][i+1] != 1) symbol = "╣";							
							if (m_wood[j-1][i] != 1 && m_wood[j+1][i] != 1 && m_wood[j][i-1] == 1 && m_wood[j][i+1] == 1) symbol = "═";
							if (m_wood[j-1][i] == 1 && m_wood[j+1][i] != 1 && m_wood[j][i-1] != 1 && m_wood[j][i+1] == 1) symbol = "╚";
							if (m_wood[j-1][i] == 1 && m_wood[j+1][i] == 1 && m_wood[j][i-1] != 1 && m_wood[j][i+1] != 1) symbol = "║";
							if (m_wood[j-1][i] != 1 && m_wood[j+1][i] == 1 && m_wood[j][i-1] == 1 && m_wood[j][i+1] != 1) symbol = "╗";
							if (m_wood[j-1][i] != 1 && m_wood[j+1][i] == 1 && m_wood[j][i-1] != 1 && m_wood[j][i+1] == 1) symbol = "╔";
							if (m_wood[j-1][i] != 1 && m_wood[j+1][i] == 1 && m_wood[j][i-1] == 1 && m_wood[j][i+1] != 1) symbol = "╝";
						}
						//System.out.print(symbol);
						//out.write(symbol.getBytes());
						System.out.print(symbol);
						//out.write(symbol.getBytes());
						out.print(symbol.getBytes());
						break;
					case '0':
						System.out.print(" ");
						//out.write((" ").getBytes());
						out.print((" ").getBytes());
						break;
					case 'K':
						System.out.print("†");
						//out.write(("†").getBytes());
						out.print(("†").getBytes());
						break;
					case 'L':
						System.out.print("♥");
						//out.write(("♥").getBytes());
						out.print(("♥").getBytes());
						break;
					default:
						break;
					}
				}
			}
			System.out.print("\n");
			//out.write(("\n").getBytes());
			out.print(("\n").getBytes());
		}
		System.out.print("\n♥ - life\n† - death\n");
		//out.write(("\n♥ - life\n† - death\n").getBytes());
		out.print(("\n♥ - life\n† - death\n").getBytes());
		// предполагаем, что игроков не более 26 :-)
		for (MyWoodman man : m_woodmanList.values()) {
			System.out.print(code.get(man.GetName()) + " - " + man.GetName()
					+ " , lifes: " + man.GetLifeCount() + "\n");
			//out.write((code.get(man.GetName()) + " - " + man.GetName()
			out.print((code.get(man.GetName()) + " - " + man.GetName()
					//	+ " , lifes: " + man.GetLifeCount() + "\n").getBytes());
					+ " , lifes: " + man.GetLifeCount() + "\n").getBytes());
		}
		} finally {
			out.close();
		}
	}

	@Override
	public void createWoodman(String name, Point start, Point finish) throws IOException {
		super.createWoodman(name, start, finish);
		printWood();
	}

	@Override
	public Action move(String name, Direction direction) throws IOException {
		Action action = super.move(name, direction);
		printWood();
		return action;
	}
}