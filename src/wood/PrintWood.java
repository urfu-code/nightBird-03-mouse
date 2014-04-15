package wood;
import java.io.IOException;
import java.io.OutputStream;

public class PrintWood extends My_Wood {
	
	OutputStream stream;
	public PrintWood(char[][] my_wood, OutputStream my_stream){
		super(my_wood);
		stream = my_stream;
	}
	private void print() throws IOException{
		char[] line = new char[m_wood[0].length];
		char[][] wood = stenci(m_wood);
		for (int i = 0; i < super.m_wood.length; i++) {
			for (int j = 0; j < super.m_wood[0].length; j++) {
				line[j] = wood[i][j];
		  		Point currentPoint = new Point(j,i);
		  		for (My_Woodman k:(super.m_woodmanlist).values()) {
		  			if (k.GetLocation().equals(currentPoint)) {
		 				line[j] = k.GetName().charAt(0);
		  			}
		  		}
		}	
			String str = new String(line);
			stream.write((str + "\n").getBytes());
	}
		stream.write("\n".getBytes());
		stream.write(("♥" + " - жизнь\n").getBytes());
		stream.write(("#" + " - капкан\n").getBytes());
		for (My_Woodman i:(super.m_woodmanlist).values()) {
			stream.write((i.GetName().charAt(0) + 
					" - " + i.GetName() + 
					"  (" + i.GetLifeCount() + 
					" - жизни)\n").getBytes());
  		}
	}
	public char[][] stenci(char[][] wood){
		char[][] my = new char[wood.length+2][wood[0].length+2];
		for(int i=0; i<wood.length+2; i++){
			for(int j=0; j<wood[0].length+2; j++){
				my[i][j] = '0';
			}
		}
		for(int i=0; i<wood.length; i++){
			for(int j=0; j<wood[0].length; j++){
				my[i+1][j+1] = wood[i][j];
			}
		}
		char[][] my2 = new char[wood.length+2][wood[0].length+2];
		for(int i=1; i<wood.length+2; i++){
			for(int j=1; j<wood[0].length+2; j++){
				switch(my[i][j]){
					case '1':{
						int down = 0, up = 0, left = 0, rigth = 0;
						if (my[i+1][j] == '1') down=1;
						if (my[i-1][j] == '1') up=1;
						if (my[i][j-1] == '1') left=1;
						if (my[i][j+1] == '1') rigth=1;
						my2[i][j] = test(down, up, left, rigth);
						if(my2[i][j] == ' ') my2[i][j] = '─';
						break;
					}
					case '0':{
						my2[i][j] = (' ');
						break;
					}
					case 'L':{
						my2[i][j] = ('♥');
						break;
					}
					case 'K':{
						my2[i][j] = ('#');
						break;
					}
				}
			}
		}
		char[][] result = new char[wood.length][wood[0].length];
		for(int i=0; i<wood.length; i++){
			for(int j=0; j<wood[0].length; j++){
				result[i][j] = my2[i+1][j+1];
			}
		}
		return result;	
	}
	
	private char test(int down, int up, int left, int rigth) {
		if(down == 1){
			if((up == 1) && (left == 1) && (rigth == 1)) return('┼');
			if((up == 1) && (left == 1)) return('┤');
			if((up == 1) &&(rigth == 1)) return('├');
			if((left == 1) && (rigth == 1)) return('┬');
			if (left == 1) return('┐');
			if (rigth == 1) return('┌');
			return('│');
		}else{
			if(up == 1){
				if((left == 1) && (rigth == 1)) return('┴');
				if(left == 1) return('┘');
				if(rigth == 1) return('└');
				return('│');
			}
			else{
				if (left == 1) return('─');
				if (rigth == 1)return('─');
			}
		}
		return ' ';
	}
	
	@Override
	public void createWoodman(String name, Point start, Point finish) {
		super.createWoodman(name, start, finish);
		try{
		print();
		} catch (IOException e){
			e.printStackTrace();
		}
	}

	@Override
	public Action move(String name, Direction direction) {
		Action currentAction;
		currentAction = super.move(name, direction);
		try{
		print();
		} catch (IOException e){
			e.printStackTrace();
		}
		return currentAction;
	}
	
}
