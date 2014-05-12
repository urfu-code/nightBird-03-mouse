package defpac;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import junit.framework.TestCase;

public class MyWoodLoaderTest extends TestCase {

	public void testLoad() {
		File file = new File("forest.txt");
		try {
			FileInputStream stream = new FileInputStream(file);
			char[][] forest = {{'1','1','1','1'},{'1','0','K','1'},{'1','0','L','1'},{'1','1','1','1'}};
			MyWood wood = new MyWood (forest);
			MyWoodLoader woods = new MyWoodLoader();
			MyWood wood2 = (MyWood)woods.Load(stream);
			assertEquals(wood, wood2);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}