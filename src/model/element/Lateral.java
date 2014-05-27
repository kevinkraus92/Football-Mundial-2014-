package model.element;

import model.board.Content;
import model.kicker.Kicker;

public class Lateral implements Content {

	@Override
	public boolean canWalkOver() {
		return true;
	}

	@Override
	public Content interact(Kicker player) {
		return this;
	}

}
