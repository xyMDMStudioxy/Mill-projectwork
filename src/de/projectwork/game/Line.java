package de.projectwork.game;

/**
 * Eine Linie repr�sentiert immer 3 nebeneinanderliegende Felder 
 * die eine M�hle bilden k�nnen.
 * F�r Feld Informationen siehe: {@link de.projectwork.game.Field}
 */
public class Line {

	private Field field1;
	private Field field2;
	private Field field3;
	
	private boolean mill;
	
	public Line(Field f1, Field f2, Field f3) {
		this.field1 = f1;
		this.field2 = f2;
		this.field3 = f3;
		this.mill = false;
	}
	
	/**
	 * Pr�ft ob ein Spieler eine M�hle mit dem letzten gesetzten/verschobenen
	 * Spielstein gelegt hat.
	 * @return true = M�hle wurde gesetzt/verschoben.
	 * @return false = Keine M�hle wurde gesetzt/verschoben.
	 */
	public boolean checkMill() {
		/**
		 * Pr�ft ob Spieler wei� (getWhichPlayer() == 1) eine M�hle gelegt hat.
		 */
		if (field1.isOccupied() && field2.isOccupied() && field3.isOccupied() &&
				field1.getWhichPlayer() == 1 && field2.getWhichPlayer() == 1 && field3.getWhichPlayer() == 1) {
			setMill(true);
			return true;
		}
		/**
		 * Pr�ft ob Spieler schwarz (getWhichPlayer() == 2) eine M�hle gelegt hat.
		 */
		else if (field1.isOccupied() && field2.isOccupied() && field3.isOccupied() &&
				field1.getWhichPlayer() == 2 && field2.getWhichPlayer() == 2 && field3.getWhichPlayer() == 2) {
			setMill(true);
			return true;
		/**
		 * Kein Spieler hat eine M�hle gelegt.
		 */
		} else {
			return false;
		}
	}
	
	public boolean isMill() {
		if (mill == true) {
			return true;
		} else {
			return false;
		}
	}
	
	/*********************************************
	 * Getter und Setter Methoden der Klasse Line.
	 *********************************************/
	
	public void setMill(boolean mill) {
		this.mill = mill;
	}
	
	public boolean getMill() {
		return mill;
	}
}