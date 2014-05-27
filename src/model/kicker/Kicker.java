package model.kicker;

import model.board.Content;
import model.board.Move;

public interface Kicker extends Content {

	
	public void setMovement(Move move);

	public Move getMovement();

	public int getNum();
}
