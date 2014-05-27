package view.gui;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;

import model.Game;
import model.GameManager;

import org.newdawn.easyogg.OggClip;

import view.BoardPanel;

public class MainMenu extends JFrame {

	private OggClip ogg;
	private static final long serialVersionUID = 1L;
	private BoardPanel boardPanel;

	private static MainMenu instance;

	public static MainMenu getInstance(){
		if(instance==null){
			instance=new MainMenu();
		}
		return instance;
	}
	private MainMenu(){
		super("Football Mundial 2014");

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		Toolkit toolkit = getToolkit();
		Dimension size = toolkit.getScreenSize();
		this.setSize(800, 545);
		this.setLocation(size.width/2 - getWidth()/2, size.height/2 - getHeight()/2);

		setContentPane(new JLabel(new ImageIcon("resources/mainMenu.png")));

		File sourceimage = new File("resources/icon.png");
		try {
			Image image = ImageIO.read(sourceimage);
			this.setIconImage(image);
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		PlayButton playButton = new PlayButton();
		playButton.addMouseListener(new MouseAdapter() { 
			public void mousePressed(MouseEvent me) { 
				MainMenu.this.setVisible(false);
				MainMenu.this.getOgg().stop();
				LevelMenu levelMenu = new LevelMenu();
				levelMenu.setVisible(true);

			}
		});


		LoadButton loadButton = new LoadButton();
		loadButton.addMouseListener(new MouseAdapter() { 
			public void mousePressed(MouseEvent me) { 
				//				MainMenu.this.setVisible(false);
				//				MainMenu.this.getOgg().stop();
				loadBoard();

			}
		});
		
		InformationButton informationButton = new InformationButton();
		informationButton.addMouseListener(new MouseAdapter() { 
			public void mousePressed(MouseEvent me) { 

				JOptionPane.showMessageDialog(MainMenu.this,"Football Mundial 2014!\nHirschowitz Kevin\nLucas Carmona");

			}
		});

		QuitButton quitButton= new QuitButton();
		quitButton.addMouseListener(new MouseAdapter() { 
			public void mousePressed(MouseEvent me) { 
				MainMenu.this.setVisible(false);
				MainMenu.this.getOgg().stop();
				System.exit(0);
			}
		});

		add(loadButton);
		add(playButton);
		add(informationButton);
		add(quitButton);

		try {
			this.setOgg(new OggClip(new FileInputStream("resources/intro.ogg")));
			this.getOgg().loop();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Error al cargar audio", "Error", JOptionPane.ERROR_MESSAGE);
		}

		JMenuBar mainMenuBar = new MenuBar();
		setJMenuBar(mainMenuBar);
	}

	public BoardPanel getBoardPanel() {
		return boardPanel;
	}

	public void setBoardPanel(BoardPanel boardPanel) {
		this.setVisible(false);
		this.boardPanel = boardPanel;
		this.boardPanel.setVisible(true);
	}
	private void setOgg(OggClip oggClip) {
		this.ogg=oggClip;		
	}
	public OggClip getOgg(){
		return ogg;
	}

	private void loadBoard(){
		JFileChooser file = new JFileChooser(); 
		file.setCurrentDirectory(new File("Saved Games"));
		file.showDialog(MainMenu.getInstance().getContentPane(), "Open file");

		GameManager game = new GameManager();
		if (file.getSelectedFile()!=null)
			try {
				setBoardPanel(new BoardPanel(new Game(game.loadBoard(file.getSelectedFile().getPath()))));
			} catch (ClassNotFoundException | IOException e1) {
				e1.printStackTrace();
			}
	}



	public static void main(String[] args) {
		MainMenu mainMenu = MainMenu.getInstance();
		mainMenu.setVisible(true);
	}

}