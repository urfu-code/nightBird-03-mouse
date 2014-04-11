import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public  class MyWood implements Wood {
	private static final Map<String, MyWoodman> m_woodmanlist = null;
	Map <String,MyWoodman> m_woodmanList = new HashMap<String,MyWoodman>();
	char [][] m_wood;
	Map <String,MyMouse> mousesList = new HashMap<String,MyMouse>();

	public MyWood(char[][] wood) {
		m_wood=wood;
	}

	public void createWoodman(String name, Point start, Point finish) throws CodeException, IOException {
		MyWoodman m_woodman=new MyWoodman(name,start,finish);
		if (m_wood[start.getX()][start.getY()]=='1')
			throw new CodeException("Error!You can't stand on the wall!");
		if (m_wood[finish.getX()][finish.getY()]=='1')
			throw new CodeException("Error!You can't stand on the wall!");
		if (!m_woodmanList.containsKey(name)) 
			m_woodmanList.put(name,m_woodman);
		else throw new CodeException("Such woodman exsists yet");
	}

	protected Point getLocation(String name) throws CodeException {
		if (!m_woodmanList.containsKey(name)) {
			//Action result=Action.WoodmanNotFound;
			throw new CodeException("Woodman not found");
		}
		return m_woodmanList.get(name).GetLocation();
	}

	public Action move(String name, Direction direction) throws IOException, CodeException {
		Action result=null;
		if (!m_woodmanList.containsKey(name)) {
			result=Action.WoodmanNotFound;
		} else {
			Point n=m_woodmanList.get(name).GetLocation();
			Point newPoint = m_woodmanList.get(name).GetLocation();

			switch (direction) {
			case Up: newPoint=n.MoveUp();
			break;
			case Down: newPoint=n.MoveDown();
			break;
			case Left: newPoint=n.MoveLeft();
			break;
			case Right: newPoint=n.MoveRigth();
			break;	
			case None:  newPoint=n;
			}

			Point p=m_woodmanList.get(name).GetFinish();

			if(newPoint.getX()==p.getX() && newPoint.getY()==p.getY()) {
				return Action.Finish;
			}

			switch (m_wood[newPoint.getX()][newPoint.getY()]) {
			case '1': {

				if(m_wood[n.getX()][n.getY()]=='K') {
					m_woodmanList.get(name).Kill();
					if(m_woodmanList.get(name).GetLifeCount()<=-1) {
						m_woodmanList.remove(name);
					} 
				}
				if(m_wood[n.getX()][n.getY()]=='L') {
					m_woodmanList.get(name).AddLife();
				}
				result=Action.Fail;
			}
			break;
			case '0': {
				result= Action.Ok;
				m_woodmanList.get(name).SetLocation(newPoint);
			}
			break;
			case 'L': {
				m_woodmanList.get(name).AddLife();
				result=Action.Life; 
				m_woodmanList.get(name).SetLocation(newPoint); 
			}
			break;
			case 'K': {
				m_woodmanList.get(name).Kill();
				result=Action.Dead;
				m_woodmanList.get(name).SetLocation(newPoint); 
				if(m_woodmanList.get(name).GetLifeCount()<=-1) {
					m_woodmanList.remove(name);
					result=Action.WoodmanNotFound;
				} 

				break;
			}

			}
		}
		return result;
	}

}
