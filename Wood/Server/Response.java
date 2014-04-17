package Server;

import java.io.Serializable;

import Enums.Action;

public class Response implements Serializable{

	private static final long serialVersionUID = 1L;

	Action action;
	Response(Action action_) {
		action = action_;
	}
}
