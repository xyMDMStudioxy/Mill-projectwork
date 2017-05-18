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
	
	private boolean occupied;
	
	/**
	 * Es wird ein neues Feld erstellt.
	 * @param posx wird als % Wert mitgegeben Bsp.: Wert 10 = 10% vom Gameboardscreen.
	 * @param posy wird als % Wert mitgegeben Bsp.: Wert 45 = 45% vom Gameboardscreen.
	 * Verwendung: {@link de.projectwork.gui.Gamescreen#createFields()})
	 */
	public Field(int posx, int posy) {
		this.posx = posx;
		this.posy = posy;
		position++;
		this.id = position;
		this.occupied = false;
		this.whichPlayer = 0;
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
	 * @return true = belegt
	 * @return false = frei
	 */
	public boolean isOccupied() {
		if (occupied) {
			return true;
		} else {
			return false;
		}
	}
	
	// TODO
	public boolean removeGamestone(Field[] field, int boardSize) {
		/*for (int i = 0; i < field.length; i++) {
			field[i].getPressedField(posx, posy, boardSize);
			if (field[i].isOccupied()) {
				field[i].whichPlayer = 0;
				field[i].occupied = false;
				return true;
			}
		}*/
		return false;
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
	 * @return 0 = leer
	 * @return 1 = weiß
	 * @return 2 = schwarz
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
	
	/**
	 * Wenn game.player() == 1, dann hat Spieler weiß gerade einen
	 * Spielstein auf ein Feld gelegt.
	 * Wenn game.player() == 2, dann hat Spieler schwarz gerade einen
	 * Spielstein auf ein Feld gelegt.
	 * @param game
	 */
	public void setWhichPlayer(Game game) {
		if (game.player() == 1) {
			this.whichPlayer = 1;
		} else {
			this.whichPlayer = 2;
		}
	}
	
	public void setOccupied(boolean occupied) {
		this.occupied = occupied;
	}
	
}