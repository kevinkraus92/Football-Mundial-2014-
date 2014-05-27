package model;

import java.awt.Point;
import java.io.Serializable;

import model.board.Board;
import model.board.Cell;
import model.board.Move;
import model.kicker.Player;

public class Game implements Serializable{
	
	private Board board;
	
	public Game(Board board){
		this.board=board;
	}
	
	public void onMove(Move move, int num) {
		board.playerMove(move, num);
	}
	
	public int getBoardHeight() {
		return Board.HEIGHT;
	}
	
	public int getBoardWidth() {
		return Board.WIDTH;
	}
	
	public Point getPlayerPosition(int num) {
		return board.getPlayerPosition(num);
	}
	

	public Board getBoard() {
		return board;
	}
	
	public Cell get(int x, int y) {
		return board.get(x, y);
	}
	
	public Player getPlayer(int num) {
		return board.getPlayer(num);
	}
	
	public boolean isOver() {
		return board.gameOver();
	}
	
	public boolean playerWon() {
		return board.playerWon();
	}
}
