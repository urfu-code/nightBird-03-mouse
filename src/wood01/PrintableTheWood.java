package wood01;

import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;



public class PrintableTheWood extends TheWood {
	//'┌','─','┬','┐','│','┼','┤','├','└','─','┴','┘','♥','Ⓣ',' ','□'
    //'α','β','γ','δ','ε','ζ','η','θ','ι','κ','λ','μ','ν','ξ','ο','π','ρ','ς','σ','τ','υ','φ','χ','ψ','ω'	
	private Map<String,Character>graphList;
	private Map<String,Character>woodmanNames;
	private char[] nameList;
	private OutputStream stream;
	
	private char getElement(int i,int j) {
		return wood[i][j];
	}
	
	public PrintableTheWood(char[][] _wood,OutputStream _stream) throws Exception {
		super(_wood);
		stream = _stream;
		graphList = new HashMap<String,Character>();
		woodmanNames = new HashMap<String,Character>();
		graphList.put("U", '│');
		graphList.put("D", '│');
		graphList.put("L", '─');
		graphList.put("R", '─');
		graphList.put("UD", '│');
		graphList.put("LR", '─');
		graphList.put("DR",'┌');
		graphList.put("LDR",'┬');
		graphList.put("LD",'┐');
		graphList.put("ULDR",'┼');
		graphList.put("ULD",'┤');
		graphList.put("UDR",'├');
		graphList.put("UR",'└');
		graphList.put("ULR",'┴');
		graphList.put("UL",'┘');
		graphList.put("Life",'♥');
		graphList.put("0",' ');
		graphList.put("A",'□');
		graphList.put("Trap",'ѻ');
		nameList = new char[]{'α','β','γ','δ','ε','ζ','η','θ','ι','κ','λ','μ','ν','ξ','ο','π','ρ','ς','σ','τ','υ','φ','χ','ψ','ω'};	
	}	
	private void printWood(OutputStream stream) throws Exception
	{
		char[] line = new char[wood[0].length];
		for (int i = 0; i < wood.length; i++) {
			for (int j = 0; j < wood[0].length; j++) {
				line[j] = findElement(i,j);
			}
			String str = new String(line);
			stream.write((str + "\n").getBytes());
		}
		stream.write("------------\n".getBytes());
		stream.write(("♥" + " - live\n").getBytes());
		stream.write(("ѻ" + " - trap\n").getBytes());
		stream.write("------------\n".getBytes());
		for (TheWoodman i:woodmans.values()) {
			stream.write((i.GetName() + " (" + getWoodmanName(i.GetName()) + ")" + " - " + i.GetLifeCount() + " live(s)\n").getBytes());
		}
	}
	private char getWoodmanName(String name) throws Exception {
		if (woodmanNames.containsKey(name)) {
			return woodmanNames.get(name);
		}
		for (int i = 0; i < nameList.length; i++) {
			if (nameList[i] != 'N') {
				woodmanNames.put(name, nameList[i]);
				nameList[i] = 'N';
				return woodmanNames.get(name);
			}
		}
		throw new Exception("обозначалки для персонажей кончились :((");
	}
	
	private char findElement(int line, int column) throws Exception
	{
		Point currentPoint = new Point(column,line);
		for (TheWoodman i:woodmans.values()) {
			if (i.GetLocation().equals(currentPoint)) {
				return getWoodmanName(i.GetName());
			}
		}
		switch (getElement(line,column)) {
		
		case 'L':
			//life
			return graphList.get("Life");
		case 'K':
			//trap
			return graphList.get("Trap");
		case '0':
			//free
			return graphList.get("0");
		default:
			break;
			
		}
		StringBuffer element = new StringBuffer();
		//up
		if ((line - 1 >= 0)&&(getElement(line - 1,column) == '1')) {
			element.append("U");
		}
		//left
		if ((column - 1 >= 0)&&(getElement(line,column - 1) == '1')) {
			element.append("L");
		}
		//down
		if ((line + 1 < wood.length)&&(getElement(line + 1,column) == '1')) {
			element.append("D");
		}
		//right
		if ((column + 1 < wood[0].length)&&(getElement(line,column + 1) == '1')) {
			element.append("R");
		}
		if (element.length() > 0) {
			return graphList.get(element.toString());
		}
		else {
			return graphList.get("A");
		}
	}
	@Override
	public Action move(String name, Direction direction) throws Exception {
		Action currentAction;
		currentAction = super.move(name, direction);
		this.printWood(stream);
		if (currentAction == Action.WoodmanNotFound) {
			char tempName = woodmanNames.remove(name);
			for (int i = 0; i < nameList.length; i++) {
				if (nameList[i] == 'N') {
					nameList[i] = tempName;
				}
			}
		}
		return currentAction;
	}
	
	@Override
	public void createWoodman(String name, Point startPoint, Point finishPoint) throws Exception {
		super.createWoodman(name, startPoint, finishPoint);
		printWood(stream);
	}
}
