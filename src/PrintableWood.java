import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class PrintableWood extends MyWood {

	private char[][] m_wood;
	private LinkedList<StringBuilder> printList;
	private Map<String,Character>woodmanNames;
	private Map<Character, Boolean> freeImage;
	private OutputStream out_stream;
	private OutputStreamWriter writer;
	private char[] imageList = {'✢', '✤', '✦', '✧', '✪', '✫', '✰', '✱', '✲', '✹', '✺', '✻', '✾', '✿', '❀', '❁', '❂', '❃', '❄', '❅', '❆', '❇', '❈', '❉'};
	
	public PrintableWood(char[][] wood, OutputStream stream) {
		super(wood);
		m_wood = wood;
		printList = new LinkedList<StringBuilder>();
		woodmanNames = new HashMap<String, Character>();
		out_stream = stream;
		
		freeImage = new HashMap<Character, Boolean>();
		for (int i = 0; i < m_wood.length; i++) {
			freeImage.put(imageList[i], false);
		}		
		writer = new OutputStreamWriter(out_stream);
	}
	
	
	private char getImage(String name) throws Exception {
		if (woodmanNames.containsKey(name)) {
			return woodmanNames.get(name);
		}
		for (int i = 0; i < imageList.length; i++) {
			if (freeImage.get(imageList[i]) == false) {
				woodmanNames.put(name, imageList[i]);
				freeImage.put(imageList[i], true);
				return woodmanNames.get(name);
			}
		}
		throw new Exception("NoFreeImages");
	}
	
	
	@Override
	public void createWoodman(String name, Point start, Point finish) throws Exception {
		super.createWoodman(name, start, finish);
		printWood();
	}

	@Override
	public Action move(String name, Direction direction) throws Exception {
		Action action = super.move(name, direction);
		printWood();
		if (action == Action.WoodmanNotFound) {
					freeImage.put(woodmanNames.remove(name), false);
			}
		return action;
	}

	public void printWood() throws Exception {
		
		
		
		for (int i = 0; i < m_wood[0].length; i++) {
			StringBuilder str_b = new StringBuilder();
			for (int k = 0; k < m_wood.length; k++) {
				switch (m_wood[k][i]) {
				case '1':
					str_b.append('▓');
					break;
				case '0':
					str_b.append('░');
					break;
				case 'L':
					str_b.append('♥');
					break;
				case 'K':
					str_b.append('✡');
					break;
				}
			}
			printList.add(str_b);
		}
		
		printList.add(new StringBuilder());
		printList.add(new StringBuilder("♥ - дополнительная жизнь"));
		printList.add(new StringBuilder("✡ - капкан"));
		
		for (MyWoodman newWoodman : m_woodmanList.values()) {         
				printList.get(newWoodman.GetLocation().getY()).setCharAt(newWoodman.GetLocation().getX(), getImage(newWoodman.GetName()));
				printList.add(new StringBuilder(getImage(newWoodman.GetName()) + " - " + newWoodman.GetName() + " (Количестро жизней:" + newWoodman.GetLifeCount() + ")"));
		}		
		
		
		StringBuilder print = new StringBuilder(); 
			for(StringBuilder str : printList) {             
				print.append(str);                      
				print.append(System.getProperty("line.separator"));   
			}	
		writer.write(print.toString());
		writer.flush();
		printList.clear();	
}
	
}