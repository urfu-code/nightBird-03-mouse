package WoodEngine;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;

public class PrintableWood extends Wood {
	
	private final int m_height;
	private final int m_width;
	private final char[][] m_gWoodMap;
	private static final HashMap<Byte, Character> chM = new HashMap<Byte, Character>();
	private OutputStream s;
	private OutputStreamWriter os;
	private HashMap<String, Character> m_wmSym;
    
    public PrintableWood(Character[] wood, int h, int w, OutputStream ostream) throws UnsupportedEncodingException {
    	super(wood, h, w);
		s = ostream;
		os = new OutputStreamWriter(s, System.getProperty("file.encoding"));
		m_wmSym = new HashMap<String, Character>();
		chM.put((byte) 0x00, '╬');
		chM.put((byte) 0x01, '═');
		chM.put((byte) 0x02, '║');
		chM.put((byte) 0x03, '╗');
		chM.put((byte) 0x04, '═');
		chM.put((byte) 0x05, '═');
		chM.put((byte) 0x06, '╔');
		chM.put((byte) 0x07, '╦');
		chM.put((byte) 0x08, '║');
		chM.put((byte) 0x09, '╝');
		chM.put((byte) 0x0A, '║');
		chM.put((byte) 0x0B, '╣');
		chM.put((byte) 0x0C, '╚');
		chM.put((byte) 0x0D, '╩');
		chM.put((byte) 0x0E, '╠');
		chM.put((byte) 0x0F, '╬');
		m_height = h;
		m_width = w;
		m_gWoodMap = new char[m_width][m_height];
		for(int j = 0; j < m_height; j++){
			for (int i = 0; i < m_width; i++) {
				m_gWoodMap[i][j] = toGraphic(i, j);
			}
		}
    }
    
    private char toGraphic(int i, int j) {
    	if(super.m_woodMap[i][j] == '0') return ' ';
    	if(super.m_woodMap[i][j] == '2') return '□';
    	if(super.m_woodMap[i][j] == '3') return '♥';
    	byte mask = 0x0F;
    	
    	if((j-1) >= 0){ //есть ли верх
    		if(super.m_woodMap[i][j-1] != '1') // есть ли стена
    			mask ^= 0x08;
    	}
    	else mask ^= 0x08;
    	
    	if((i+1) < m_width){ //есть ли право
    		if(super.m_woodMap[i+1][j] != '1') // есть ли стена
    			mask ^= 0x04;
    	}
    	else mask ^= 0x04;
    	
    	if((j+1) < m_height){ //есть ли низ
    		if(super.m_woodMap[i][j+1] != '1') // есть ли стена
    			mask ^= 0x02;
    	}
    	else mask ^= 0x02;
    	
    	if((i-1) >= 0){ //есть ли лево
    		if(super.m_woodMap[i-1][j] != '1') // есть ли стена
    			mask ^= 0x01;
    	}
    	else mask ^= 0x01;
		return chM.get(mask);
	}

    @Override
	protected void eraseWoodman(String name){
    	super.eraseWoodman(name);
    	m_wmSym.remove(name);
    }
    
    @Override
    public void createWoodman(String name, Point start, Point finish) throws IOException{
    	pos:for (int j = 0; j < name.length(); j++) {
        	for (Character ch : m_wmSym.values()) {
    			if(name.charAt(j) == ch){
    				continue pos;
    			}
    		}
        	m_wmSym.put(name, name.charAt(j));
        	super.createWoodman(name, start, finish);
        	printWood();
        	return;
		}
    }
	
	private void printWood() throws IOException {
		try{
			for (int j = 0; j < m_height; j++) {
				point: for (int i = 0; i < m_width; i++) {
					for (Woodman wm : super.m_woodmansSet.keySet()) {
						if(wm.GetLocation().equals(new Point(i, j))){
							os.write(m_wmSym.get(wm.GetName()));
							continue point;
						}
					}
					os.write(m_gWoodMap[i][j]);
					continue;
				}
				os.write(System.lineSeparator());
			}
			for (String wmN : m_wmSym.keySet()) {
				os.write(m_wmSym.get(wmN) + " - " + wmN);
				os.write(System.lineSeparator());
			}
			os.write("♥ - life");
			os.write(System.lineSeparator());
			os.write("□ - IT'S A TRAP");
			os.write(System.lineSeparator());
			os.flush();
		}
		catch(IOException e){
			os.close();
			s.close();
			e.printStackTrace();
		}
	}

	@Override
	public Action move(String name, Direction direction) throws IOException {
		Action action = super.move(name, direction);
		printWood();
		return action;
	}

}