package view.gui;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import model.Game;
import model.GameManager;
import model.board.level.GameLevel1;
import view.BoardPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

public class MenuBar extends JMenuBar {
	private static final long serialVersionUID = 1L;

	public MenuBar(){
		//		Menu File
		JMenu file = new JMenu("Archivo");

		file.setMnemonic(KeyEvent.VK_A);

		//		NewGame
		JMenuItem newgame = new JMenuItem("Nuevo Juego");
		newgame.setMnemonic(KeyEvent.VK_N);
		newgame.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,ActionEvent.CTRL_MASK));
		newgame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				MenuBar.this.setVisible(false);
				//MainMenu.getInstance().getOgg().stop(); tengo que hacer singleton si o si?
				//BoardPanel boardPanel = new BoardPanel(new Game(new GameLevel1()));
				//try{
				MainMenu.getInstance().getBoardPanel().getOgg().stop();//}
				MainMenu.getInstance().getBoardPanel().getOgg().close();//}
				//catch(IOException e1){
				//e1.printStackTrace();
				//}
				MainMenu.getInstance().getBoardPanel().dispose();
				MainMenu.getInstance().setBoardPanel(new BoardPanel(new Game(new GameLevel1())));

				MainMenu.getInstance().getBoardPanel().setVisible(true);
				//boardPanel.setVisible(true);
			}	
		});

		//		Open
		JMenuItem open = new JMenuItem("Abrir");
		open.setMnemonic(KeyEvent.VK_O);
		open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,ActionEvent.CTRL_MASK));
		open.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				loadBoard();
			}
		});

		//		Save
		JMenuItem save = new JMenuItem("Guardar");
		save.setMnemonic(KeyEvent.VK_G);
		save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G,ActionEvent.CTRL_MASK));
		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				saveBoard();
			}
		});

		//		Quit
		JMenuItem quit = new JMenuItem("Salir");
		quit.setMnemonic(KeyEvent.VK_Q);
		quit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q,ActionEvent.CTRL_MASK));
		quit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		//		Item Help
		JMenu help = new JMenu("Acerca De");
		help.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});

		//		Developers
		JMenuItem developers = new JMenuItem("Desarrolladores");
		developers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(MenuBar.this,"Football Mundial 2014!\nHirschowitz Kevin\nLucas Carmona");
			}
		});

		// Materia profs
		JMenuItem subject = new JMenuItem("Materia");
		subject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(MenuBar.this,"Programacion Orientada a Objetos\nITBA 2014\nProfesores: Pablo Giorgi y don Claudio");
			}
		});

		file.add(newgame);
		file.add(open);
		file.add(save);
		file.add(quit);
		help.add(developers);
		help.add(subject);
		this.add(file);
		this.add(help);
	}

	private void loadBoard(){
		JFileChooser file = new JFileChooser(); 
		file.setCurrentDirectory(new File("Saved Games"));
		file.showDialog(MainMenu.getInstance().getContentPane(), "Open file");

		GameManager game = new GameManager();
		if (file.getSelectedFile()!=null)
			try {
				MainMenu.getInstance().getBoardPanel().close();
				MainMenu.getInstance().setBoardPanel(new BoardPanel(new Game(game.loadBoard(file.getSelectedFile().getPath()))));
				MainMenu.getInstance().getBoardPanel().setVisible(true);
			} catch (ClassNotFoundException | IOException e1) {
				e1.printStackTrace();
			}
	}

	private void saveBoard() {
		JFileChooser file = new JFileChooser(); 
		file.setCurrentDirectory(new File("Saved Games"));
		System.out.println(file.getCurrentDirectory());
		file.showDialog(MainMenu.getInstance().getContentPane(), "Save file");

		GameManager game = new GameManager();
		if (file.getSelectedFile()!=null)
			try {
				System.out.println(MainMenu.getInstance());//.getBoardPanel());
				game.saveBoard(MainMenu.getInstance().getBoardPanel().getGame().getBoard(), file.getSelectedFile().getPath());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
	}



}
