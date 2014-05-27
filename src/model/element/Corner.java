package model.element;

import model.board.Content;
import model.kicker.Kicker;

public class Corner implements Content {
	private int num;
	
	public Corner(int num){
		this.num=num;
	}

	public int getPos(){
		return num;
	}
	@Override
	public boolean canWalkOver() {
		return true;
	}

	@Override
	public Content interact(Kicker player) {
		return this;
	}

}