package Bots;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import WoodEngine.Action;
import WoodEngine.Direction;
import WoodEngine.Point;

public class Mouse implements IMouse {
	
	private int m_hp;
	private Direction m_prevMove;
	private Point m_curCell;
	private HashMap<Point, Character> m_woodGraph;
	private boolean m_onTrap;
	private boolean m_onLife;
	public final String NAME;
	
	public Mouse(String name){
		NAME = name;
		m_hp = 3;
		m_curCell = new Point(0, 0);
		m_prevMove = null;
		m_onTrap = false;
		m_onLife = false;
		m_woodGraph = new HashMap<Point, Character>();
	}

	@Override
	public Direction NextMove(Action action) throws Exception {
		
		if (m_prevMove == null){ // FIRST STEP TO NOWHERE
			m_prevMove = Direction.None;
			return Direction.None;
		}
		
		if (m_prevMove == Direction.None && action == Action.Fail){ // shit happens :(
			throw new IOException("I'm in a wall :(");
		}
		
		Point nextCell = m_curCell.MoveTo(m_prevMove); // ogogog
		if (m_woodGraph.containsKey(nextCell) && m_woodGraph.get(nextCell) == '4'){ // '4' - possible unknown way
			m_woodGraph.remove(nextCell);
		}
		switch(action){
		case Dead:
			m_woodGraph.put(nextCell, '2');
			m_onTrap = true;
			m_onLife = false;
			m_hp = m_hp - 1;
			m_curCell = nextCell;
			break;
		case Fail:
			m_woodGraph.put(nextCell, '1');
			if(m_onTrap){
				m_hp = m_hp - 1;
			}
			if(m_onLife){
				m_hp = m_hp + 1;
			}
			break;
		case Ok:
			m_woodGraph.put(nextCell, '0');
			m_onTrap = false;
			m_onLife = false;
			m_curCell = nextCell;
			break;
		case Life:
			m_woodGraph.put(nextCell, '3');
			m_onTrap = false;
			m_onLife = true;
			m_hp = m_hp + 1;
			m_curCell = nextCell;
			break;
		default:
			throw new IOException("Illegal Action");
		}
		Point unknownCellU = nextCell.MoveUp(); // unkown spots to go
		Point unknownCellD = nextCell.MoveDown();
		Point unknownCellL = nextCell.MoveLeft();
		Point unknownCellR = nextCell.MoveRigth();
		if(!m_woodGraph.containsKey(unknownCellD)){
			m_woodGraph.put(unknownCellD, '4');
		}
		if(!m_woodGraph.containsKey(unknownCellU)){
			m_woodGraph.put(unknownCellU, '4');
		}
		if(!m_woodGraph.containsKey(unknownCellL)){
			m_woodGraph.put(unknownCellL, '4');
		}
		if(!m_woodGraph.containsKey(unknownCellR)){
			m_woodGraph.put(unknownCellR, '4');
		}
		return NextDirection();
	}

	private Direction NextDirection() throws Exception {
		if(m_onLife && m_hp <= 5){
			return Direction.None;
		}
		if(m_hp <= 2){
			try {
				return FindAWayTo('3');
			} catch (Exception e) {
				if(e.getMessage() != "No solutions found"){
					e.printStackTrace();
				}
				else{
					return Direction.None;
				}
			}
		}
		return FindAWayTo('4');
	}

	private Direction FindAWayTo(char c) throws Exception {
		ArrayList<Solution> sol = new ArrayList<Solution>();
		for (Point dest : m_woodGraph.keySet()) {
			if (m_woodGraph.get(dest) == c) {
				try {
					sol.add(WaveSearch(dest));
				} catch (Exception e) {
					if(e.getMessage() == "No way found"){
						continue;
					}
					if(e.getMessage() == "WTF"){
						continue;
					}
					e.printStackTrace();
				}
			}
		}
		if(sol.isEmpty()){
			if(c == '3') return FindAWayTo('4');
			else throw new Exception("No solutions found");
		}
		Direction res = Direction.None;
		int l = -10;
		int s = 999;
		for (Solution sltn : sol) {
			if(sltn.cost.LIFES + m_hp >= 0){
				// 5,0 - 9,3
				if(sltn.cost.STEPS - s <= (sltn.cost.LIFES - l)*4 &&  sltn.dir != OppositDir(m_prevMove)){
					s = sltn.cost.STEPS;
					l = sltn.cost.LIFES;
					res = sltn.dir;
				}
			}
		}
		m_prevMove = res;
		return res;
	}

	private Direction OppositDir(Direction dir) {
		if(dir == Direction.Up) return Direction.Down;
		if(dir == Direction.Down) return Direction.Up;
		if(dir == Direction.Left) return Direction.Right;
		if(dir == Direction.Right) return Direction.Left;
		if(dir == Direction.None) return Direction.None;
		return dir;
	}

	private Solution WaveSearch(Point dest) throws Exception {
		// preparation
		int steps = 0;
		boolean stop = false;
		HashMap<Point, CellCost> costs = new HashMap<Point, CellCost>();
		for (Point cell : m_woodGraph.keySet()) {
			// exclude walls 'n' other possible ways
			if((m_woodGraph.get(cell) == '4' || m_woodGraph.get(cell) == '1')
					&& cell != dest) continue;
			// fill costs
			int l = 0;
			if(m_woodGraph.get(cell) == '2') l = -1;
			if(m_woodGraph.get(cell) == '3') l = 1;
			costs.put(cell, new CellCost(-1, l));
		}
		// wave
		costs.put(m_curCell, new CellCost(steps, 0));
		while( !stop && costs.get(dest).STEPS == -1){
			stop = true;
			for (Point cell : costs.keySet()) {
				if(costs.get(cell).STEPS == steps){
					if( costs.containsKey(cell.MoveDown()) && costs.get(cell.MoveDown()).STEPS == -1 ){
						int ld = costs.get(cell.MoveDown()).LIFES + costs.get(cell).LIFES;
						if(ld + m_hp < 0){
							costs.put(cell.MoveDown(), new CellCost(-2, ld));
						}
						else{
							stop = false;
							costs.put(cell.MoveDown(), new CellCost(steps + 1, ld));
						}
					}
					if( costs.containsKey(cell.MoveUp()) && costs.get(cell.MoveUp()).STEPS == -1 ){
						int lu = costs.get(cell.MoveUp()).LIFES + costs.get(cell).LIFES;
						if(lu + m_hp < 0){
							costs.put(cell.MoveUp(), new CellCost(-2, lu));
						}
						else{
							stop = false;
							costs.put(cell.MoveUp(), new CellCost(steps + 1, lu));
						}
					}
					if( costs.containsKey(cell.MoveLeft()) && costs.get(cell.MoveLeft()).STEPS == -1 ){
						int ll = costs.get(cell.MoveLeft()).LIFES + costs.get(cell).LIFES;
						if(ll + m_hp < 0){
							costs.put(cell.MoveLeft(), new CellCost(-2, ll));
						}
						else{
							stop = false;
							costs.put(cell.MoveLeft(), new CellCost(steps + 1, ll));
						}
					}
					if( costs.containsKey(cell.MoveRigth()) && costs.get(cell.MoveRigth()).STEPS == -1 ){
						int lr = costs.get(cell.MoveRigth()).LIFES + costs.get(cell).LIFES;
						if(lr + m_hp < 0){
							costs.put(cell.MoveRigth(), new CellCost(-2, lr));
						}
						else{
							stop = false;
							costs.put(cell.MoveRigth(), new CellCost(steps + 1, lr));
						}
					}
				}
			}
			steps = steps + 1;
		}
		// check
		if(costs.get(dest).STEPS == -1){
			throw new Exception("No way found");
		}
		// recostruction
		Direction prevMove = Direction.None;
		int step = costs.get(dest).STEPS;
		Point curCell = dest;
		while(step > 0){
			step = step - 1;
			for (Point cell : costs.keySet()) {
				if( cell.MoveDown().equals(curCell) && costs.get(cell).STEPS == step ){
					curCell = cell;
					prevMove = Direction.Down;
				}
				if( cell.MoveUp().equals(curCell) && costs.get(cell).STEPS == step ){
					curCell = cell;
					prevMove = Direction.Up;
				}
				if( cell.MoveLeft().equals(curCell) && costs.get(cell).STEPS == step ){
					curCell = cell;
					prevMove = Direction.Left;
				}
				if( cell.MoveRigth().equals(curCell) && costs.get(cell).STEPS == step ){
					curCell = cell;
					prevMove = Direction.Right;
				}
			}
		}
		if(prevMove == Direction.None){
			throw new Exception("WTF");
		}
		return new Solution(prevMove, costs.get(dest));
	}

}