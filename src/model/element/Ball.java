package model.element;

import model.board.Content;
import model.kicker.Kicker;

public class Ball implements Content{

	@Override
	public boolean canWalkOver() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public Content interact(Kicker player) {
		// TODO Auto-generated method stub
		return player;
	}

}
