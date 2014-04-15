package mouse;

import java.util.HashMap;
import java.util.Map;

import wood.Action;
import wood.Direction;

public class My_Mouse implements Mouse {
	private Map<String, String> map; //карта в голове мышки
	private int n = 0, m = 0;//наши координаты на карте
	private Direction oldDirection;
	
	public My_Mouse(){
		oldDirection = null;
		map = new HashMap<String,String>();
		n = 0;
		m = 0;
	}
	
	@Override
	public Direction NextMove(Action action) {
		if(oldDirection == null){
			oldDirection = Direction.None;
			return Direction.None;
		}
		writeMap(action);
		if(oldDirection == Direction.None){
			n++;
			oldDirection = Direction.Right;
			return Direction.Right;
		}
		return crawlOnTheRightWall();
	}
	
	private void writeMap(Action action){
		String s = "";
		s=n + "." + m;
		//System.out.println(s);
		switch (action){
		case Ok:	map.put(s, "0");
			break;
		case Fail: {
			map.put(s, "1");
			switch(oldDirection){
			case Up: m++;
				break;
			case Down: m--;
				break;
			case Right: n--;
				break;
			case Left: n++;
				break;
			default:
				break;
			}
		}
			break;
		case Life: map.put(s, "L");
			break;
		case Dead: map.put(s, "K");
			break;
		/*case Finish:
			System.out.println("Мышь дошла до финиша!!!");
			break;
		*/
		default:
			break;
		}
	}
	
	private Direction crawlOnTheRightWall (){
		String s1 = n + "." + (m-1); //смотрим на вверх
		String s2 = n + "." + (m+1);//смотрим вниз
		String s3 = (n-1) + "." + m;//смотрим влево
		String s4 = (n+1) + "." + m;//смотрим вправо
		if(map.containsKey(s1) && map.containsKey(s2) && map.containsKey(s4)){
			if((map.get(s1) == "1") && (map.get(s2) == "1") && (map.get(s4) == "1")){//ВНП
				n--;
				oldDirection = Direction.Left;
				return Direction.Left;
			}
		}
		if(map.containsKey(s1) && map.containsKey(s3) && map.containsKey(s4)){
			if((map.get(s1) == "1") && (map.get(s3) == "1") && (map.get(s4) == "1")){//ВПЛ
				m++;
				oldDirection = Direction.Down;
				return Direction.Down;
			}
		}
		if(map.containsKey(s1) && map.containsKey(s2) && map.containsKey(s3)){
			if((map.get(s1) == "1") && (map.get(s2) == "1") && (map.get(s3) == "1")){//ВНЛ
				n++;
				oldDirection = Direction.Right;
				return Direction.Right;
			}
		}
		if(map.containsKey(s4) && map.containsKey(s2) && map.containsKey(s3)){
			if((map.get(s4) == "1") && (map.get(s2) == "1") && (map.get(s3) == "1")){//ЛНП
				m--;
				oldDirection = Direction.Up;
				return Direction.Up;
			}
		}
		if(map.containsKey(s1) && map.containsKey(s3)){
			if((map.get(s1) == "1") && (map.get(s3) == "1")){//ВЛ
				m++;
				oldDirection = Direction.Down;
				return Direction.Down;
			}
		}
		if(map.containsKey(s2) && map.containsKey(s3)){
			if((map.get(s2) == "1") && (map.get(s3) == "1")){//НЛ
				n++;
				oldDirection = Direction.Right;
				return Direction.Right;
			}
		}
		if(map.containsKey(s2) && map.containsKey(s4)){
			if((map.get(s2) == "1") && (map.get(s4) == "1")){//НП
				m--;
				oldDirection = Direction.Up;
				return Direction.Up;
			}
		}
		if(map.containsKey(s1) && map.containsKey(s4)){
			if((map.get(s1) == "1") && (map.get(s4) == "1")){//ВП
				n--;
				oldDirection = Direction.Left;
				return Direction.Left;
			}
		}
		if((map.containsKey(s1)) && (oldDirection != Direction.Right)){
			if(map.get(s1) == "1"){
				n--;
				oldDirection = Direction.Left;
				return Direction.Left;
			}
		}
		if((map.containsKey(s2))&& (oldDirection != Direction.Left)){
			if(map.get(s2) == "1"){
				n++;
				oldDirection = Direction.Right;
				return Direction.Right;
			}
		}
		if((map.containsKey(s3)) && (oldDirection != Direction.Up)){
			if(map.get(s3) == "1"){
				m++;
				oldDirection = Direction.Down;
				return Direction.Down;
			}
		}
		if((map.containsKey(s4)) && (oldDirection != Direction.Down)){
			if(map.get(s4) == "1"){
				m--;
				oldDirection = Direction.Up;
				return Direction.Up;
			}
		}
		
		/* Случай 
		 *  0						101
		 * 0М0   -> тк без циклов 	0М0
		 *  0						101
		 */
		if(map.containsKey(s1) & map.containsKey(s2) & map.containsKey(s3) & map.containsKey(s4)){
			if((map.get(s1) == "0") & (map.get(s2) == "0") & (map.get(s3) == "0") & (map.get(s4) == "0")){
				String st1 = (n-1) + "." + (m-1);
				String st2 = (n+1) + "." + (m-1);
				String st3 = (n-1) + "." + (m+1);
				String st4 = (n+1) + "." + (m+1);
				map.put(st1, "1");
				map.put(st2, "1");
				map.put(st3, "1");
				map.put(st4, "1");
			}
		}
		if (oldDirection == Direction.Left){
			m--;
			oldDirection = Direction.Up;
			return Direction.Up;
		}
		if (oldDirection == Direction.Right){
			m++;
			oldDirection = Direction.Down;
			return Direction.Down;
		}
		if (oldDirection == Direction.Down){
			n--;
			oldDirection = Direction.Left;
			return Direction.Left;
		}
		if (oldDirection == Direction.Up){
			n++;
			oldDirection = Direction.Right;
			return Direction.Right;
		}
		return null;
		
	}

}
