package model.element;

import model.board.Content;
import model.kicker.Kicker;
import model.kicker.Player;



public class Goal implements Content {

	private int num;
	
	public Goal(int num){
		this.num=num;
	}
	
	public int getNum(){
		return num;
	}
	
	@Override
	public boolean canWalkOver() {
		return true;
	}

	@Override
	public Content interact(Kicker kicker) {

		return new Ball();
	}
	
	public void won(Player py) {

	}
	
}