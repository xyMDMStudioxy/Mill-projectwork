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
 * Repräsentiert den linken Bildschirmteil des Spielbildschirms der
 * 2/3 des Gesamtplatzes einnimmt. Dieser Bildschirmteil stellt das
 * Mühle Spielbrett dar.
 */
public class Gameboardscreen extends JPanel implements MouseListener {

	private static final long serialVersionUID = -5834887111306741006L;
	
	private JFrame fGamescreen;
	
	private Game game;
	
	private final Field field[] = new Field[24];
	
	private final Line line[] = new Line[16];
	
	private int pressedField;
	private int roundCounter;
	private int currentField;
	
	private boolean removeGamestone = false;
	
	public Gameboardscreen(JFrame fGamescreen, Game game) {
		this.fGamescreen = fGamescreen;
		this.game = game;
		
		createFields();
		createLines();
		
		fGamescreen.addMouseListener(this);
		
		setBorder(BorderFactory.createLineBorder(Color.black));
	}
	
	/**
	 * Es wird überprüft in welcher Spielphase sich das Spiel
	 * gerade befindet.
	 */
	public void mousePressed(MouseEvent e) {
		if (game.getGamePhase() == 1) {
			phase1(e);
		} else if (game.getGamePhase() == 2) {
			phase2(e); // TODO Phase2 implementieren.
		} else if (game.getGamePhase() == 3) {
			phase3(e); // TODO Phase3 implementieren.
		}
		repaint();
		// TODO gameinfoscreen.repaint();
	}

	/**
	 * Das Mühle Spielbrett wird dynamisch dem Bildschirm angepasst und die
	 * enthaltenen Komponenten werden neu gezeichnet. Dazu gehören die Spielsteine
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
	 * Repräsentiert die 1.Spielphase (Setzphase).
	 * @param e
	 */
	public void phase1(MouseEvent e) {
		currentField = getPressedField(e);
		if (currentField != -1) {				// Wurde auf ein Feld angeklickt oder daneben geklickt?
			if (removeGamestone == false) {		// Der Spieler darf einen Spielstein setzen.
				// TODO KI einbauen.
				if (field[currentField].isOccupied() == false) {
					// TODO KI einbauen.
					field[currentField].setOccupied(true);
					field[currentField].setWhichPlayer(game);
					// Es wird überprüft ob der neu gesetzte Spielstein eine neue
					// Mühle gebildet hat.
					// ... wenn ja darf der aktuelle Spieler dem Gegner einen
					// Spielstein weg nehmen.
					if (line[field[currentField].getLine1()].checkMill() == true ||
							line[field[currentField].getLine2()].checkMill() == true) {
						removeGamestone = true;
					// ... wenn nein ist der gegnerische Spieler am Zug.
					} else {
						roundCounter = game.getRoundCounter();
						game.setRoundCounter(roundCounter);
						game.changeCurrentPlayer();
					}
					if (roundCounter == 17) {
						game.setGamePhase(2);
					}
				}
			}
			else {
				removeGamestone();
			}
		}
	}
	
	/**
	 * Repräsentiert die 2.Spielphase (Verschiebenphase).
	 * @param e
	 */
	public void phase2(MouseEvent e) {
	
	}
	
	/**
	 * Repräsentiert die 3.Spielphase (Springphase).
	 * @param e
	 */
	public void phase3(MouseEvent e) {
		
	}
	
	/**
	 * Überprüft welches Feld gerade geklickt wurde.
	 * @param e MouseEvent
	 * @return field id || -1 bei falschen Klick.
	 */
	public int getPressedField(MouseEvent e) {
		for (int i = 0; i < field.length; i++) {
			pressedField = field[i].getPressedField(e.getX(), e.getY() - 25, getBoardSize());
			if (pressedField >= 0) {
				return pressedField;
			}
		}
		return pressedField;
	}
	
	/**
	 * Ein Spieler darf dem Gegner einen Spielstein entfernen, weil er eine
	 * Mühle gelegt hat. Es wird überprüft, ob das Feld nicht leer ist und ob
	 * auf dem Feld auch ein gegnerischer Spielstein liegt und kein eigener.
	 * Anschließend wird überprüft, ob der Spielstein zu einer Mühle gehört.
	 * ... wenn ja darf der Spielstein nicht entfernt werden.
	 * ... wenn nein darf der Spielstein entfernt werden.
	 */
	public void removeGamestone() {
		int currentPressedField = field[currentField].getWhichPlayer();
		if (currentPressedField != 0 && currentPressedField != game.getCurrentPlayer()) {
			if (line[field[currentField].getLine1()].checkMill() == false) {
				if (line[field[currentField].getLine2()].checkMill() == false) {
					// TODO Wenn alle Spielsteine in einer Mühle liegen dann entfernen
					// eines Steines zulassen.
					field[currentField].setOccupied(false);
					field[currentField].removeGamestone();
					roundCounter = game.getRoundCounter();
					game.setRoundCounter(roundCounter);
					game.changeCurrentPlayer();
					removeGamestone = false;
				}
			}
		}
	}
	
	/**
	 * Die Prozentwerte werden in Pixel umgerechnet um die
	 * Spielsteine und das Mühle Spielbrett zeichnen zu können.
	 * @param pos
	 * @return
	 */
	public int prozentToPixel(int pos) {
		return getBoardSize() * pos / 100;
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
	 * Spielfeld mit Felder wird aufgebaut und die Positionen
	 * der Felder werden in Prozent übergeben und die beiden Verbindungslinien
	 * die zu dem jeweiligen Feld gehören.
	 * siehe Beschreibung von Field: {@link de.projectwork.game.Field#Field(int, int)})	
	 */
	public void createFields() {
		field[0] = new Field(0, 0, 8, 0);
		field[1] = new Field(45, 0, 11, 0);
		field[2] = new Field(90, 0, 15, 0);
		field[3] = new Field(15, 15, 9, 1);
		field[4] = new Field(45, 15, 11, 1);
		field[5] = new Field(75, 15, 14, 1);
		field[6] = new Field(30, 30, 10, 2);
		field[7] = new Field(45, 30, 11, 2);
		field[8] = new Field(60, 30, 13, 2);
		field[9] = new Field(0, 45, 8, 3);
		field[10] = new Field(15, 45, 9, 3);
		field[11] = new Field(30, 45, 10, 3);
		field[12] = new Field(60, 45, 13, 4);
		field[13] = new Field(75, 45, 14, 4);
		field[14] = new Field(90, 45, 15, 4);
		field[15] = new Field(30, 60, 10, 5);
		field[16] = new Field(45, 60, 12, 5);
		field[17] = new Field(60, 60, 13, 5);
		field[18] = new Field(15, 75, 9, 6);
		field[19] = new Field(45, 75, 12, 6);
		field[20] = new Field(75, 75, 14, 6);
		field[21] = new Field(0, 90, 8, 7);
		field[22] = new Field(45, 90, 12, 7);
		field[23] = new Field(90, 90, 15, 7);
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
		line[9] = new Line(field[3], field[10], field[18]);
		line[10] = new Line(field[6], field[11], field[15]);
		line[11] = new Line(field[1], field[4], field[7]);
		line[12] = new Line(field[16], field[19], field[22]);
		line[13] = new Line(field[8], field[12], field[17]);
		line[14] = new Line(field[5], field[13], field[20]);
		line[15] = new Line(field[2], field[14], field[23]);
	}
	
	/**
	 * Das jeweilige Image des Feldes schwarzen, weißer Spielstein oder
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