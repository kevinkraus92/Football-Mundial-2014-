package model.board;

import java.io.Serializable;


public interface ListenerPlayerMove extends Serializable{
	
	public void actionPlayerMove(Move move);

}
