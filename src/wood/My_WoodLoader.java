package wood;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;


public class My_WoodLoader implements WoodLoader {

	@Override
	public My_Wood Load(InputStream inStream) {
		char[][] wood = read(inStream);
		return new My_Wood(wood);
	}

	public PrintWood Load(InputStream inStream, OutputStream outStream) {
		char[][] wood = read(inStream);
		return new PrintWood(wood, outStream);
	}
	
	public char[][] read(InputStream stream) {
		int n = 0, m = 0;
		Scanner in = new Scanner(stream);
		String s = "";
		while(in.hasNextLine()){
			String test = in.nextLine();
			s += test;
			m = test.length();
			n++;		
		}
		//stream.close();
		char [][] wood = new char [n][m];
		int a=0, b=m, k=0;
		while (k < n){
			String work = s.substring(a, b);
			for(int i = 0; i < work.length(); i++){
				wood[k][i] = work.charAt(i);
			}
			k++;
			a=b;
			b += m;
		}
		return wood;
	}

}
