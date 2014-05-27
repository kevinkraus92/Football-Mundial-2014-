package view.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.JButton;

public class LoadButton extends JButton {

	private static final long serialVersionUID = 1L;

	public LoadButton(){
		setBorder(null);
		setBorderPainted(false);
		setContentAreaFilled(false);
		setOpaque(false);
		setFocusPainted(false);
		setBounds(10, 180, 200, 50);
		setText("Cargar");

		try {
			InputStream is = new FileInputStream("resources/goticaBastard.ttf");
			Font GoticaBastard = Font.createFont(Font.TRUETYPE_FONT, is);
			GoticaBastard = GoticaBastard.deriveFont(32f);
			setForeground(Color.WHITE);
			setFont(GoticaBastard);

		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
		}

		addMouseListener(new MouseAdapter() { 
			public void mousePressed(MouseEvent me) {
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				setForeground(Color.GRAY);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				setForeground(Color.WHITE);
			}

		});
	}
}