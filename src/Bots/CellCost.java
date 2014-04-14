package Bot;

public class CellCost {
	public int steps;
	public int lifes;
	
	public CellCost(int st, int l){
		steps = st;
		lifes = l;
	}
	
	@Override
	public String toString(){
		return "(S(" + steps + "), L(" + lifes + "))";
	}
}
