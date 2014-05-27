package view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

import org.newdawn.easyogg.OggClip;

import view.gui.MenuBar;
import model.Game;
import model.board.Move;

public class BoardPanel extends JFrame {
	private static final long serialVersionUID = 1L;

	private Game game;

	private MainPanel mainPanel;
	private OggClip ogg;
	private JPanel stats;
	private JLabel score1;
	private JLabel score2;
	private JMenuBar mainMenuBar;

	public BoardPanel(Game game) {
		super("Football Mundial 2014!");

		this.game = game;

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		Toolkit toolkit = getToolkit();
		Dimension size = toolkit.getScreenSize();
		this.setContentPane(mainPanel = new MainPanel(game));
		this.setSize(mainPanel.getWidth(), mainPanel.getHeight() + 80);
		this.setLocation(size.width/2 - getWidth()/2, size.height/2 - getHeight()/2);

		try {
			this.ogg = new OggClip(new FileInputStream("resources/map.ogg"));
			this.ogg.loop();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Error al cargar audio", "Error", JOptionPane.ERROR_MESSAGE);
		}

		addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				switch ( e.getKeyCode() ) {
				case KeyEvent.VK_UP:
					BoardPanel.this.game.onMove(Move.UP,2); break;
				case KeyEvent.VK_DOWN:
					BoardPanel.this.game.onMove(Move.DOWN,2); break;
				case KeyEvent.VK_LEFT:
					BoardPanel.this.game.onMove(Move.LEFT,2); break;
				case KeyEvent.VK_RIGHT:
					BoardPanel.this.game.onMove(Move.RIGHT,2); break;

				case KeyEvent.VK_W:
					BoardPanel.this.game.onMove(Move.UP,1); break;
				case KeyEvent.VK_S:
					BoardPanel.this.game.onMove(Move.DOWN,1); break;
				case KeyEvent.VK_A:
					BoardPanel.this.game.onMove(Move.LEFT,1); break;
				case KeyEvent.VK_D:
					BoardPanel.this.game.onMove(Move.RIGHT,1); break;
				}
				mainPanel.refresh();
				score1.setText(Integer.toString(BoardPanel.this.game.getBoard().getScore1()));
				score2.setText(Integer.toString(BoardPanel.this.game.getBoard().getScore2()));
			}
		});



		stats = new JPanel();
		stats.setLayout(new FlowLayout());
		stats.setBounds(0, mainPanel.getHeight(), mainPanel.getWidth(), 40);
		JLabel lscore = new JLabel("Resultado: ");
		score1 = new JLabel(Integer.toString(game.getBoard().getScore1()));
		score2 = new JLabel(Integer.toString(game.getBoard().getScore2()));

		//		JButton restart = new JButton("Reiniciar(r)");
		//		restart.addActionListener(new ActionListener() {
		//			@Override
		//			public void actionPerformed(ActionEvent e){
		//				restart();
		//				}});
		//		

		stats.add(lscore);
		stats.add(score1);
		stats.add(score2);
		stats.add(new JLabel("   "));
		//		stats.add(restart);
		stats.setBorder(new EtchedBorder());

		add(stats);
		
		mainMenuBar = new MenuBar();
		setJMenuBar(mainMenuBar);


	}

	
	public OggClip getOgg(){
		return ogg;
	}
	
	public Game getGame() {
		return game;
	}
	
	public void close(){
		this.ogg.stop();
		this.ogg.close();
		dispose();
	}
	/**
	 * 
	 * @param args
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	//	public static void main(String[] args) throws InstantiationException, IllegalAccessException {
	//		
	//		
	////		BoardPanel mainWindow = new BoardPanel(new Game(GameLevel1.class));
	//		BoardPanel mainWindow = new BoardPanel(new Game(new GameLevel1()));
	//		mainWindow.setVisible(true);
	//		
	//	}
}

