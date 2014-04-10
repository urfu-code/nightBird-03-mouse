package Bots;

public class CellCost {
	public int STEPS;
	public int LIFES;
	
	public CellCost(int st, int l){
		STEPS = st;
		LIFES = l;
	}
	
	@Override
	public String toString(){
		return "(S(" + STEPS + "), L(" + LIFES + "))";
	}
}
