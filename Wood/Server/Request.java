package Server;

import java.io.Serializable;

import Enums.Direction;

public class Request implements Serializable {

	private static final long serialVersionUID = 1L;

	enum Type {
		CreateNewWoodman,
		Move;
	}
	public Type type;
	public String name;
	public Direction direction;
	
	/**
	 * Type: Move
	 * @param name_ - String
	 * @param direction_ - Direction
	 */
	public Request(String name_, Direction direction_)
	{
		type = Type.Move;
		name = name_;
		direction = direction_;
	}
	
	public Request(String name_) {
		type = Type.CreateNewWoodman;
		name = name_;
		direction = null;
	}
	
	public boolean isMove() {
		return type == Type.Move;
	}
}
