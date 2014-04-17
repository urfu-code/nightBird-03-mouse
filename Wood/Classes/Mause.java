package Classes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

import Enums.Action;
import Enums.Direction;
import Interfaces.Mouse;

public class Mause implements Mouse {
	enum Task {
		goToFinish, 	//идти вперед по правилу левой руки
		goToLife,		//возвращаться назад к жизни
		increaseLife,	//встретил на пути жизнь и копим на ней жизни
		goTheEndOfWay;	//вернуться к месте, откуда пошли назад зажизнью
	}
//Будем ходить по правилу правой руки
	HashMap<Point, Action> map;
	private final int enoughLifes = 4;
	private final int reserveLives = 7;
	private int lifeCount;
	//вектор направления движения
	private Point moveDirectionVector;
	//Текущее цель мыши
	private Task task;
	//Направление прошлого хода
	//Точка, в которой мы были перед тем, как пошли обратно за жизнями
	//Текущее положение мыши
	private Point thisPoint;
	//Точка, котротой была мыш перед попыткой перейни на новую точку
	private Point nextPoint;
	//Точка, на которую надо вернуть, когда мы копим жизни и ткнулись ради разветки в другое место
	private Point needReturn;
	private Point DirectionVectorInIncrease;
	//Путь до ближайшей жизни
	private ArrayList <Point> wayToLife;
	private int indexInWayToLife;
	private int needLifes;
	public Mause() {
		lifeCount = 3;
		//Хочу, что бы первым движением мышь шла вниз, тк мы повернем вектор налево, то указываем вектор,
		//		направленный налево
		moveDirectionVector = new Point (-1, 0);
		thisPoint = new Point (0, 0);
		nextPoint = null;
		map = new HashMap<>();
		wayToLife = null;
		task = Task.goToFinish;
	}
	//Поворот вектора движения налево
	private Point turnLeft(Point pt) {
		return new Point(pt.getY(), -1*pt.getX());
	}
	//Поворот вектора движения направо
	private Point turnRight(Point pt) {
		return new Point(-1*pt.getY(), pt.getX());
	}
	
	//Конвертация вектора движения в дирекшена
	private Direction pointToDirection(Point p) {
		if (p.equals(new Point(0, 0)))
			return Direction.None;
		if (p.equals(new Point(1, 0)))
			return Direction.Right;
		if (p.equals(new Point(0, 1)))
			return Direction.Down;
		if (p.equals(new Point(-1, 0)))
			return Direction.Left;
		if (p.equals(new Point(0, -1)))
			return Direction.Up;
		throw new RuntimeException();		
	}
	//Конвертация дирекшена в вектор движения
	private Point directionToPoint(Direction d)
	{
		switch (d) {
		case Down:
			return new Point (0, 1);
		case Left:
			return new Point(-1, 0);
		case Up:
			return new Point(0, -1);
		case Right:
			return new Point(1, 0);
		case None:
			return new Point(0, 0);
		}
		return null;
	}
	
	private int haveEdge(Point p, Point t) {
		if (!map.containsKey(t))
			return 0;
		int len =  (p.getX() - t.getX())*(p.getX() - t.getX()) + (p.getY() - t.getY())*(p.getY() - t.getY());
		if (len != 1)
			return 0;
		if (map.get(t) == Action.Fail)
			return 0;
		if (map.get(t) == Action.Dead)
			return 2;
		return 1;
	}
	private ArrayList<Point> adjacent(Point p) {
		ArrayList<Point> result = new ArrayList<>();
		if (haveEdge(p, p.MoveDown()) != 0)
			result.add(p.MoveDown());
		if (haveEdge(p, p.MoveLeft()) != 0)
			result.add(p.MoveLeft());
		if (haveEdge(p, p.MoveUp()) != 0)
			result.add(p.MoveUp());
		if (haveEdge(p, p.MoveRigth()) != 0)
			result.add(p.MoveRigth());
		return result;
	}
	private ArrayList<Point> Dijkstra(Point a) {
		//Алгоритм Дейкстры для поиска кротчайших расстояний в взвешенном графе
		int max = map.size() * map.size();
		//Массив использованных вершин
		HashSet<Point> used = new HashSet<>();
		//Массив расстояний от начальной точки
		HashMap<Point, Integer> distances = new HashMap<>();
		//Массив путей от начальной точки
		HashMap<Point, LinkedList<Point> > ways = new HashMap<>();
		//Массив вершин
		HashMap<Point, Action> V = new HashMap<>();
		//Отсеим из Карты (map) все стенки и положим в V
		for (Point i: map.keySet()) {
			if (map.get(i) != Action.Fail)
				V.put(i, map.get(i));
		}
		for (Point i: V.keySet()) {
			distances.put(i, max);
			ways.put(i, new LinkedList<Point>());
		}
		distances.put(a, 0);
		while (true) {
			Point w = null;
			int minDist = max;
			for (Point i: distances.keySet()) {
				if ( (!used.contains(i)) && (distances.get(i) < minDist) ) {
					minDist = distances.get(i);
					w = i;
				}
			}
			if (w == null)
				break;
			used.add(w);
			int thisDist = distances.get(w);
			
			ArrayList<Point> adjArr = adjacent(w);
			for (int i=0; i<adjArr.size(); i++) {
				Point next = adjArr.get(i);
				if (distances.get(next) > thisDist + haveEdge(next, w) /*distances.get(w)*/ ) {
					distances.put(next, thisDist + haveEdge(next, w) /*distances.get(w)*/);
					LinkedList<Point> tempList = new LinkedList<>();
					tempList.addAll(ways.get(w));
					tempList.add(next);
					ways.put(next, tempList);
				}
			}
		}
		//Массив точек жизней
		ArrayList<Point> lives = new ArrayList<>();
		for (Point i: map.keySet()) {
			if (map.get(i) == Action.Life)
				lives.add(i);
		}
		
		Point live = null;
		int minDist = max;
		
		//Массив достижимых точек жизней
		ArrayList<Point> goodLives = new ArrayList<>();
		for (int i=0; i<lives.size(); i++) {
			int fare = 0;
			for (Point j: ways.get(lives.get(i))) {
				if (map.get(j) == Action.Dead)
					fare++;
			}
			if (lifeCount - fare > -1)
				goodLives.add(lives.get(i));
		}
		if (goodLives.size() == 0)
			return null;
		for (int i=0; i< goodLives.size(); i++)
			System.out.println(goodLives.get(i).toString() +" " + distances.get(goodLives.get(i)));
		//Выбираем из достижимых самую близкую
		for (int i=0; i<goodLives.size(); i++) {
			if (distances.get(goodLives.get(i)) < minDist) {
				minDist = distances.get(goodLives.get(i));
				live = goodLives.get(i);
			}
		}
		//Дописываем в начало исходную точку
		ways.get(live).add(0, thisPoint);
		return new ArrayList<Point>(ways.get(live));
	}
	
	private boolean buildWayToLife()
	{
		ArrayList<Point> way = Dijkstra(thisPoint);
		if (way == null)
			return false;
		int fare = 0;
		for (int i=1; i<way.size(); i++) {
			if (map.get(way.get(i)) == Action.Dead)
				fare++;
		}
		wayToLife = way;
		needLifes = reserveLives + fare;
		indexInWayToLife = 0;
		return true;
	};

	private Direction setNextNone()
	{
		nextPoint = thisPoint;
		return Direction.None;
	}

	private Direction setNext(Direction dir) {
		Point dirPointVector = directionToPoint(dir);
		nextPoint = thisPoint.add(dirPointVector);
		moveDirectionVector= dirPointVector;
		return dir;
	}
	
	@Override
	public Direction NextMove(Action action) {
		//по дефолту nextPoint=null, если так, значит это первый ход => вернем None и узнаем на какой мы стоим клетке
		if (nextPoint == null)
			return setNextNone();
		if (action != Action.Fail)
			thisPoint = nextPoint;
		
		map.put(nextPoint, action);
		
		switch (map.get(thisPoint)) {
			case Dead:
				lifeCount--;
				break;
			case Life:
				lifeCount++;
				break;
			default:
				break;
		}
		//если наступаем на капкан, то смотрим сколько жизней, смотрим есть ли известаня
		//жизнь и достижима ли она, и принимаем решение: идти за жизнью или продолжать поиск финиша
		if (task == Task.goToFinish && map.get(thisPoint)==Action.Dead ) {
			if ( (lifeCount<=3) && (map.containsValue(Action.Life)) )
				if (buildWayToLife()) {
					task = Task.goToLife;
				}
		}
		//Если мы находимся в состоянии движения к жизни
		if (task == Task.goToLife) {
			//если мы в конце пути (стоим на жизни)
			if (indexInWayToLife == wayToLife.size()-1) {
				//копим жизни
				if (lifeCount <= needLifes)
					return setNextNone();
				else {
					//переходим к задаче: вернуться в точку, в которой мы перешли к поиску жизни
					task = Task.goTheEndOfWay;
				}
			}
			//Если мы еще не дошли для искомой жизни
			else {
				//Вычесляем новое направление движения
				Point thisP = wayToLife.get(indexInWayToLife);
				Point nextP = wayToLife.get(indexInWayToLife+1);
				Point nextEnter = new Point(nextP.getX() - thisP.getX(), nextP.getY() - thisP.getY());
				//Запоминаем, что пршли новую точку в пути
				indexInWayToLife++;
				return setNext(pointToDirection(nextEnter));
			}
		}
		
		//Если мы находимся в состоянии движения к точке, в которой мы перешли к поиску жизни
		if (task == Task.goTheEndOfWay) {
			//Если мы уже вернулись к этой точке
			if (indexInWayToLife == 0)
				//Переходим в состояния движения к финишу
				task = Task.goToFinish;
			else {
				//Иначе вычисляем направление нового шага
				Point thisP = wayToLife.get(indexInWayToLife);
				Point nextP = wayToLife.get(indexInWayToLife-1);
				Point nextEnter = new Point(nextP.getX() - thisP.getX(), nextP.getY() - thisP.getY());
				//Не забываем отметить, что мы прошли точку в пути
				indexInWayToLife--;
				return setNext(pointToDirection(nextEnter));
			}
		}
		
		//Если мы находимся в состоянии движения к финишу
		if (task == Task.goToFinish) {
			//Если мы наступили на жизнь
			if ( map.get(thisPoint)==Action.Life )
				//Если у нас мало жизней (<=4)
				if (lifeCount <= enoughLifes){
					//то немного постоим
					task = Task.increaseLife;
					DirectionVectorInIncrease = moveDirectionVector;
					needReturn = thisPoint;
				}
			
			
		}
		
		//Если мы стоим на жизни и копим
		if (task == Task.increaseLife) {
			if (!thisPoint.equals(needReturn)) {
				Point nextDirectionVector = DirectionVectorInIncrease.revers();
				nextPoint = thisPoint.add(nextDirectionVector);
				return pointToDirection(nextDirectionVector);
			}
			if (lifeCount == 0)
				return Direction.None;
			if (lifeCount > reserveLives) {
				task = Task.goToFinish;
				nextPoint = needReturn;
				lifeCount--;
				return NextMove(Action.Life);	
			}
			else {
				/*Point nextDirectionVector = turnLeft(DirectionVectorInIncrease);
				for (int i=0; i<4; i++) {
					if (!map.containsKey(thisPoint.add(nextDirectionVector))) {
						nextPoint = thisPoint.add(nextDirectionVector);
						DirectionVectorInIncrease = nextDirectionVector;
						return setNext(pointToDirection(nextDirectionVector));
					}
					else
						nextDirectionVector = turnLeft(nextDirectionVector);
				}
				return setNextNone();*/
				return setNextNone();
				
			}
			
		}
		if (task == Task.goToFinish) {
			//Если мы на прошлом шаге ударились в стену
			if (action == Action.Fail) {
				//то поворачиваем направо
				Point nextMoveDirection = turnRight(moveDirectionVector);
				Point nextPoint = thisPoint.add(nextMoveDirection);
				while (map.containsKey(nextPoint) && map.get(nextPoint) == Action.Fail) {
					nextMoveDirection = turnRight(nextMoveDirection);
					nextPoint = thisPoint.add(nextMoveDirection);
				}
				return setNext(pointToDirection(nextMoveDirection));
			}
			//Еслимы не ударились об стену
			else{
				//то повернем налево
				Point nextMoveDirection = turnLeft(moveDirectionVector);
				Point nextPoint = thisPoint.add(nextMoveDirection);
				while (map.containsKey(nextPoint) && map.get(nextPoint) == Action.Fail) {
					nextMoveDirection = turnRight(nextMoveDirection);
					nextPoint = thisPoint.add(nextMoveDirection);
				}
				return setNext(pointToDirection(nextMoveDirection));
			}
		}

		throw new RuntimeException("ВСЕ ПЛОХО!");
	}

}
		
