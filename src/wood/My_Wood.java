package wood;
import java.util.HashMap;
import java.util.Map;


public class My_Wood implements Wood {
	protected char[][] m_wood;
	protected Map<String, My_Woodman> m_woodmanlist;
	
	public My_Wood(char[][] wood){
		m_woodmanlist = new HashMap<String, My_Woodman>();
		m_wood = wood;
	}

	@Override
	public void createWoodman(String name, Point start, Point finish) {
		try{
			if(m_woodmanlist.containsKey(name)) 
			throw new Exception("Персонаж с таким именем уже существует!");
			m_woodmanlist.put(name, new My_Woodman(name, start, finish));
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public Action move(String name, Direction direction) {
		if(!m_woodmanlist.containsKey(name)){
			return Action.WoodmanNotFound;
		}
	Point position= (m_woodmanlist.get(name)).GetLocation();
	switch (direction){
		case Up: position = position.MoveUp();
			break;
		case Down: position = position.MoveDown();
			break;
		case Left: position = position.MoveLeft();
			break;
		case Right: position = position.MoveRigth();
			break;
		case None:
			break;
		default:
			break;
	}
	if(position.equals((m_woodmanlist.get(name)).GetFinish())){
		//(m_woodmanlist.get(name)).SetLocation(position);
		m_woodmanlist.remove(name);
		return Action.Finish;
	}
	char look = m_wood[position.getY()][position.getX()];
	switch(look){
		case '1': { Point my_point = (m_woodmanlist.get(name)).GetLocation();
			if ('K' == m_wood[my_point.getY()][my_point.getX()]){
				if(!(m_woodmanlist.get(name)).Kill()){
					m_woodmanlist.remove(name);
					return Action.WoodmanNotFound;
				}
			}
			if ('L' == m_wood[my_point.getY()][my_point.getX()]){
				(m_woodmanlist.get(name)).AddLife();
			}
		}
		return Action.Fail;
		case 'K': {
			if(!(m_woodmanlist.get(name)).Kill()){
				m_woodmanlist.remove(name);
				return Action.WoodmanNotFound;
			} else {
				(m_woodmanlist.get(name)).SetLocation(position);
				return Action.Dead;
			}
		}
		case 'L':{
			(m_woodmanlist.get(name)).AddLife();
			(m_woodmanlist.get(name)).SetLocation(position);
			return Action.Life;
		}
		case '0':{
			(m_woodmanlist.get(name)).SetLocation(position);
			return Action.Ok;
		}
		default:
			break;
		}
		return null;
	}

}
