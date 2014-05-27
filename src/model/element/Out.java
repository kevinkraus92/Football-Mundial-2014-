package model.element;

import model.board.Content;
import model.kicker.Kicker;

public class Out implements Content{

	@Override
	public boolean canWalkOver() {
		return false;
	}

	@Override
	public Content interact(Kicker player) {
		return this;
	}

}
