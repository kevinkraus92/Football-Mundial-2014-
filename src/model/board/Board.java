package model.board;

import java.awt.Point;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.sun.org.apache.xalan.internal.utils.XMLSecurityManager.Limit;

import model.element.Ball;
import model.element.Goal;
import model.element.Lateral;
import model.kicker.Kicker;
import model.kicker.Player;

public abstract class Board implements Serializable{

	public static final int HEIGHT = 15;
	public static final int WIDTH = 30;

	private Cell[][] g = new Cell[WIDTH][HEIGHT];
	protected List <Kicker> opponents;
	protected Point playerPosition1;
	protected Point playerPosition2;
	protected Point ballPosition;
	protected int score1;
	protected int score2;

	private Set <ListenerPlayerMove> listenersPlayerMove = new HashSet<ListenerPlayerMove>();

	public Board() {
		for (int x = 0; x < HEIGHT; x++) {
			for (int y = 0; y < WIDTH; y++) {
				g[y][x] = new Cell();
			}
		}

		setContents();

		Player player1 = new Player(1);
		Player player2 = new Player(2);

		playerPosition1 = getPlayerInitPosition(1);
		g[playerPosition1.y][playerPosition1.x].setContent(player1);
		playerPosition2 = getPlayerInitPosition(2);
		g[playerPosition2.y][playerPosition2.x].setContent(player2);

		Ball ball = new Ball();
		ballPosition = getBallInitPosition();
		g[ballPosition.y][ballPosition.x].setContent(ball);

		score1 = 0;
		score2 = 0;
	}

	/**
	 * El metodo <code>addListenerPlayerMove</code> agrega Listeners al set de <code>&ltListerPlayerMove&gt</code>
	 * @param content
	 */

	public void addListenerPlayerMove(ListenerPlayerMove content){
		listenersPlayerMove.add(content);
	}

	public Cell[][] getG() {
		return g;
	}

	public void playerMove(Move move, int num) {
		Point newPlayerPosition;
		if(num==1)
			newPlayerPosition = new Point(playerPosition1.x + move.getX(), playerPosition1.y + move.getY());
		else
			newPlayerPosition = new Point(playerPosition2.x + move.getX(), playerPosition2.y + move.getY());
		getPlayer(num).setMovement(move);
		Cell newPlayerPositionCell = g[newPlayerPosition.y][newPlayerPosition.x];

		//si la nueva posicion del player esta dentro del tablero
		if (newPlayerPosition.x >= 0 && newPlayerPosition.y >= 0 && newPlayerPosition.x < HEIGHT && newPlayerPosition.y < WIDTH) {

			//si la nueva posicion del player es "pisable"
			if (newPlayerPositionCell.canWalkOver()) {

				//dentro de lo "pisable", si es una pelota
				if(newPlayerPositionCell.getContent() instanceof Ball){

					Point newBallPosition = new Point(ballPosition.x + move.getX(), ballPosition.y + move.getY());
					Cell newBallPositionCell = g[newBallPosition.y][newBallPosition.x];

					//si la nueva posicion de la pelota esta dentro de la cancha o es el arco
					if((newBallPosition.x >= 2 && newBallPosition.y >= 2 && newBallPosition.x < HEIGHT-2 && newBallPosition.y < WIDTH-2) || (g[newBallPosition.y][newBallPosition.x].getContent() instanceof Goal))

						//si la nueva posicion de la pelota es pisable
						if (newBallPositionCell.canWalkOver()) {

							//si la nueva posicion de la pelota es el arco
							if(newBallPositionCell.getContent() instanceof Goal){
								Goal goal = (Goal) newBallPositionCell.getContent();
								//es goal, movel la pelota a la mitad de la cancha

								Player p1 = getPlayer(1);
								Player p2 = getPlayer(2);
								//mover a los player a sus lugares
								if(g[WIDTH/2][HEIGHT/2].getContent()!=null){
									Player p = (Player) g[WIDTH/2][HEIGHT/2].getContent();
									if(p.getNum()==1){
										g[WIDTH/4][HEIGHT/2].setContent(p);
										p.setMovement(Move.RIGHT);

									}
									else{
										g[3*WIDTH/4][HEIGHT/2].setContent(p);
										p.setMovement(Move.LEFT);
									}
								}
								movePlayer(new Point(HEIGHT/2,WIDTH/4),p1);
								getPlayer(1).setMovement(Move.RIGHT);
								movePlayer(new Point(HEIGHT/2,3*WIDTH/4),p2);
								getPlayer(2).setMovement(Move.LEFT);

								moveBall(new Point(HEIGHT/2,WIDTH/2),getBall());
								//anotar gol
								if(goal.getNum()==1){
									score2++;
									System.out.println(score2);
								}
								else{
									score1++;
									System.out.println(score1);
								}


							}
							else{	
								moveBall(newBallPosition,getBall());
								movePlayer(newPlayerPosition,getPlayer(num));
								alertPlayerMove(move);
							}
						}
				}
				else if((newPlayerPositionCell.getContent() instanceof Goal)){
				}
				else{
					movePlayer(newPlayerPosition,getPlayer(num));
					alertPlayerMove(move);
				}

			}//POR AHORA NO HACE FALTA ESTA PARTE PORQUE SI NO PUEDO CAMINAR SOBRE EL ELEMENTO ESTE NO ME HACE NADA (NO COMO LOS OPPONENT)
			// else if (g[newPlayerPosition.y][newPlayerPosition.x].canInteract()) {
			//				g[newPlayerPosition.y][newPlayerPosition.x].setContent(g[newPlayerPosition.y][newPlayerPosition.x].getContent());
			//				//				g[newPlayerPosition.y][newPlayerPosition.x].interact(getPlayer());
			//			}
		}
	}

	private void moveBall(Point newBallPosition, Ball ball) {
		g[newBallPosition.y][newBallPosition.x].setContent(getBall());	
		g[ballPosition.y][ballPosition.x].removeContent();
		ballPosition = newBallPosition;
	}

	private void movePlayer(Point newPlayerPosition, Player player) {

		g[newPlayerPosition.y][newPlayerPosition.x].setContent(getPlayer(player.getNum()));
		if(player.getNum()==1){
			//Si la posicion a la que fui no era null no tengo que remover el contenido!
			g[playerPosition1.y][playerPosition1.x].removeContent();
			playerPosition1 = newPlayerPosition;
		}
		else{
			g[playerPosition2.y][playerPosition2.x].removeContent();
			playerPosition2 = newPlayerPosition;
		}
	}

	/**
	 * El metodo <code>alertPlayerMove</code> es llamado por el metodo <code>playerMove</code> en el caso de que el Player
	 * cambie de posicion, para que los Listeners hagan lo que tengan que hacer ante este movimiento, en el caso
	 * nuestro
	 */
	private void alertPlayerMove(Move move) {
		for(ListenerPlayerMove element:listenersPlayerMove){
			element.actionPlayerMove(move);
		}
	}

	public Point getPlayerPosition(int num){
		if(num==1)
			return playerPosition1;
		else
			return playerPosition2;
	}

	public Point getBallPosition(){
		return ballPosition;
	}

	public int getScore1() {
		return score1;
	}

	public int getScore2() {
		return score2;
	}

	public Cell get(int x, int y) {
		return g[y][x];
	}

	public Player getPlayer(int num) {
		if(num==1)
			return (Player) g[playerPosition1.y][playerPosition1.x].getContent();
		if(num==2)
			return (Player) g[playerPosition2.y][playerPosition2.x].getContent();
		return null;
	}
	public Ball getBall() {
		return (Ball) g[ballPosition.y][ballPosition.x].getContent();
	}

	//RE VILLERO
	protected Cell[][] g(){
		return g;
	}

	protected abstract void setContents();

	protected abstract Point getPlayerInitPosition(int num);

	protected abstract Point getBallInitPosition();

	public abstract boolean gameOver();

	public abstract boolean playerWon();
}
