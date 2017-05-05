package de.projectwork.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import de.projectwork.game.Field;

/**
 * Repräsentiert den linken Bildschirmteil des Spielbildschirms der
 * 2/3 des Gesamtplatzes einnimmt. Dieser Bildschirmteil stellt das
 * Mühle Spielbrett dar.
 */
public class Gameboardscreen extends JPanel {

	private static final long serialVersionUID = -5834887111306741006L;

	private JFrame fGamescreen;
	
	private final Field field[];
	
	public int boardOffset = 30;
	
	public Gameboardscreen(JFrame fGamescreen, Field field[]) {
		this.fGamescreen = fGamescreen;
		this.field = field;
		setBorder(BorderFactory.createLineBorder(Color.black));
		
		// TODO mousePressed als seperate Methode.
		fGamescreen.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				//System.out.println("X: " + e.getX());
				//System.out.println("Y: " + e.getY());
				for (int i = 0; i < field.length; i++) {
					field[i].getPressedField(e.getX(), e.getY(), getGameboardSize());
				}
			}
		});
	}
	
	/*public void mousePressed(MouseEvent e) {
		System.out.println("X: " + e.getX());
		System.out.println("Y: " + e.getY());
		for (int i = 0; i < field.length; i++) {
			field[i].getPressedField(e.getX(), e.getY(), getGameboardSize());
		}
	}*/
	
	/**
	 * Das Mühle Spielbrett wird dynamisch dem Bildschirm angepasst und die
	 * enthaltenen Komponenten werden neu gezeichnet. Dazu gehören die Spielsteine
	 * und das Spielbrett.
	 */
	public void paint(Graphics g) {
		super.paint(g);
		Image image = new ImageIcon(this.getClass().getResource("/gameboard2.png")).getImage();
		setBounds(0, 0, getBoardSize(), getBoardSize());
		g.drawImage(image, 
				boardOffset, 
				boardOffset, 
				getGameboardSize(),
				getGameboardSize(),
				null);
		// TODO auslagern, da Bilder bei jedem Aufruf wieder neu geladen werden --> hoher Rechenaufwand.
		Image imageBlack = new ImageIcon(this.getClass().getResource("/blackGamestone.png")).getImage();
		Image imageWhite = new ImageIcon(this.getClass().getResource("/whiteGamestone.png")).getImage();
		for (int i = 0; i < field.length; i++) {
			if (field[i].getWhichPlayer() == 1) {
				g.drawImage(imageWhite,
						prozentToPixel(field[i].getPosx()) + boardOffset,
						prozentToPixel(field[i].getPosy()) + boardOffset,
						prozentToPixel(10),
						prozentToPixel(10),
						null);
			}
			if (field[i].getWhichPlayer() == 2) {
				g.drawImage(imageBlack,
						prozentToPixel(field[i].getPosx()) + boardOffset,
						prozentToPixel(field[i].getPosy()) + boardOffset,
						prozentToPixel(10),
						prozentToPixel(10),
						null);
			}
		}
		g.drawString("Runde: ", getBoardSize() + 20, getBoardSize() + 20);
	}
	
	/**
	 * Die Prozentwerte werden in Pixel umgerechnet um die
	 * Spielsteine und das Mühle Spielbrett zeichnen zu können.
	 * @param pos
	 * @return
	 */
	public int prozentToPixel(int pos) {
		return getGameboardSize() * pos / 100;
	}
	
	/**
	 * Die aktuelle Fenstergröße wird gegeben und der Spielbildschirm wird
	 * auf 2/3 der Fenstergröße skaliert.
	 * @return die Größe des Mühle Spielbretts (da das Spielbrett immer quadratisch
	 * ist reicht ein Wert).
	 */
	public int getBoardSize() {
		int width = fGamescreen.getWidth();
		int height = fGamescreen.getHeight();
		if (width / 3 * 2 < height) {
			return width / 3 * 2;
		} else {
			return height;
		}
	}
	
	/**
	 * @return getBoardSize() - boardOffset * 2
	 */
	public int getGameboardSize() {
		return getBoardSize() - boardOffset * 2;
	}
}