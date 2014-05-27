package model.board.level;

import java.awt.Point;
import java.util.LinkedList;
import java.util.List;

import model.board.Board;
import model.board.Content;
import model.element.*;
import model.kicker.Kicker;

/**
 * Decidimos implementar todo lo que refiere a la soluci�n de <code>gameOver()</code> y <code>playerWon()</code> 
 * en la clase <code>GameLevel1</code>.
 * Por esto, agregarmos el campo <code>private List &ltFighter&gt enemies</code>, que tiene almacenados a 
 * los enemigos con vida y que elimina de la lista a aquellos que mueren, a trav�s del metodo
 * <code>refreshEnemies()</code>.
 * La lista la hicimos aca, porque consideramos que el criterio de ganar el juego, en este nivel en particular,
 * es al matar a todos los enemigos, y no es algo especifico del Board.
 */

public class GameLevel2 extends Board {

	private List <Kicker> enemies;

	@Override
	protected Point getPlayerInitPosition(int num) {
		if(num==1){
			playerPosition1=new Point(8,6);
			return playerPosition1;
		}
		else{
			playerPosition2=new Point(9,12);
			return playerPosition2;
		}
	}
	
	@Override
	protected Point getBallInitPosition() {
		ballPosition=new Point(8,3);
		return ballPosition;
	}
	
	
	

	@Override
	public boolean gameOver(){ 
//		refreshEnemies();
		return false;//(!getPlayer().isAlive() || playerWon());
	}

	
	//FALTA IMPLEMENTAAAR!!!! APARECE COMO QUE GANA SIEMPRE
	@Override
	public boolean playerWon() {
		return true;
		//IF LA PELOTA ENTRO EN EL ARCO
	}

	@Override
	protected void setContents() {

		enemies = new LinkedList<Kicker>();
		Lateral lateral = new Lateral();
		Goal goal1 = new Goal(0);
		Goal goal2 = new Goal(180);
		Limit limit = new Limit();
		Out out = new Out();
		Corner cornerLU = new Corner(90);
		Corner cornerLD = new Corner(0);
		Corner cornerRD = new Corner(270);
		Corner cornerRU = new Corner(180);
		
		Opponent opponent1 = new Opponent(new Point(4,4),'a',this);
		Opponent opponent2 = new Opponent(new Point(10,4),'b',this);

		for(int i=0;i<Board.HEIGHT; i++){
			setContent(i,0, out);
			setContent(i,Board.WIDTH-1, out);
		}

		for(int i=0;i<Board.WIDTH; i++){
			setContent(0,i, out);
			setContent(Board.HEIGHT-1,i, out);
		}


		for(int i=1;i<Board.HEIGHT-1; i++){

			if(i!=Board.HEIGHT/2){
				setFixContent(i,1,limit);
				setFixContent(i,Board.WIDTH-2,limit);
				//setContent(i,1, limit);
				//setContent(i,Board.WIDTH-2, limit);
			}
			else{
				setContent(i,1, goal1);
				setContent(i,Board.WIDTH-2, goal2);

			}
		}

		for(int i=1;i<Board.WIDTH-1; i++){
			//setContent(1,i, lateral);
			//setContent(Board.HEIGHT-2,i, lateral);
			setFixContent(1,i,lateral);
			setFixContent(Board.HEIGHT-2,i,lateral);
		}

		setFixContent(Board.HEIGHT-2, 1, cornerLD );
		setFixContent(1, 1, cornerLU );
		setFixContent(Board.HEIGHT-2,Board.WIDTH-2, cornerRD );
		setFixContent(1, Board.WIDTH-2, cornerRU );


		setContent(opponent1.getX(), opponent1.getY(), opponent1);
		setContent(opponent2.getX(), opponent2.getY(), opponent2);

	}
	private void setFixContent(int x, int y, Content content) {
		g()[y][x].setFixContent(content);		
	}

	/**
	 * El metodo <code>setContent(int x, int y, Content c)</code> lo creamos para agregar contenido a la celda.
	 * @param x
	 * @param y
	 * @param c
	 */
	private void setContent(int x, int y, Content c){
		g()[y][x].setContent(c);
	}

	/**
	 * Este metodo <code>setContent(int x, int y, Kicker f)</code> es sobrecargado, y dentro del mismo se utiliza el
	 * metodo <code>setContent(int x, int y, Content c)</code> y luego agrega al fighter
	 * a la lista de fighters.
	 * @param x
	 * @param y
	 * @param f
	 */
//	private void setContent(int x, int y, Kicker f){
//		setContent(x,y,(Content)f);
//		enemies.add(f);
//	}

	/**
	 * 	El metodo <code>refreshEnemies()</code> se encarga de recorrer la Lista de enemigos 
	 * y chequear si alguno muri�. En caso de que as� sea, se elimina dicho enemigo de la lista.
	 * Como durante el uso de un iterador no se puede alterar una coleccion, utilizamos un while 
	 * en lugar de un for each.  
	 */
//	private void refreshEnemies(){	
//		Iterator<Kicker> it = enemies.iterator();
//		while (it.hasNext()){
//			if(!it.next().isAlive())
//				it.remove();
//		}
//	}
}