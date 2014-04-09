import java.util.HashMap;
import java.util.Map;

public class MyMouse implements Mouse {

	private int mouse_life_count;
	private Point mouse_location;
	Map <String,Character> mousesWorld=new HashMap<String,Character>();
	private Direction last_direction;

	int x=0;
	int y=0; 

	public MyMouse() {
		mouse_life_count=3;
		mouse_location = new Point(0,0);
		last_direction = Direction.None;
		x=0;
		y=0;
	}

	public boolean Kill() {
		mouse_life_count = mouse_life_count-1;
		if (mouse_life_count > -1)
			return true;
		else
			return false;
	}

	public void AddMouseLife() {
		mouse_life_count = mouse_life_count+1;
	}

	public Direction searchTheWayInLabirint() {
		Direction direction = Direction.None;
		Point finish = null;
		String upPoint = x + "," + (y-1);
		String downPoint = x + "," + (y+1);
		String leftPoint = (x-1) + "," + y;
		String rightPoint = (x+1) + "," + y;

		//если ничего не известно и мы шли вправо
		if (last_direction == Direction.Right) {
			mouse_location=mouse_location.MoveRigth();
			y++;
			finish = mouse_location;
			last_direction = Direction.Right;
			direction=Direction.Right;
		}

		//если известно право и мы шли не вниз
		if ((mousesWorld.containsKey(rightPoint)) && (last_direction != Direction.Down)) {
			if(mousesWorld.get(rightPoint) == '1') {
				y--;
				finish = mouse_location.MoveLeft();
				last_direction = Direction.Left;
				direction=Direction.Left;
			} else {
				mouse_location=mouse_location.MoveRigth();
				y++;
				finish = mouse_location;
				last_direction = Direction.Right;
				direction=Direction.Right;
			}
		}

		//если известно право и там не стенка
		if ((mousesWorld.containsKey(rightPoint))) {
			if(mousesWorld.get(rightPoint) == '1') {
				mouse_location=mouse_location.MoveDown();
				finish = mouse_location;
				x++;
				last_direction = Direction.Down;
				direction=Direction.Down;
			} else {
				mouse_location=mouse_location.MoveRigth();
				finish = mouse_location;
				y++;
				last_direction = Direction.Right;
				direction=Direction.Right;
			}
		}

		//если известны право и низ
		if (mousesWorld.containsKey(downPoint) && mousesWorld.containsKey(rightPoint)) {
			if((mousesWorld.get(downPoint) == '1') && (mousesWorld.get(rightPoint) == '1')) {
				mouse_location=mouse_location.MoveLeft();
				y--;
				finish = mouse_location;
				last_direction = Direction.Left;
				direction=Direction.Left;
			}
			if((mousesWorld.get(downPoint) == '0') && (mousesWorld.get(rightPoint) == '0')) {
				mouse_location=mouse_location.MoveRigth();
				y++;
				finish = mouse_location;
				last_direction = Direction.Right;
				direction=Direction.Right;
			}
			if((mousesWorld.get(downPoint) == '0') && (mousesWorld.get(rightPoint) == '1')) {
				mouse_location=mouse_location.MoveDown();
				x++;
				finish = mouse_location;
				last_direction = Direction.Down;
				direction=Direction.Down;
			}
			if((mousesWorld.get(downPoint) == '1') && (mousesWorld.get(rightPoint) == '0')) {
				mouse_location=mouse_location.MoveRigth();
				y++;
				finish = mouse_location;
				last_direction = Direction.Right;
				direction=Direction.Right;
			}
		}

		//если известны низ,лево,право
		if (mousesWorld.containsKey(rightPoint) && mousesWorld.containsKey(downPoint) && mousesWorld.containsKey(leftPoint)) {
			if((mousesWorld.get(rightPoint) == '1') && (mousesWorld.get(downPoint) == '1') && (mousesWorld.get(leftPoint) == '1')) {
				mouse_location=mouse_location.MoveUp();
				x--;
				finish = mouse_location;
				last_direction = Direction.Up;
				direction=Direction.Up;
			}
			if((mousesWorld.get(rightPoint) == '1') && (mousesWorld.get(downPoint) == '0') && (mousesWorld.get(leftPoint) == '0')) {
				mouse_location=mouse_location.MoveDown();
				x++;
				finish = mouse_location.MoveDown();
				last_direction = Direction.Down;
				direction=Direction.Down;
			}
			if((mousesWorld.get(rightPoint) == '1') && (mousesWorld.get(downPoint) == '1') && (mousesWorld.get(leftPoint) == '0')) {
				mouse_location=mouse_location.MoveLeft();
				y--;
				finish = mouse_location;
				last_direction = Direction.Left;
				direction=Direction.Left;
			}
			if((mousesWorld.get(rightPoint) == '0') && (mousesWorld.get(downPoint) == '0') && (mousesWorld.get(leftPoint) == '0')) {
				mouse_location=mouse_location.MoveRigth();
				y++;
				finish = mouse_location;
				last_direction = Direction.Right;
				direction=Direction.Right;
			}
		}
		//если известны верх,низ,лево,право
		if (mousesWorld.containsKey(upPoint) && mousesWorld.containsKey(downPoint) && mousesWorld.containsKey(rightPoint) && mousesWorld.containsKey(leftPoint) ) {
			if((mousesWorld.get(upPoint) == '1') && (mousesWorld.get(downPoint) == '1') && (mousesWorld.get(rightPoint) == '1') && (mousesWorld.get(leftPoint) == '1')) {
				finish = mouse_location;
				last_direction = Direction.None;
				direction=Direction.None;
			}
			if((mousesWorld.get(upPoint) == '0') && (mousesWorld.get(downPoint) == '1') && (mousesWorld.get(rightPoint) == '1') && (mousesWorld.get(leftPoint) == '1')) {
				mouse_location=mouse_location.MoveUp();
				x--;
				finish = mouse_location;
				last_direction = Direction.Up;
				direction=Direction.Up;
			}
			if((mousesWorld.get(upPoint) == '0') && (mousesWorld.get(downPoint) == '1') && (mousesWorld.get(rightPoint) == '1') && (mousesWorld.get(leftPoint) == '0')) {
				mouse_location=mouse_location.MoveLeft();
				y--;
				finish = mouse_location;
				last_direction = Direction.Left;
				direction=Direction.Left;
			}
			if((mousesWorld.get(upPoint) == '0') && (mousesWorld.get(downPoint) == '0') && (mousesWorld.get(rightPoint) == '1') && (mousesWorld.get(leftPoint) == '0')) {
				mouse_location=mouse_location.MoveDown();
				x++;
				finish = mouse_location;
				last_direction = Direction.Down;
				direction=Direction.Down;
			}
		}

		return direction;
	}

	@Override
	public Direction NextMove(Action action) {
		String point = x + "," + y;		
		Direction direction = last_direction;

		if (direction == Direction.None) {
			direction = Direction.Right;
			last_direction = direction;
			x++;
			return direction;
		}	

		switch (action) {
		case WoodmanNotFound: direction=Direction.None;
		break;
		case Finish: {
			direction = Direction.None;
			System.out.println("Congratulations! Woodman reached the finish!");
		}			
		break;
		case Dead: {  
			Kill();
			mousesWorld.put(point, 'K');
			direction=searchTheWayInLabirint(); 
		}
		break;
		case Fail: {  
			mousesWorld.put(point, '1');
			switch(last_direction){
			case Up: x--;
			break;
			case Down: x++;
			break;
			case Right: y++;
			break;
			case Left: y--;
			break;
			default:
				break;
			}
			direction=searchTheWayInLabirint(); 
		}
		break;
		case Life: { 
			AddMouseLife();
			mousesWorld.put(point, 'L');
			direction=searchTheWayInLabirint(); 
		}
		break;	
		case Ok:  {  
			mousesWorld.put(point, '0');
			direction=searchTheWayInLabirint(); 
		}	
		}
		return  direction;
	}	

}
