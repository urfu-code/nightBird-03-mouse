import java.io.ByteArrayOutputStream;
import java.io.OutputStream;


public class PrintableWood extends My_Wood {

	private OutputStream out;

	public PrintableWood(char[][] wood, OutputStream out) {
		super(wood);
		this.out = out;
	}
	
	@Override
	public void createWoodman(String name, Point start) {
		super.createWoodman(name, start);
		this.printWood();
	}

	private void printWood() {
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		stream.write(123);
		
		byte[] array = stream.toByteArray();
		
	}

}
