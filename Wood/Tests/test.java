package Tests;

import java.io.IOException;
import java.io.OutputStreamWriter;

public class test {

	public static void main(String[] args) {

		OutputStreamWriter writer = new OutputStreamWriter(System.out);
		try {
			writer.write("Тратата");
			writer.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
