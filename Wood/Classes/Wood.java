package Classes;

import java.util.HashMap;
import java.util.Map;

import Enums.Action;
import Enums.Direction;
import Exceptions.*;
import Interfaces.WoodInterface;
import Interfaces.WoodmanInterface;

public class Wood implements WoodInterface {
	private char[][] m_wood;
	private Point m_finish;
	private Map <String, WoodmanInterface> m_woodmans;
	
	public Wood (char [][] wood) {
		m_wood = new char[wood.length][];
		for (int i=0; i<wood.length; i++)
			m_wood[i]=wood[i].clone();
		m_woodmans = new HashMap<>();
	}
	@Override
	public void createWoodman(String name, Point start, Point finish) throws UnexceptableNameException {
		if (m_wood[start.getX()][start.getY()] == '1')
			throw new RuntimeException("Невозможно создать игрока в клетке " + start.toString() + ". в этой клетке стена");
//		Проверка на уникальность имени
		if (m_woodmans.containsKey(name))
			throw new UnexceptableNameException(name);		
		m_woodmans.put(name, new Woodman(start, name));
		m_finish = finish;
	}

	protected Point getLocation(String name) {
		if (!m_woodmans.containsKey(name))
			throw new RuntimeException("Woodman not found");
		return m_woodmans.get(name).GetLocation();
	}
	protected int getLifeCount(String name) {
		if (!m_woodmans.containsKey(name))
			throw new RuntimeException("Нет такого игрока");
		return m_woodmans.get(name).GetLifeCount();
		
	}
	@Override
	public Action move(String name, Direction direction) {
		if (!m_woodmans.containsKey(name))
			return Action.WoodmanNotFound;
		WoodmanInterface carecter = m_woodmans.get(name);
		Point location = carecter.GetLocation();
		switch (direction) {
			case Up:
				location = location.MoveUp();
				break;
			case Down:
				location = location.MoveDown();
				break;
			case Right:
				location = location.MoveRigth();
				break;
			case Left:
				location = location.MoveLeft();
				break;
			default:
				break;
		}
		if (location.equals(m_finish))
			return Action.Finish;
		if (direction.equals(Direction.None)) {
			if (m_wood[location.getX()][location.getY()] == 'K') {
				if (carecter.Kill())
					return Action.Dead;
				else
				{
					m_woodmans.remove(name);
					return Action.WoodmanNotFound;
				}
			}
			if (m_wood[location.getX()][location.getY()] == 'L') {
				carecter.AddLife();				
				return Action.Life;
			}
			if (m_wood[location.getX()][location.getY()] == '0')
				return Action.Ok;
				return null;
			
		}
		if (location.equals(m_finish))
			return Action.Finish;
		int x = location.getX();
		int y = location.getY();
		if ( (x<0) || (x>m_wood.length) )
			throw new RuntimeException("неправильная координата Х=" + x + ". А длина массива по X: " + m_wood.length);
		if ( (y<0) || (y>m_wood[0].length) )
			throw new RuntimeException("неправильная координата Y=" + y + ". А длина массива по Y: " + m_wood[0].length);
		switch (m_wood[x][y]) {
			case '0':
				carecter.SetLocation(location);
				return Action.Ok;
			case '1':
				//на случай если мы стоит на капкане или жизни опять запускаем функцию, что бы она прибавила или уменьшила
				//количество жизней
				location = carecter.GetLocation();
				switch (m_wood[location.getX()][location.getY()]) {
				case 'K':
					if (carecter.Kill())
						return Action.Fail;
					else
					{
//						умер((
						m_woodmans.remove(name);
						return Action.WoodmanNotFound;
					}
				case 'L':
					carecter.AddLife();
				}
				return Action.Fail;
				
				
			case 'K':
				carecter.SetLocation(location);
				if (carecter.Kill())
					return Action.Dead;
				else
				{
//					умер((
					m_woodmans.remove(name);
					return Action.WoodmanNotFound;
				}
			case 'L':
				carecter.SetLocation(location);
				carecter.AddLife();	
				return Action.Life;
		}
		//Никогда до этого момента не должен дойти
		return null;
	}
}
