package de.projectwork.game;

/**
 * Repräsentiert ein einzelnes Feld auf dem Mühle Spielbrett.
 * Insgesamt gibt es auf dem Mühle Spielbrett 24 Felder.
 */
public class Field {
	
	private int posx;
	private int posy;
	private static int position = -1;
	private int id;
	private int whichPlayer;
	private int line1;
	private int line2;
	
	private boolean occupied;
	
	/**
	 * Es wird ein neues Feld erstellt.
	 * @param posx wird als % Wert mitgegeben Bsp.: Wert 10 = 10% vom Gameboardscreen.
	 * @param posy wird als % Wert mitgegeben Bsp.: Wert 45 = 45% vom Gameboardscreen.
	 * @param Verbindungslinie1 in der sich das Feld befindet.
	 * @param Verbindungslinie2 in der sich das Feld befindet.
	 * Verwendung: {@link de.projectwork.gui.Gameboardscreen#createFields()})
	 */
	public Field(int posx, int posy, int line1, int line2) {
		this.posx = posx;
		this.posy = posy;
		position++;
		this.id = position;
		this.occupied = false;
		this.whichPlayer = 0;
		this.line1 = line1;
		this.line2 = line2;
	}
	
	/**
	 * Es wird überprüft auf welches Feld geklickt wurde. Jedes Feld ist jeweils 10% groß in x und
	 * y Richtung.
	 * @param mousePosX wird als absoluter Wert übergeben Bsp.: Wert 394 = 394 Pixel in x Richtung.
	 * @param mousePosY wird als absoluter Wert übergeben Bsp.: Wert 253 = 253 Pixel in y Richtung.
	 * @param boardSize {@link de.projectwork.gui.Gameboardscreen#getGameboardSize()})
	 * @return id des Feldes oder wenn kein Feld angeklickt wurde -1.
	 */
	public int getPressedField(int mousePosX, int mousePosY, int boardSize) {
		int boardProzentX = 100 * mousePosX / boardSize;
		int boardProzentY = 100 * mousePosY / boardSize;
		if ((posx < boardProzentX && posx + 10 > boardProzentX) &&
				(posy < boardProzentY && posy + 10 > boardProzentY)) {
			return id;
		} else {
			return -1;
		}
	}
	
	/**
	 * Prüft ob ein Feld belegt ist oder frei ist.
	 * @return true = belegt || false = frei
	 */
	public boolean isOccupied() {
		if (occupied) {
			return true;
		} else {
			return false;
		}
	}
	
	public void removeGamestone() {
		this.whichPlayer = 0;
	}
	
	/**********************************************
	 * Getter und Setter Methoden der Klasse Field.
	 **********************************************/
	
	public int getId() {
		return id;
	}
	
	public int getPosx() {
		return posx;
	}
	
	public int getPosy() {
		return posy;
	}
	
	public boolean getOccupied() {
		return occupied;
	}
	
	/**
	 * Welcher Spieler (Spielstein) ist gerade auf dem Feld. 
	 * @return 0 = leer || 1 = weiß || 2 = schwarz
	 */
	public int getWhichPlayer() {
		if (whichPlayer == 0) {
			return 0;
		} else if (whichPlayer == 1) {
			return 1;
		} else {
			return 2;
		}
	}
	
	public int getLine1() {
		return line1;
	}
	
	public int getLine2() {
		return line2;
	}
	
	/**
	 * Wenn game.getCurrentPlayer() == 1, dann hat Spieler weiß gerade einen
	 * Spielstein auf ein Feld gelegt.
	 * Wenn game.getCurrentPlayer() == 2, dann hat Spieler schwarz gerade einen
	 * Spielstein auf ein Feld gelegt.
	 * @param game Objekt
	 */
	public void setWhichPlayer(Game game) {
		if (game.getCurrentPlayer() == 1) {
			this.whichPlayer = 1;
		} else {
			this.whichPlayer = 2;
		}
	}
	
	/**
	 * Ein Feld wird auf belegt oder frei gesetzt.
	 * @param occupied true = belegen || false = frei machen
	 */
	public void setOccupied(boolean occupied) {
		this.occupied = occupied;
	}
}