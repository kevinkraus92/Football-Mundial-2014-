package model.kicker;

import model.board.Content;
import model.board.Move;

public class Player implements Kicker {

	private int num;
	private Move movement;

	public Player(int num){
		this.num=num;
		if (num==1)
			movement= Move.RIGHT;
		else
			movement= Move.LEFT;
	}

	public int getNum(){
		return num;
	}
	
	protected Kicker getPlayer() {
		return this;
	}

	@Override
	public boolean canWalkOver() {
		return false;
	}

	@Override
	public Content interact(Kicker player) {
		return this;
	}

	public void setMovement(Move move) {
		this.movement=move;

	}
	public Move getMovement(){
		return movement;
	}


}
