package WoodEngine;
import java.io.IOException;
import java.util.HashMap;

public class Wood implements IWood {
	/**
	 * 0 - floor
	 * 1 - wall
	 * 2 - trap
	 * 3 - life
	 */
	protected final char[][] m_woodMap;
	protected HashMap<Woodman, Point> m_woodmansSet;
	
	public Wood (Character[] objs, int height, int width){
		m_woodmansSet = new HashMap<Woodman, Point>();
		m_woodMap = new char[width][height];
		for (int j = 0; j < height; j++) {
			for (int i = 0; i < width; i++) {
				m_woodMap[i][j] = objs[j*width + i];
			}
		}
	}
	
	protected void eraseWoodman(String name){
		for (Woodman curWM : m_woodmansSet.keySet()) {
			if (curWM.GetName() == name) {
				m_woodmansSet.remove(curWM);
			}
		}
	}

	@Override
	public void createWoodman(String name, Point start, Point finish) throws IOException {
		if(m_woodMap[start.getX()][start.getY()] == '1'){
			throw new IOException("Bad start point");
		}
		m_woodmansSet.put(new Woodman(name, start), finish);
		return;
	}

	@Override
	public Action move(String name, Direction direction) throws IOException {
		for (Woodman curWM : m_woodmansSet.keySet()) {
			if (curWM.GetName() == name) {
				Point curLoc = curWM.GetLocation();
				Point wannabeLoc = curLoc.MoveTo(direction);
				switch(m_woodMap[wannabeLoc.getX()][wannabeLoc.getY()]){
				case '1': {
					if(m_woodMap[curLoc.getX()][curLoc.getY()] == '2'){
						if (curWM.Kill()) {
							eraseWoodman(name);
						}
					}
					if(m_woodMap[curLoc.getX()][curLoc.getY()] == '3'){
						curWM.AddLife();
					}
					return Action.Fail; // wall
				}
				case '0': { // floor
					curWM.SetLocation(wannabeLoc);
					if(curWM.GetLocation().equals(m_woodmansSet.get(curWM)))
						return Action.Finish;
					return Action.Ok;
				}
				case '2': { // trap
					curWM.SetLocation(wannabeLoc);
					if(curWM.GetLocation().equals(m_woodmansSet.get(curWM)))
						return Action.Finish;
					if (curWM.Kill()) {
						eraseWoodman(name);
						return Action.WoodmanNotFound;
					}
					return Action.Dead;
				}
				case '3': { // life
					curWM.SetLocation(wannabeLoc);
					if(curWM.GetLocation().equals(m_woodmansSet.get(curWM)))
						return Action.Finish;
					curWM.AddLife();
					return Action.Life;
				}
				default: return Action.Fail;
				}
			}
		}
		return Action.WoodmanNotFound;
	}

}
