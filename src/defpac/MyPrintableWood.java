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

	public void printWood() throws IOException {
		PrintWriter out = new PrintWriter(
				new OutputStreamWriter(
				new BufferedOutputStream(output)));
		try {
			Map<String, String> code = new HashMap<String, String>();
			String symbols = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
			boolean[] emptySymbol = new boolean[symbols.length()];
			for (int Idx = 0; Idx < symbols.length(); Idx++) {
				emptySymbol[Idx] = true;
			}
			for (MyWoodman man : m_woodmanList.values()) {
				int Idx = 0;
				while (emptySymbol[Idx] != true) {
					Idx++;
				}
				code.put(man.GetName(), symbols.substring(Idx, Idx+1));
				emptySymbol[Idx] = false;
			}
			for (int j = 0; j < m_wood.length; j++) {
				for (int i = 0; i < m_wood[0].length; i++) {
					boolean player = false;
					for (MyWoodman man : m_woodmanList.values()) {
						Point point = new Point(i, j);
						if (man.GetLocation().equals(point)) {
							System.out.print(code.get(man.GetName()));
							out.print((code.get(man.GetName())).getBytes());
							player = true;
						}
					}
					if (player == false) {
						switch (m_wood[j][i]) {
						case '1':
							String symbol = "";
							String map_Symbols = "╬║║║═╔╚╠═╗╝╣═╦╩╬";
							int wall = 0;
							if (i != 0 && m_wood[j][i-1] == '1') wall += 8;
							if (i != m_wood[0].length - 1 && m_wood[j][i+1] == '1') wall += 4;
							if (j != 0 && m_wood[j-1][i] == '1') wall += 2;
							if (j != m_wood.length - 1 && m_wood[j+1][i] == '1') wall += 1;
							symbol += map_Symbols.substring(wall,wall+1);
							System.out.print(symbol);
							out.print(symbol.getBytes());
							break;
						case '0':
							System.out.print(" ");
							out.print((" ").getBytes());
							break;
						case 'K':
							System.out.print("†");
							out.print(("†").getBytes());
							break;
						case 'L':
							System.out.print("♥");
							out.print(("♥").getBytes());
							break;
						default:
							break;
						}
					}
				}
				System.out.print("\n");
				out.print(("\n").getBytes());
			}
			System.out.print("\n♥ - life\n† - death\n");
			out.print(("\n♥ - life\n† - death\n").getBytes());
			// предполагаем, что игроков не более 52 :-)
			for (MyWoodman man : m_woodmanList.values()) {
				System.out.print(code.get(man.GetName()) + " - " + man.GetName()
						+ " , lifes: " + man.GetLifeCount() + "\n");
				out.print((code.get(man.GetName()) + " - " + man.GetName()
						+ " , lifes: " + man.GetLifeCount() + "\n").getBytes());
			}
			for (int Idx = 0; Idx < symbols.length(); Idx++) {
				emptySymbol[Idx] = true;
			}
			for (MyWoodman man : m_woodmanList.values()) {
				char letter = code.get(man.GetName()).charAt(0);
				int Idx = 0;
				while (letter != symbols.charAt(Idx))
					Idx++;
				emptySymbol[Idx] = false;
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