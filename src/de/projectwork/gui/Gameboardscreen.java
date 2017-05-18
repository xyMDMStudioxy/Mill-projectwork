package de.projectwork.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import de.projectwork.game.Field;
import de.projectwork.game.Game;
import de.projectwork.game.Line;

/**
 * Repr�sentiert den linken Bildschirmteil des Spielbildschirms der
 * 2/3 des Gesamtplatzes einnimmt. Dieser Bildschirmteil stellt das
 * M�hle Spielbrett dar.
 */
public class Gameboardscreen extends JPanel implements MouseListener {

	private static final long serialVersionUID = -5834887111306741006L;
	
	private JFrame fGamescreen;
	
	private Game game;
	
	private final Field field[] = new Field[24];
	
	private final Line line[] = new Line[16];
	
	private int pressedField;
	private int roundCounter;
	
	public Gameboardscreen(JFrame fGamescreen, Game game) {
		this.fGamescreen = fGamescreen;
		this.game = game;
		
		createFields();
		createLines();
		
		fGamescreen.addMouseListener(this);
		
		setBorder(BorderFactory.createLineBorder(Color.black));
	}
	
	/**
	 * Zuerst wird ermittelt auf welches Feld geklickt wurde --> getPressedField()
	 * Danach wird der entsprechende Spieler gesetzt --> setWhichPlayer()
	 * Dann wird das Feld auf belegt gesetzt --> setOccupied()
	 * Anschlie�end wird der Round Counter um 1 erh�ht --> setRoundCounter()
	 * Mit der Methode checkMill wird �berpr�ft ob eine M�hle gelegt wurde.
	 */
	public void mousePressed(MouseEvent e) {
		for (int i = 0; i < field.length; i++) {
			pressedField = field[i].getPressedField(e.getX(), e.getY() - 25, getBoardSize());
			if (pressedField != -1 && field[i].isOccupied() == false) {
				field[i].setOccupied(true);
				field[i].setWhichPlayer(game);
				for (int j = 0; j < line.length; j++) {
					// Es wurde keine M�hle gelegt.
					if (line[j].checkMill() == false && j == 15) {
						roundCounter = game.getRoundCounter();
						game.setRoundCounter(roundCounter);
					}
					// Es wurde eine neue M�hle gelegt.
					else if (line[j].isMill() == false && line[j].checkMill() == true) {
						field[i].removeGamestone(field, getBoardSize()); // TODO removeGamestone() implementieren.
						System.out.println("isMill = false");
					}
					// M�hle war bereits vor dem letzten gesetzten Spielstein vorhanden.
					else if (line[j].isMill() == true && line[j].checkMill() == true) {
						roundCounter = game.getRoundCounter();
						game.setRoundCounter(roundCounter);
						System.out.println("M�hle");
						break;
					} 
				}
				repaint();
				// TODO gameinfoscreen.repaint();
			}
		}
	}

	/**
	 * Das M�hle Spielbrett wird dynamisch dem Bildschirm angepasst und die
	 * enthaltenen Komponenten werden neu gezeichnet. Dazu geh�ren die Spielsteine
	 * und das Spielbrett.
	 */
	public void paint(Graphics g) {
		super.paint(g);
		Image image = new ImageIcon(this.getClass().getResource("/gameboard2.png")).getImage();
		setBounds(0, 0, getBoardSize(), getBoardSize());
		g.drawImage(image, 0, 0, getBoardSize(), getBoardSize(), null);
		setImage(g);
	}
	
	/**
	 * Die Prozentwerte werden in Pixel umgerechnet um die
	 * Spielsteine und das M�hle Spielbrett zeichnen zu k�nnen.
	 * @param pos
	 * @return
	 */
	public int prozentToPixel(int pos) {
		return getBoardSize() * pos / 100;
	}
	
	/**
	 * Die aktuelle Fenstergr��e wird gegeben und der Spielbildschirm wird
	 * auf 2/3 der Fenstergr��e skaliert.
	 * @return die Gr��e des M�hle Spielbretts (da das Spielbrett immer quadratisch
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
	 * Spielfeld mit Felder wird aufgebaut und die Positionen
	 * der Felder werden in Prozent �bergeben.
	 * siehe Beschreibung von Field: {@link de.projectwork.game.Field#Field(int, int)})	
	 */
	public void createFields() {
		field[0] = new Field(0, 0);
		field[1] = new Field(45, 0);
		field[2] = new Field(90, 0);
		field[3] = new Field(15, 15);
		field[4] = new Field(45, 15);
		field[5] = new Field(75, 15);
		field[6] = new Field(30, 30);
		field[7] = new Field(45, 30);
		field[8] = new Field(60, 30);
		field[9] = new Field(0, 45);
		field[10] = new Field(15, 45);
		field[11] = new Field(30, 45);
		field[12] = new Field(60, 45);
		field[13] = new Field(75, 45);
		field[14] = new Field(90, 45);
		field[15] = new Field(30, 60);
		field[16] = new Field(45, 60);
		field[17] = new Field(60, 60);
		field[18] = new Field(15, 75);
		field[19] = new Field(45, 75);
		field[20] = new Field(75, 75);
		field[21] = new Field(0, 90);
		field[22] = new Field(45, 90);
		field[23] = new Field(90, 90);
	}
	
	/**
	 * Linien werden aus Feldern erstellt.
	 * siehe Beschreibung von Line: {@link de.projectwork.game.Line}
	 */
	public void createLines() {
		line[0] = new Line(field[0], field[1], field[2]);
		line[1] = new Line(field[3], field[4], field[5]);
		line[2] = new Line(field[6], field[7], field[8]);
		line[3] = new Line(field[9], field[10], field[11]);
		line[4] = new Line(field[12], field[13], field[14]);
		line[5] = new Line(field[15], field[16], field[17]);
		line[6] = new Line(field[18], field[19], field[20]);
		line[7] = new Line(field[21], field[22], field[23]);
		line[8] = new Line(field[0], field[9], field[21]);
		line[9] = new Line(field[1], field[4], field[7]);
		line[10] = new Line(field[2], field[14], field[23]);
		line[11] = new Line(field[3], field[10], field[18]);
		line[12] = new Line(field[5], field[13], field[20]);
		line[13] = new Line(field[6], field[11], field[15]);
		line[14] = new Line(field[8], field[12], field[17]);
		line[15] = new Line(field[16], field[19], field[22]);
	}
	
	/**
	 * Das jeweilige Image des Feldes schwarzen, wei�er Spielstein oder
	 * leeres Feld wird gesetzt.
	 * @param g
	 */
	public void setImage(Graphics g) {
		for (int i = 0; i < field.length; i++) {
			if (field[i].getWhichPlayer() == 0) {
				Image imageEmpty = new ImageIcon(this.getClass().getResource("/emptyField.png")).getImage();
				g.drawImage(imageEmpty,
						prozentToPixel(field[i].getPosx()),
						prozentToPixel(field[i].getPosy()),
						prozentToPixel(10),
						prozentToPixel(10),
						null);
			}
			if (field[i].getWhichPlayer() == 1) {
				Image imageWhite = new ImageIcon(this.getClass().getResource("/whiteGamestone.png")).getImage();
				g.drawImage(imageWhite,
						prozentToPixel(field[i].getPosx()),
						prozentToPixel(field[i].getPosy()),
						prozentToPixel(10),
						prozentToPixel(10),
						null);
			}
			if (field[i].getWhichPlayer() == 2) {
				Image imageBlack = new ImageIcon(this.getClass().getResource("/blackGamestone.png")).getImage();
				g.drawImage(imageBlack,
						prozentToPixel(field[i].getPosx()),
						prozentToPixel(field[i].getPosy()),
						prozentToPixel(10),
						prozentToPixel(10),
						null);
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}
}