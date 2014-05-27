package view;

import java.awt.Color;

import javax.swing.JPanel;

import model.Game;
import view.panel.GamePanel;

public class MainPanel extends JPanel{
	private static final long serialVersionUID = 1L;

	private static final int CELL_SIZE = 40;
	
	private GamePanel gPanel;
	private ImageManager imgManager;
	
	private int height;
	
	public MainPanel(Game game){
		this.imgManager = new ImageManager();
		this.height = CELL_SIZE * game.getBoardHeight();
		setLayout(null);
		add(gPanel = new GamePanel(game, imgManager, CELL_SIZE,Color.BLACK));
		setSize(CELL_SIZE * game.getBoardWidth(), height);
		refresh();
	}	
	
	public void refresh() {
		gPanel.refresh();
	}
}
