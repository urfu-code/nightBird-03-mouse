package defpac;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Deadmau5 implements Mau5 {
	int X;
	int Y;
	private char[][] map = new char[Y][X];
	private char[][] LKmap = new char[Y][X];
	private Point mau5loc;
	private Direction lastDir;
	private Direction lastlastDir;
	private Action lastAct;
	private boolean openLife;
	private int lifeCounter;
	private int lost;
	private int lifes;
	private boolean changeMap;
	private boolean safeMode;
	private boolean stackCreated;
	private static Stack<Point>wayToLife;
	private static Stack<Point>wayFromLife;

	public Deadmau5() {
		openLife = false;
		X = Y = 3;
		map = new char [Y][X];
		LKmap = new char [Y][X];
		for (int Idx = 0; Idx < X; Idx++) {
			for (int Jdx = 0; Jdx < Y; Jdx++) {
				LKmap[Jdx][Idx] = '*';
				map[Jdx][Idx] = '0';
			}
		}
		mau5loc = new Point(1,1);
		LKmap[1][1] = '0'; 
		map[1][1] = '2';		
		lastDir = Direction.Down;
		lastlastDir = Direction.None;
		lastAct = Action.Ok;
		lifeCounter = 3;
		lost = 0;
		lifes = 0;
		changeMap = false;
		safeMode = false;
		stackCreated = false;
		wayToLife = new Stack<Point>();
		wayFromLife = new Stack<Point>();
	}
//поиск в ширину (рабочий), нужен для создания пути до жизни и обратно
	static void bfs (Point current, boolean[][] matrix, Point[][] prev, Queue <Point> points) {
		Point p = new Point(current.getX()-1, current.getY());
		if (matrix[p.getX()][p.getY()] == false)
		{ matrix[p.getX()][p.getY()] = true; points.add(p); prev[p.getX()][p.getY()] = current; }
		p = new Point(current.getX()+1, current.getY());
		if (matrix[p.getX()][p.getY()] == false)
		{ matrix[p.getX()][p.getY()] = true; points.add(p); prev[p.getX()][p.getY()] = current; }
		p = new Point(current.getX(), current.getY()-1);
		if (matrix[p.getX()][p.getY()] == false)
		{ matrix[p.getX()][p.getY()] = true; points.add(p); prev[p.getX()][p.getY()] = current; }
		p = new Point(current.getX(), current.getY()+1);
		if (matrix[p.getX()][p.getY()] == false)
		{ matrix[p.getX()][p.getY()] = true; points.add(p); prev[p.getX()][p.getY()] = current; }
	}
//создание пути до конкретной точки
	Stack <Point> init (Point[][]prev, boolean[][]matrix, Point start, Point finish, Point[] kills) {
		Stack <Point> route = new Stack<Point>();
		Queue <Point> points = new LinkedList<Point>();
		matrix[start.getX()][start.getY()] = true;
		points.add(start);
		while(!points.isEmpty()) {
			bfs(points.peek(),matrix,prev,points);
			points.remove();
		}
		Point current = finish;
		int kill = 0;
		while (start.equals(current) == false) {
			current = prev[current.getX()][current.getY()];
			for (int i = 0; i < kills.length; i++)
				if (current.equals(kills[i])) kill++;
		}
		current = finish;
		for (int i = 0; i < kill; i++)
			route.push(finish);
		while (start.equals(current) == false) {
			route.push(current);
			current = prev[current.getX()][current.getY()];
			for (int i = 0; i < kills.length; i++)
				if (current.equals(kills[i])) kill++; 
		}
		route.push(start);
		return route;
	}	
//отладочная функция
	public void printMap () {
		int k = 0;
		System.out.print("\nMap\n"+k+++"\t");
		for (int j = 0; j < Y; j++) {
			for (int i = 0; i < X; i++) {
				System.out.print(map[j][i]);
			}
			System.out.print("\n"+k+++"\t");
		}
		/*k = 0;
		System.out.print("\nLKMap\n"+k+++"\t");
		for (int j = 0; j < Y; j++) {
			for (int i = 0; i < X; i++) {
				System.out.print(LKmap[j][i]);
			}
			System.out.print("\n"+k+++"\t");
		}*/
		System.out.print("\n"+k+"\t");
		for (int i = 0; i < X; i++)
			System.out.print(i);
		System.out.print("\n"+mau5loc.getY()+" "+mau5loc.getX()+" last action="+lastAct+", next direction="+lastDir+", lifeCounter="+lifeCounter+"\n");
	}

	public void changeMap () {
		int Mau5_x = mau5loc.getX();		
		int Mau5_y = mau5loc.getY();
		if (lastAct == Action.Fail) {
			if (lastDir == Direction.Up) map[Mau5_x-1][Mau5_y] = '1';
			if (lastDir == Direction.Down) map[Mau5_x+1][Mau5_y] = '1';
			if (lastDir == Direction.Left) map[Mau5_x][Mau5_y-1] = '1';
			if (lastDir == Direction.Right) map[Mau5_x][Mau5_y+1] = '1';
		}
		if (lastAct == Action.Ok || lastAct == Action.Dead || lastAct == Action.Life) {
			char symbol = '2';
			char LKsymbol = '0';
			if (lastAct == Action.Dead) LKsymbol = 'K';
			if (lastAct == Action.Life) LKsymbol = 'L';
			if (lastDir == Direction.Up) {
				mau5loc = new Point(Mau5_x-1,Mau5_y);
				if (mau5loc.getX() == 0) {
					Y++;
					char[][] LKmap_new = new char[Y][X];
					char[][] map_new = new char[Y][X];
					for (int Idx = 0; Idx < X; Idx++) {
						for (int Jdx = 0; Jdx < Y-1; Jdx++) {
							map_new[Jdx+1][Idx] = map[Jdx][Idx];
							LKmap_new[Jdx+1][Idx] = LKmap[Jdx][Idx];
						}
					}
					for (int Idx = 0; Idx < X; Idx++) {
						map_new[0][Idx] = '0';
						LKmap_new[0][Idx] = '*';
					}
					map = map_new;
					LKmap = LKmap_new;
					mau5loc = new Point(Mau5_x,Mau5_y);
				}
			}
			if (lastDir == Direction.Down) {
				mau5loc = new Point(Mau5_x+1,Mau5_y);
				if (mau5loc.getX() == Y-1) { 
					Y++;
					char[][] LKmap_new = new char[Y][X];
					char[][] map_new = new char[Y][X];
					for (int Idx = 0; Idx < X; Idx++) {
						for (int Jdx = 0; Jdx < Y-1; Jdx++) {
							map_new[Jdx][Idx] = map[Jdx][Idx];
							LKmap_new[Jdx][Idx] = LKmap[Jdx][Idx];
						}
					}
					for (int Idx = 0; Idx < X; Idx++) {
						map_new[Y-1][Idx] = '0';
						LKmap_new[Y-1][Idx] = '*';
					}
					map = map_new;
					LKmap = LKmap_new;
				}
			}
			if (lastDir == Direction.Left) {
				mau5loc = new Point(Mau5_x,Mau5_y-1);
				if (mau5loc.getY() == 0) { 
					X++;
					char[][] map_new = new char[Y][X];
					char[][] LKmap_new = new char[Y][X];
					for (int Idx = 0; Idx < X-1; Idx++) {
						for (int Jdx = 0; Jdx < Y; Jdx++) {
							map_new[Jdx][Idx+1] = map[Jdx][Idx];
							LKmap_new[Jdx][Idx+1] = LKmap[Jdx][Idx];
						}
					}
					for (int Jdx = 0; Jdx < Y; Jdx++) {
						map_new[Jdx][0] = '0';
						LKmap_new[Jdx][0] = '*';
					}
					map = map_new;
					LKmap = LKmap_new;
					mau5loc = new Point(Mau5_x,Mau5_y);
				}
			}
			if (lastDir == Direction.Right) {
				mau5loc = new Point(Mau5_x,Mau5_y+1);
				if (mau5loc.getY() == X-1) { 
					X++;
					char[][] map_new = new char[Y][X];
					char[][] LKmap_new = new char[Y][X];
					for (int Idx = 0; Idx < X-1; Idx++) {
						for (int Jdx = 0; Jdx < Y; Jdx++) {
							map_new[Jdx][Idx] = map[Jdx][Idx];
							LKmap_new[Jdx][Idx] = LKmap[Jdx][Idx];
						}
					}
					for (int Jdx = 0; Jdx < Y; Jdx++) {
						map_new[Jdx][X-1] = '0';
						LKmap_new[Jdx][X-1] = '*';
					}
					map = map_new;
					LKmap = LKmap_new;
				}
			}
			map[mau5loc.getX()][mau5loc.getY()] = symbol;
			LKmap[mau5loc.getX()][mau5loc.getY()] = LKsymbol;
		}
		if (map[Mau5_x][Mau5_y+1] == '1'&&map[Mau5_x][Mau5_y-1] == '1'&&map[Mau5_x+1][Mau5_y] == '1') map[Mau5_x][Mau5_y] = '1';
		if (map[Mau5_x][Mau5_y+1] == '1'&&map[Mau5_x][Mau5_y-1] == '1'&&map[Mau5_x-1][Mau5_y] == '1') map[Mau5_x][Mau5_y] = '1';
		if (map[Mau5_x][Mau5_y+1] == '1'&&map[Mau5_x+1][Mau5_y] == '1'&&map[Mau5_x-1][Mau5_y] == '1') map[Mau5_x][Mau5_y] = '1';
		if (map[Mau5_x][Mau5_y-1] == '1'&&map[Mau5_x+1][Mau5_y] == '1'&&map[Mau5_x-1][Mau5_y] == '1') map[Mau5_x][Mau5_y] = '1';
		if (map[Mau5_x][Mau5_y-1] == '2'&&map[Mau5_x+1][Mau5_y-1] == '2') map[Mau5_x+1][Mau5_y] = '1';
		if (map[Mau5_x][Mau5_y+1] == '2'&&map[Mau5_x+1][Mau5_y+1] == '2') map[Mau5_x+1][Mau5_y] = '1';
		if (map[Mau5_x][Mau5_y+1] == '2'&&map[Mau5_x-1][Mau5_y+1] == '2') map[Mau5_x-1][Mau5_y] = '1';
		if (map[Mau5_x][Mau5_y-1] == '2'&&map[Mau5_x-1][Mau5_y-1] == '2') map[Mau5_x-1][Mau5_y] = '1';
		if (map[Mau5_x+1][Mau5_y] == '2'&&map[Mau5_x+1][Mau5_y-1] == '2') map[Mau5_x][Mau5_y-1] = '1';
		if (map[Mau5_x+1][Mau5_y] == '2'&&map[Mau5_x+1][Mau5_y+1] == '2') map[Mau5_x][Mau5_y+1] = '1';
		if (map[Mau5_x-1][Mau5_y] == '2'&&map[Mau5_x-1][Mau5_y+1] == '2') map[Mau5_x][Mau5_y+1] = '1';
		if (map[Mau5_x-1][Mau5_y] == '2'&&map[Mau5_x-1][Mau5_y-1] == '2') map[Mau5_x][Mau5_y-1] = '1';
		if (Mau5_y > 2 && map[Mau5_x][Mau5_y-2] == '2' && map[Mau5_x][Mau5_y-1] == '0') map[Mau5_x][Mau5_y-1] = '1';
		if (Mau5_y < map[0].length-3 && map[Mau5_x][Mau5_y+2] == '2' && map[Mau5_x][Mau5_y+1] == '0') map[Mau5_x][Mau5_y+1] = '1';
		if (Mau5_x > 2 && map[Mau5_x-2][Mau5_y] == '2' && map[Mau5_x-1][Mau5_y] == '0') map[Mau5_x-1][Mau5_y] = '1';
		if (Mau5_x < map.length-3 && map[Mau5_x+2][Mau5_y] == '2' && map[Mau5_x+1][Mau5_y] == '0') map[Mau5_x+1][Mau5_y] = '1';
		int life = 0;
		int trap = 0;
		for (int Idx = 1; Idx < X-1; Idx++)
			for (int Jdx = 1; Jdx < Y-1; Jdx++) {
				if (LKmap[Jdx][Idx] == 'K') trap++;
				if (LKmap[Jdx][Idx] == 'L') life++;
			}
		lost = trap;
		lifes = life;
		if (safeMode && openLife && !stackCreated) {
			Point[] lifePoints = new Point[lifes];
			Point[] killPoints = new Point[lost];
			int l = 0, k = 0;
			boolean[][]matrix = new boolean[Y][X];
			Point[][] prev = new Point[Y][X];
			for (int j = 0; j < Y; j++) {
				for (int i = 0; i < X; i++) {
					if (LKmap[j][i] == '*') {
						matrix[j][i] = true;
						prev[j][i] = new Point(0,0);
					}
					if (LKmap[j][i] == '0' || LKmap[j][i] == 'L' || LKmap[j][i] == 'K') {
						matrix[j][i] = false;
						prev[j][i] = new Point(0,0);
						if (LKmap[j][i] == 'L') lifePoints[l++] = new Point(j,i);
						if (LKmap[j][i] == 'K') killPoints[k++] = new Point(j,i);
					}
				}
			}
			prev[Mau5_x][Mau5_y] = new Point(0,0);
			for (int i = 0; i < lifePoints.length; i++) {
				Point finish = lifePoints[i];
				Stack <Point> router = init(prev,matrix,mau5loc,finish,killPoints);
				if (router.size() < wayToLife.size() || wayToLife.size() == 0)
					wayToLife = router;
			}			
			Stack <Point> stack = (Stack<Point>) wayToLife.clone();
			while (!stack.isEmpty()) {
				Point p = stack.pop();
				if (LKmap[p.getX()][p.getY()] != 'L') wayFromLife.push(p);
			}
			stackCreated = true;
		}
	}


	public Direction NextMove(Action action) {
		lastAct = action;
		if (changeMap)
			changeMap();
		changeMap = true;
		int mX = mau5loc.getX();
		int mY = mau5loc.getY();
		if (LKmap[mX][mY] == 'L') {
			lifeCounter++;
			openLife = true;
			if (lifeCounter < 5+2*lost) {
				safeMode = true;
			}
			else safeMode = false;
		}
		if (LKmap[mX][mY] == 'K') lifeCounter--;
		Direction direction = Direction.None;
		if (lifeCounter < 2+lost && openLife)
			safeMode = true;
		if (!safeMode) {
			if (!wayFromLife.isEmpty()) {
				Point p = wayFromLife.pop();
				Point q = new Point(mX-1,mY);
				if (p.equals(q)) direction = Direction.Up;
				q = new Point(mX+1,mY);
				if (p.equals(q)) direction = Direction.Down;
				q = new Point(mX,mY-1);
				if (p.equals(q)) direction = Direction.Left;
				q = new Point(mX,mY+1);
				if (p.equals(q)) direction = Direction.Right;
			}
			else {
				if (lastDir == Direction.None) {
					lastDir = lastlastDir;
					lastlastDir = Direction.None;
				}
				if (stackCreated) {
					stackCreated = false;
					wayToLife.clear();
				}
				if ((lastDir == Direction.Down&&map[mX+1][mY] == '0')||(lastDir == Direction.Up&&map[mX-1][mY] == '0')
						||(lastDir == Direction.Left&&map[mX][mY-1] == '0')||(lastDir == Direction.Right&&map[mX][mY+1] == '0'))
					direction = lastDir;
				else {
					Direction reverseDir = Direction.None;
					if (lastDir == Direction.Down) reverseDir = Direction.Up;
					if (lastDir == Direction.Up) reverseDir = Direction.Down;
					if (lastDir == Direction.Left) reverseDir = Direction.Right;
					if (lastDir == Direction.Right) reverseDir = Direction.Left;
					if (reverseDir == Direction.Up || reverseDir == Direction.Down) {
						if (map[mX][mY-1] == '0')
							direction = Direction.Left;
						if (map[mX][mY+1] == '0')
							direction = Direction.Right;
						if (map[mX-1][mY] == '0'&&reverseDir == Direction.Up)
							direction = Direction.Up;
						if (map[mX+1][mY] == '0'&&reverseDir == Direction.Down)
							direction = Direction.Down;
					}
					if (reverseDir == Direction.Left || reverseDir == Direction.Right) {
						if (map[mX-1][mY] == '0')
							direction = Direction.Up;
						if (map[mX+1][mY] == '0')
							direction = Direction.Down;
						if (map[mX][mY-1] == '0'&&reverseDir == Direction.Left)
							direction = Direction.Left;
						if (map[mX][mY+1] == '0'&&reverseDir == Direction.Right)
							direction = Direction.Right;
					}
					if (direction == Direction.None) {
						if ((lastDir == Direction.Down&&map[mX+1][mY] == '2')||(lastDir == Direction.Up&&map[mX-1][mY] == '2')
								||(lastDir == Direction.Left&&map[mX][mY-1] == '2')||(lastDir == Direction.Right&&map[mX][mY+1] == '2'))
							direction = lastDir;
						else {
							if (reverseDir == Direction.Up || reverseDir == Direction.Down) {
								if (map[mX][mY-1] == '2')
									direction = Direction.Left;
								if (map[mX][mY+1] == '2')
									direction = Direction.Right;
							}
							if (reverseDir == Direction.Left || reverseDir == Direction.Right) {
								if (map[mX-1][mY] == '2')
									direction = Direction.Up;
								if (map[mX+1][mY] == '2')
									direction = Direction.Down;
							}
						}
					}
					if (direction == Direction.None)
						direction = reverseDir;
				}
			}
		}
		else {
			if (wayToLife.isEmpty())
				direction = Direction.None;
			else {
				Point p = wayToLife.pop();
				Point q = new Point(mX-1,mY);
				if (p.equals(q)) direction = Direction.Up;
				q = new Point(mX+1,mY);
				if (p.equals(q)) direction = Direction.Down;
				q = new Point(mX,mY-1);
				if (p.equals(q)) direction = Direction.Left;
				q = new Point(mX,mY+1);
				if (p.equals(q)) direction = Direction.Right;
			}
		}
		if (map[mX][mY] == '2'&&LKmap[mX][mY] != 'L'&&LKmap[mX][mY] != 'K') LKmap[mX][mY] = '0';
		if (direction == Direction.None&&lastlastDir == Direction.None) lastlastDir = lastDir;
		lastAct = action;
		lastDir = direction;
		return direction;
	}
}