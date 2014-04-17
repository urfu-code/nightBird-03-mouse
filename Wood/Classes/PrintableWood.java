package Classes;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

import Enums.Action;
import Enums.Direction;
import Exceptions.UnexceptableNameException;

// up, right, down, left
public class PrintableWood extends Wood {

	private HashMap<String, Character> m_borders;
	private char[][] m_wood;	//Исходный лес из 0 и 1
	private OutputStream m_oStream;		//Поток вывода
	private HashMap<String, Character> m_players;	//Массив имен игроков
	private HashSet<Character> m_freeSymbolsOfPlayers;		//Массив картинок для игроков
	private char[][] m_WoodForPrint;		//Лес из псевдографики
	
	private void makeBorders() {
		m_borders = new HashMap<>();
		m_borders.put("1111", '┼');
		m_borders.put("1110", '├');
		m_borders.put("1101", '┴');
		m_borders.put("1100", '└');
		m_borders.put("1011", '┤');
		m_borders.put("1010", '│');
		m_borders.put("1001", '┘');
		m_borders.put("1000", '│');
		m_borders.put("0111", '┬');
		m_borders.put("0110", '└');
		m_borders.put("0101", '─');
		m_borders.put("0110", '┌');
		m_borders.put("0100", '─');
		m_borders.put("0011", '┐');
		m_borders.put("0010", '│');
		m_borders.put("0001", '─');
		m_borders.put("0000", '+');
		
	}
	
	public PrintableWood(char[][] wood, OutputStream oStream) {
		super(wood);
		m_wood=wood;
		m_freeSymbolsOfPlayers =  new HashSet<>();
		m_freeSymbolsOfPlayers.addAll(Arrays.asList('@','♗','♖','♣','★','✖','♘','♔','♆','♀','⌘','Ω','Ѱ'));
		m_players = new HashMap<>(m_freeSymbolsOfPlayers.size());
		m_oStream = oStream;
		m_WoodForPrint = new char[wood.length][wood[0].length];
		makeBorders();
		
		for (int x=0; x<m_WoodForPrint.length; x++)
			for (int y=0; y<m_WoodForPrint[0].length; y++) {
				m_WoodForPrint[x][y] = getSymbol(x, y);
			}
		write();
	}
	
	private void write() {
		OutputStreamWriter writer = new OutputStreamWriter(m_oStream);
		try {
			for (int i=0; i<m_WoodForPrint[0].length; i++){
				for (int j=0; j<m_WoodForPrint.length; j++)
					writer.write(m_WoodForPrint[j][i]);
				writer.write('\n');
			}
			for (String playerName: m_players.keySet()) {
				writer.write(m_players.get(playerName));
				writer.write(" - ");
				writer.write(playerName);
				writer.write("; (Жизней: ");
				writer.write(String.valueOf(super.getLifeCount(playerName)));
				writer.write(")");
				writer.write('\n');
			}
			writer.write('\n');
			writer.flush();
				
		} catch (IOException e) {
			System.out.println("ERROR" + e.getMessage());
			e.printStackTrace();
		}
	}
	private char getSymbol(int x, int y) {
		char live = '♥';
		char trap = '☢';
		if (m_wood[x][y] == 'L')
			return live;
		if (m_wood[x][y] == 'K')
			return trap;
		if (m_wood[x][y] == '0')
			return ' ';
		StringBuilder str = new StringBuilder();
		//UP
		if (isBorder(x, y-1))
			str.append("1");
		else
			str.append("0");
		//RIGHT
		if (isBorder(x+1, y))
			str.append("1");
		else
			str.append("0");
		//DOWN
		if (isBorder(x, y+1))
			str.append("1");
		else
			str.append("0");
		//LEFT
		if (isBorder(x-1, y))
			str.append("1");
		else
			str.append("0");
		return m_borders.get(str.toString());
	}
	private boolean isBorder(int x, int y) {
		try {
			if (m_wood[x][y] == '1')
				return true;
			else
				return false;
		} catch (ArrayIndexOutOfBoundsException e) {
			return false;
		}
	}

	@Override
	public void createWoodman(String name, Point start, Point finish) throws UnexceptableNameException {
		Iterator<Character> itr = m_freeSymbolsOfPlayers.iterator();
		if (!itr.hasNext()) {
			throw new RuntimeException("Превышено максимальное количество игроков");
		}
		super.createWoodman(name, start, finish);
		Character symbol = itr.next();
		m_freeSymbolsOfPlayers.remove(symbol);
		m_players.put(name, symbol);
		m_WoodForPrint[start.getX()][start.getY()] = symbol;
		write();
	}
	
	@Override
	public Action move(String name, Direction direction) {
		Point lastLocation = super.getLocation(name);
		//Кладем в точку то, что изначально там было
		m_WoodForPrint[lastLocation.getX()][lastLocation.getY()] = getSymbol(lastLocation.getX(), lastLocation.getY());
		Action result = super.move(name, direction);
		//Если игрок умер или неправильное имя - то просто выходим
		if (result == Action.WoodmanNotFound) {
			m_freeSymbolsOfPlayers.add(m_players.get(name));
			m_players.remove(name);
			//write();
			return result;
		}
		Point newLocation = super.getLocation(name);
		//Кладем в новое местоположение игрока его рисунок
		m_WoodForPrint[newLocation.getX()][newLocation.getY()] = m_players.get(name);
		write();
		return result;
	}
}
