package Bot;

import WoodEngine.Direction;

public class Solution {
	public Direction dir;
	public CellCost cost;
	
	public Solution(Direction d, CellCost c){
		dir = d;
		cost = c;
	}
	
	@Override
	public String toString(){
		return dir.toString() + "=" + cost.toString();
	}
}
