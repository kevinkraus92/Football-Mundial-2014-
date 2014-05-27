package model.element;

import java.awt.Point;

import model.board.Board;
import model.board.Content;
import model.board.ListenerPlayerMove;
import model.board.Move;
import model.kicker.Kicker;

public class Opponent implements Content, /*Kicker ,*/ ListenerPlayerMove{

	private Point position;
	private Board board;
	private int movement;
	private char type;

	public Opponent(Point position, char type,Board board){
		board.addListenerPlayerMove(this);
		this.position=position;
		this.type=type;
		this.board=board;
	}


	public int getX() {
		return position.x;
	}

	public int getY() {
		return position.y;
	}


	@Override
	public boolean canWalkOver() {
		return false;
	}

	@Override
	public Content interact(Kicker player) {
		return this;
	}

	//POR EL MOMENTO OPPONENT NO IMPLEMENTA KICKER (SUPONIENDO QUE NO VA A PATEAR LA PELOTA)
	//	@Override
	//	public void setMovement(Move move) {
	//		// TODO Auto-generated method stub
	//		
	//	}
	//
	//	@Override
	//	public Move getMovement() {
	//		// TODO Auto-generated method stub
	//		return null;
	//	}

	@Override
	public void actionPlayerMove(Move move) {
		movement++;
		Point newPosition;
		if(movement%2==1){
			if(type=='a')
				newPosition= new Point(getX(),getY()+1);
			else 
				newPosition= new Point(getX()+1,getY());
		}
		else{
			if(type=='a')
				newPosition= new Point(getX(),getY()-1);
			else
				newPosition= new Point(getX()-1,getY());	
		}
		if(!board.getPlayerPosition(1).equals(newPosition) && !board.getPlayerPosition(2).equals(newPosition)){
			if(!board.getBallPosition().equals(newPosition)){
				board.getG()[newPosition.y][newPosition.x].setContent(this);
				board.getG()[getY()][getX()].removeContent();
				position=newPosition;
			}
			else
				movement++;
		}
		else
			movement++;

	}

}