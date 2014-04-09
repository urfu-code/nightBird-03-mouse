package defpac;

public class Deadmau5 implements Mau5 {
	int X;
	int Y;
	private char[][] map = new char[Y][X];
	private Point mau5location;
	private Direction lastDirection;
	private Action lastAction;
	private boolean openedLife;
	private boolean needLifes;
	private int lifeCounter;
	private int lifesNeeded;
	
	public Deadmau5() {
		needLifes = false;
		openedLife = false;
		X = Y = 3;
		for (int Idx = 0; Idx < X; Idx++)
			for (int Jdx = 0; Jdx < Y; Jdx++)
				map[Idx][Jdx] = '0';
		mau5location = new Point(1,1);
		map[1][1] = '2';		
		lastDirection = Direction.Left;
		lastAction = Action.Fail;
		lifeCounter = 3;
		lifesNeeded = 5;
	}
	
	public boolean hurt() {
		if (lifesNeeded > lifeCounter)
			needLifes = true;
		return needLifes;
	}
	
	public void changeMap () {
		
		if (lastDirection == Direction.Down) {
			if (lastAction == Action.Fail) {
				map[mau5location.getX()+1][mau5location.getY()] = '1';
			}
			if (lastAction == Action.Ok || lastAction == Action.Dead || lastAction == Action.Life) {
				char symbol = '2';
				if (openedLife) symbol = '3';
				if (lastAction == Action.Dead) symbol = 'K';
				if (lastAction == Action.Life) symbol = 'L';
					if (mau5location.getX() == Y-1) { 
					Y++;
					for (int Idx = 0; Idx < X; Idx++)
						map[Idx][Y-1] = '0';
				}
				map[mau5location.getX()][mau5location.getY()] = symbol;
				if (symbol == 'K') lifesNeeded++;
			}			
		}
		
		if (lastDirection == Direction.Up) {
			if (lastAction == Action.Fail) {
				map[mau5location.getX()-1][mau5location.getY()] = '1';
			}
			if (lastAction == Action.Ok || lastAction == Action.Dead || lastAction == Action.Life) {
				char symbol = '2';
				if (openedLife) symbol = '3';
				if (lastAction == Action.Dead) symbol = 'K';
				if (lastAction == Action.Life) symbol = 'L';
					if (mau5location.getX() == 0) { 
					Y++;
					for (int Idx = Y-2; Idx >= 0; Idx--)
						for (int Jdx = 0; Jdx < X; Jdx++)
							map[Jdx][Idx] = map[Jdx][Idx+1];
					for (int Jdx = 0; Jdx < X; Jdx++)
						map[Jdx][0] = '0';
				}
				mau5location = new Point(mau5location.getX()+1,mau5location.getY());
				map[mau5location.getX()][mau5location.getY()] = symbol;
				if (symbol == 'K') lifesNeeded++;
			}
		}
		
		if (lastDirection == Direction.Left) {			
			if (lastAction == Action.Fail) {
				map[mau5location.getX()][mau5location.getY()-1] = '1';
			}
			if (lastAction == Action.Ok || lastAction == Action.Dead || lastAction == Action.Life) {
				char symbol = '2';
				if (openedLife) symbol = '3';
				if (lastAction == Action.Dead) symbol = 'K';
				if (lastAction == Action.Life) symbol = 'L';
					if (mau5location.getY() == 0) { 
					X++;
					for (int Idx = X-2; Idx >= 0; Idx--)
						for (int Jdx = 0; Jdx < Y; Jdx++)
							map[Jdx][Idx] = map[Jdx+1][Idx];
					for (int Jdx = 0; Jdx < Y; Jdx++)
						map[0][Jdx] = '0';
				}
				mau5location = new Point(mau5location.getX(),mau5location.getY()+1);
				map[mau5location.getX()][mau5location.getY()] = symbol;
				if (symbol == 'K') lifesNeeded++;
			}
		}
		
		if (lastDirection == Direction.Right) {
			if (lastAction == Action.Fail) {
				map[mau5location.getX()][mau5location.getY()+1] = '1';
			}
			if (lastAction == Action.Ok || lastAction == Action.Dead || lastAction == Action.Life) {
				char symbol = '2';
				if (openedLife) symbol = '3';
				if (lastAction == Action.Dead) symbol = 'K';
				if (lastAction == Action.Life) symbol = 'L';
					if (mau5location.getY() == X-1) { 
					X++;
					for (int Idx = 0; Idx < Y; Idx++)
						map[X-1][Idx] = '0';
				}
				map[mau5location.getX()][mau5location.getY()] = symbol;
				if (symbol == 'K') lifesNeeded++;
			}
		}	
	}

	public Direction NextMove(Action action) {
		Direction direction = Direction.None;
		lastAction = action;
		switch (action) {
			case WoodmanNotFound:
				break;
			case Finish:
				break;
			case Life:
				changeMap();
				openedLife = true;
				int mau5X = mau5location.getX();
				int mau5Y = mau5location.getY();
				if (!hurt()) {
					if (map[mau5X][mau5Y-1] == '3') direction = Direction.Left;
					if (map[mau5X][mau5Y+1] == '3') direction = Direction.Right;
					if (map[mau5X-1][mau5Y] == '3') direction = Direction.Up;
					if (map[mau5X+1][mau5Y] == '3') direction = Direction.Down;
					if (direction == Direction.None) {
						if (map[mau5X][mau5Y-1] == '0') direction = Direction.Left;
						if (map[mau5X][mau5Y+1] == '0') direction = Direction.Right;
						if (map[mau5X-1][mau5Y] == '0') direction = Direction.Up;
						if (map[mau5X+1][mau5Y] == '0') direction = Direction.Down;
					}
					if (direction == Direction.None) {
						if (map[mau5X][mau5Y-1] == 'K') direction = Direction.Left;
						if (map[mau5X][mau5Y+1] == 'K') direction = Direction.Right;
						if (map[mau5X-1][mau5Y] == 'K') direction = Direction.Up;
						if (map[mau5X+1][mau5Y] == 'K') direction = Direction.Down;
					}
					if (direction == Direction.None) {
						if (map[mau5X][mau5Y-1] == '2') direction = Direction.Left;
						if (map[mau5X][mau5Y+1] == '2') direction = Direction.Right;
						if (map[mau5X-1][mau5Y] == '2') direction = Direction.Up;
						if (map[mau5X+1][mau5Y] == '2') direction = Direction.Down;
					}
				}
				if (hurt()) {
					if (map[mau5X][mau5Y-1] == '0') direction = Direction.Left;
					if (map[mau5X][mau5Y+1] == '0') direction = Direction.Right;
					if (map[mau5X-1][mau5Y] == '0') direction = Direction.Up;
					if (map[mau5X+1][mau5Y] == '0') direction = Direction.Down;
				}				
				break;
			case Ok:
				changeMap();
				if (!hurt()||(hurt() && !openedLife)) {
					direction = lastDirection;
					if (direction == Direction.Left && map[mau5location.getX()][mau5location.getY()-1] != '0') {
						if (map[mau5location.getX()-1][mau5location.getY()] == '0' || map[mau5location.getX()-1][mau5location.getY()] == 'L') direction = Direction.Up;
						if (map[mau5location.getX()+1][mau5location.getY()] == '0' || map[mau5location.getX()+1][mau5location.getY()] == 'L') direction = Direction.Down;
						if (map[mau5location.getX()-1][mau5location.getY()] == 'K') direction = Direction.Up;
						if (map[mau5location.getX()+1][mau5location.getY()] == 'K') direction = Direction.Down;
						if (map[mau5location.getX()][mau5location.getY()+1] == '2' || map[mau5location.getX()][mau5location.getY()+1] == 'L') direction = Direction.Right;
						if (map[mau5location.getX()][mau5location.getY()+1] == 'K') direction = Direction.Right;
					}
					if (direction == Direction.Right && map[mau5location.getX()][mau5location.getY()+1] != '0') {
						if (map[mau5location.getX()-1][mau5location.getY()] == '0' || map[mau5location.getX()-1][mau5location.getY()] == 'L') direction = Direction.Up;
						if (map[mau5location.getX()+1][mau5location.getY()] == '0' || map[mau5location.getX()+1][mau5location.getY()] == 'L') direction = Direction.Down;
						if (map[mau5location.getX()-1][mau5location.getY()] == 'K') direction = Direction.Up;
						if (map[mau5location.getX()+1][mau5location.getY()] == 'K') direction = Direction.Down;
						if (map[mau5location.getX()][mau5location.getY()-1] == '2' || map[mau5location.getX()][mau5location.getY()-1] == 'L') direction = Direction.Left;
						if (map[mau5location.getX()][mau5location.getY()-1] == 'K') direction = Direction.Left;
					}
					if (direction == Direction.Up && map[mau5location.getX()-1][mau5location.getY()] != '0') {
						if (map[mau5location.getX()][mau5location.getY()-1] == '0' || map[mau5location.getX()][mau5location.getY()-1] == 'L') direction = Direction.Left;
						if (map[mau5location.getX()][mau5location.getY()+1] == '0' || map[mau5location.getX()][mau5location.getY()+1] == 'L') direction = Direction.Right;
						if (map[mau5location.getX()][mau5location.getY()-1] == 'K') direction = Direction.Left;
						if (map[mau5location.getX()][mau5location.getY()+1] == 'K') direction = Direction.Right;
						if (map[mau5location.getX()+1][mau5location.getY()] == '2' || map[mau5location.getX()+1][mau5location.getY()] == 'L') direction = Direction.Down;
						if (map[mau5location.getX()+1][mau5location.getY()] == 'K') direction = Direction.Down;
					}
					if (direction == Direction.Down && map[mau5location.getX()-1][mau5location.getY()] != '0') {
						if (map[mau5location.getX()][mau5location.getY()-1] == '0' || map[mau5location.getX()][mau5location.getY()-1] == 'L') direction = Direction.Left;
						if (map[mau5location.getX()][mau5location.getY()+1] == '0' || map[mau5location.getX()][mau5location.getY()+1] == 'L') direction = Direction.Right;
						if (map[mau5location.getX()][mau5location.getY()-1] == 'K') direction = Direction.Left;
						if (map[mau5location.getX()][mau5location.getY()+1] == 'K') direction = Direction.Right;
						if (map[mau5location.getX()-1][mau5location.getY()] == '2' || map[mau5location.getX()-1][mau5location.getY()] == 'L') direction = Direction.Up;
						if (map[mau5location.getX()-1][mau5location.getY()] == 'K') direction = Direction.Up;
					}
				}
				if ((hurt() && openedLife)) {
					direction = lastDirection;
					if (direction == Direction.Left && map[mau5location.getX()][mau5location.getY()-1] != '3') {
						if (map[mau5location.getX()][mau5location.getY()+1] == '3' || map[mau5location.getX()][mau5location.getY()+1] == 'L') direction = Direction.Right;
						if (map[mau5location.getX()-1][mau5location.getY()] == '3' || map[mau5location.getX()-1][mau5location.getY()] == 'L') direction = Direction.Up;
						if (map[mau5location.getX()+1][mau5location.getY()] == '3' || map[mau5location.getX()+1][mau5location.getY()] == 'L') direction = Direction.Down;
						if (map[mau5location.getX()][mau5location.getY()+1] == 'K') direction = Direction.Right;
						if (map[mau5location.getX()-1][mau5location.getY()] == 'K') direction = Direction.Up;
						if (map[mau5location.getX()+1][mau5location.getY()] == 'K') direction = Direction.Down;
					}
					if (direction == Direction.Right && map[mau5location.getX()][mau5location.getY()+1] != '3') {
						if (map[mau5location.getX()][mau5location.getY()-1] == '3' || map[mau5location.getX()][mau5location.getY()-1] == 'L') direction = Direction.Left;
						if (map[mau5location.getX()-1][mau5location.getY()] == '3' || map[mau5location.getX()-1][mau5location.getY()] == 'L') direction = Direction.Up;
						if (map[mau5location.getX()+1][mau5location.getY()] == '3' || map[mau5location.getX()+1][mau5location.getY()] == 'L') direction = Direction.Down;
						if (map[mau5location.getX()][mau5location.getY()-1] == 'K') direction = Direction.Left;
						if (map[mau5location.getX()-1][mau5location.getY()] == 'K') direction = Direction.Up;
						if (map[mau5location.getX()+1][mau5location.getY()] == 'K') direction = Direction.Down;
					}
					if (direction == Direction.Up && map[mau5location.getX()-1][mau5location.getY()] != '3') {
						if (map[mau5location.getX()][mau5location.getY()-1] == '3' || map[mau5location.getX()][mau5location.getY()-1] == 'L') direction = Direction.Left;
						if (map[mau5location.getX()][mau5location.getY()+1] == '3' || map[mau5location.getX()][mau5location.getY()+1] == 'L') direction = Direction.Right;
						if (map[mau5location.getX()+1][mau5location.getY()] == '3' || map[mau5location.getX()+1][mau5location.getY()] == 'L') direction = Direction.Down;
						if (map[mau5location.getX()][mau5location.getY()-1] == 'K') direction = Direction.Left;
						if (map[mau5location.getX()][mau5location.getY()+1] == 'K') direction = Direction.Right;
						if (map[mau5location.getX()+1][mau5location.getY()] == 'K') direction = Direction.Down;
					}
					if (direction == Direction.Down && map[mau5location.getX()-1][mau5location.getY()] != '3') {
						if (map[mau5location.getX()][mau5location.getY()-1] == '3' || map[mau5location.getX()][mau5location.getY()-1] == 'L') direction = Direction.Left;
						if (map[mau5location.getX()][mau5location.getY()+1] == '3' || map[mau5location.getX()][mau5location.getY()+1] == 'L') direction = Direction.Right;
						if (map[mau5location.getX()-1][mau5location.getY()] == '3' || map[mau5location.getX()-1][mau5location.getY()] == 'L') direction = Direction.Up;
						if (map[mau5location.getX()][mau5location.getY()-1] == 'K') direction = Direction.Left;
						if (map[mau5location.getX()][mau5location.getY()+1] == 'K') direction = Direction.Right;
						if (map[mau5location.getX()-1][mau5location.getY()] == 'K') direction = Direction.Up;
					}
				}			
				break;
			case Fail:
				changeMap();
				if (map[mau5location.getX()][mau5location.getY()-1] == '0') direction = Direction.Left;
				if (map[mau5location.getX()][mau5location.getY()+1] == '0') direction = Direction.Right;
				if (map[mau5location.getX()-1][mau5location.getY()] == '0') direction = Direction.Up;
				if (map[mau5location.getX()+1][mau5location.getY()] == '0') direction = Direction.Down;
				if (direction == Direction.None) {
					if (map[mau5location.getX()][mau5location.getY()-1] != '1') direction = Direction.Left;
					if (map[mau5location.getX()][mau5location.getY()+1] != '1') direction = Direction.Right;
					if (map[mau5location.getX()-1][mau5location.getY()] != '1') direction = Direction.Up;
					if (map[mau5location.getX()+1][mau5location.getY()] != '1') direction = Direction.Down;
				}
				break;
			case Dead:
				changeMap();
				if (map[mau5location.getX()][mau5location.getY()-1] == '0') direction = Direction.Left;
				if (map[mau5location.getX()][mau5location.getY()+1] == '0') direction = Direction.Right;
				if (map[mau5location.getX()-1][mau5location.getY()] == '0') direction = Direction.Up;
				if (map[mau5location.getX()+1][mau5location.getY()] == '0') direction = Direction.Down;
				if (direction == Direction.None) {
					if (map[mau5location.getX()][mau5location.getY()-1] != '1') direction = Direction.Left;
					if (map[mau5location.getX()][mau5location.getY()+1] != '1') direction = Direction.Right;
					if (map[mau5location.getX()-1][mau5location.getY()] != '1') direction = Direction.Up;
					if (map[mau5location.getX()+1][mau5location.getY()] != '1') direction = Direction.Down;
				}
				break;	
			}
			lastDirection = direction;
			return direction;
		}
	}