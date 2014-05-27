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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;

import model.Game;
import model.board.level.GameLevel1;
import model.board.level.GameLevel2;
import model.board.level.GameLevel3;

import org.newdawn.easyogg.OggClip;

import view.BoardPanel;


public class LevelMenu extends JFrame {
	private static final long serialVersionUID = 1L;
	private OggClip ogg;
	public LevelMenu(){

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
			JOptionPane.showMessageDialog(null, "Error al cargar datos visuales", "Error", JOptionPane.ERROR_MESSAGE);
		}

		LevelButtons lb1 = new LevelButtons("1");
		lb1.setBounds(20, 60, 200, 50);


		lb1.addMouseListener(new MouseAdapter() { 
			public void mousePressed(MouseEvent me) { 
				LevelMenu.this.setVisible(false);
				LevelMenu.this.ogg.stop();

				// 	  try {
				MainMenu.getInstance().setBoardPanel(new BoardPanel(new Game(new GameLevel1())));

				//	  } catch (InstantiationException | IllegalAccessException e) {
				//JOptionPane.showMessageDialog(null, "Error al cargar datos del nivel", "Error", JOptionPane.ERROR_MESSAGE);
			}

			//}
		});


		LevelButtons lb2 = new LevelButtons("2");
		lb2.setBounds(20, 120, 200, 50);


		lb2.addMouseListener(new MouseAdapter() { 
			public void mousePressed(MouseEvent me) { 
				LevelMenu.this.setVisible(false);
				LevelMenu.this.ogg.stop();

				//try {
				MainMenu.getInstance().setBoardPanel(new BoardPanel(new Game(new GameLevel2())));
				
				//  } catch (InstantiationException | IllegalAccessException e) {
				//JOptionPane.showMessageDialog(null, "Error al cargar datos del nivel", "Error", JOptionPane.ERROR_MESSAGE);
			}

			//     }
		});

		LevelButtons lb3 = new LevelButtons("3");
		lb3.setBounds(20, 180, 200, 50);


		lb3.addMouseListener(new MouseAdapter() { 
			public void mousePressed(MouseEvent me) { 
				LevelMenu.this.setVisible(false);
				LevelMenu.this.ogg.stop();

				//try {
				MainMenu.getInstance().setBoardPanel(new BoardPanel(new Game(new GameLevel3())));
				
				//  } catch (InstantiationException | IllegalAccessException e) {
				//JOptionPane.showMessageDialog(null, "Error al cargar datos del nivel", "Error", JOptionPane.ERROR_MESSAGE);
			}

			//     }
		});

		add(lb1);
		add(lb2);
		add(lb3);

		try {
			this.ogg = new OggClip(new FileInputStream("resources/intro.ogg"));
			this.ogg.loop();
		} catch (IOException e) {
			//JOptionPane.showMessageDialog(null, "Error al cargar audio", "Error", JOptionPane.ERROR_MESSAGE);
		}
		JMenuBar mainMenuBar = new MenuBar();
		setJMenuBar(mainMenuBar);
	}
	
	
	
}
