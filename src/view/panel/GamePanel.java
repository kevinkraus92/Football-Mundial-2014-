package view.panel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.JPanel;

import model.Game;
import model.board.Cell;
import view.ImageManager;

public class GamePanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private Game game;
	private ImageManager imgManager;
	private int rows, columns;
	private int cellSize;
	private Color color;
	private Image[][] images;


	public GamePanel(Game game, ImageManager imgManager, final int cellSize, Color color) {
		this.game = game;
		this.imgManager = imgManager;
		this.rows = game.getBoardHeight();
		this.columns = game.getBoardWidth();
		this.images = new Image[rows][columns];
		this.cellSize = cellSize;
		this.color = color;
		setSize(columns * cellSize, rows * cellSize);

	}

	public void put(Image image, int row, int column) {
		images[row][column] = image;
	}

	public void clear(int row, int column) {
		images[row][column] = null;
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(color);
		g.fillRect(0, 0, columns * cellSize, rows * cellSize);

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				if (images[i][j] != null) {
					g.drawImage(images[i][j], j * cellSize, i * cellSize, null);
				}
			}
		}
		if (game.isOver()) {
			Font plain=new Font("Arial", Font.ITALIC, 30);
			g.setFont(plain);
			Graphics2D string= (Graphics2D) g;
			if (game.playerWon()) {
				//				string.setBackground(Color.RED);
				string.setColor(Color.BLUE);
				//				string.drawString("Game Over - Player WON !!!", 10, 140);
			} else {
				//				string.setBackground(Color.RED);
				string.setColor(Color.RED);
				string.drawString("Game Over - Player DIED !!!", 10, 140);
			}
		}


	}

	public void refresh() {
		for(int i=0; i<game.getBoardHeight(); i++){
			for(int j=0; j<game.getBoardWidth(); j++) {
				Cell cell = game.get(i, j);
				put(imgManager.get(cell), i, j);
			}
		}
		repaint();
	}
}