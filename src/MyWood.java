import java.util.Hashtable;
import java.util.Map;


public class MyWood implements Wood {

	private char[][] m_wood;
	protected Map<String, MyWoodman> m_woodmanList = new Hashtable<String, MyWoodman>();
	
	public MyWood(char[][] wood) {
		m_wood = wood;
	}
	
	@Override
	public void createWoodman(String name, Point start, Point finish) throws Exception {
		if (m_wood[start.getX()][start.getY()]==1){
			throw new Exception("WoodmanIsInTheWall");
		} else {
			if (m_woodmanList.containsKey(name)) {
				throw new Exception("NameIsUsed");
			} else {
				m_woodmanList.put(name, new MyWoodman(name, start, finish));
			}
		}
	}

	@Override
	public Action move(String name, Direction direction) throws NullPointerException, Exception {
		Action result = null;
		try {
		Point new_lokation = m_woodmanList.get(name).GetLocation();
		switch (direction) {
		case Up:
			new_lokation = m_woodmanList.get(name).GetLocation().MoveUp();
			break;
			
		case Down:
			new_lokation = m_woodmanList.get(name).GetLocation().MoveDown();
			break;
			
		case Left:
			new_lokation = m_woodmanList.get(name).GetLocation().MoveLeft();
			break;
			
		case Right:
			new_lokation =  m_woodmanList.get(name).GetLocation().MoveRigth();
			break;
			
		case None:
			break;
		}
		if (m_wood[new_lokation.getX()][new_lokation.getY()] == '0') {
			m_woodmanList.get(name).SetLocation(new_lokation);
			result = Action.Ok;
		}
		
		if (m_wood[new_lokation.getX()][new_lokation.getY()] == '1') {
			if (m_wood[m_woodmanList.get(name).GetLocation().getX()][m_woodmanList.get(name).GetLocation().getY()] == 'K') {
				m_woodmanList.get(name).Kill();
			}
			if (m_wood[m_woodmanList.get(name).GetLocation().getX()][m_woodmanList.get(name).GetLocation().getY()] == 'L') {
				m_woodmanList.get(name).AddLife();	
			}
			result = Action.Fail;
		}
		
		if (m_wood[new_lokation.getX()][new_lokation.getY()] == 'L') {
			m_woodmanList.get(name).SetLocation(new_lokation);
			m_woodmanList.get(name).AddLife();
			result = Action.Life;
		}
		
		if (m_wood[new_lokation.getX()][new_lokation.getY()] == 'K') {
			m_woodmanList.get(name).SetLocation(new_lokation);
			m_woodmanList.get(name).Kill();
			if (m_woodmanList.get(name).GetLifeCount() < 0) {
				result = Action.WoodmanNotFound;
			}
			else {
				m_woodmanList.remove(name);
				result = Action.Dead;
			}
		}
		
		if ((new_lokation.getX()== m_woodmanList.get(name).GetFinish().getX()) && (new_lokation.getY() == m_woodmanList.get(name).GetFinish().getY())) {
			m_woodmanList.get(name).SetLocation(new_lokation);
			result = Action.Finish;
		}

		}	catch (Exception e){
			result = Action.WoodmanNotFound;
			}
		return result;
	}
}
