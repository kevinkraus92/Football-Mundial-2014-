package model.board;

import java.io.Serializable;

import model.kicker.Kicker;

public interface Content extends Serializable{
	
	public boolean canWalkOver();
	public Content interact(Kicker player);
	
}